package com.chao.shortlink.project.config;

import com.chao.shortlink.project.mq.consumer.ShortLinkStatsSaveConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.chao.shortlink.project.common.constant.RedisKeyConstant.SHORT_LINK_STATS_STREAM_GROUP_KEY;
import static com.chao.shortlink.project.common.constant.RedisKeyConstant.SHORT_LINK_STATS_STREAM_TOPIC_KEY;

/**
 * Author:chao
 * Date:2024-02-22
 * Description: Redis Stream 消息队列配置
 */

@Configuration
@RequiredArgsConstructor
public class RedisStreamConfiguration {

    private final RedisConnectionFactory redisConnectionFactory;
    private final ShortLinkStatsSaveConsumer shortLinkStatsSaveConsumer;


    /**
     * 线程池执行异步从 Redis Stream 中拉取消息的任务
     * @return
     */
    @Bean
    public ExecutorService asyncStreamConsumer() {
        AtomicInteger index = new AtomicInteger();
        int processors = Runtime.getRuntime().availableProcessors();
        return new ThreadPoolExecutor(processors,
                processors + processors >> 1,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                runnable -> {
                    Thread thread = new Thread(runnable);
                    thread.setName("stream_consumer_short-link_stats_" + index.incrementAndGet());
                    thread.setDaemon(true);
                    return thread;
                }
        );
    }

    /**
     * 消息监听容器会自动监听指定 Redis Stream 中的消息，并在消息到达时调用指定的消费者进行处理。
     * @param asyncStreamConsumer
     * @return
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer(ExecutorService asyncStreamConsumer) {
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> options =
                StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                        .builder()
                        // 一次最多获取多少条消息
                        .batchSize(10)
                        // 执行从 Stream 拉取到消息的任务流程
                        .executor(asyncStreamConsumer)
                        // 如果没有拉取到消息，需要阻塞的时间。不能大于 ${spring.data.redis.timeout}，否则会超时
                        .pollTimeout(Duration.ofSeconds(3))
                        .build();
        StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer =
                StreamMessageListenerContainer.create(redisConnectionFactory, options);
        streamMessageListenerContainer.receiveAutoAck(Consumer.from(SHORT_LINK_STATS_STREAM_GROUP_KEY, "stats-consumer"),
                StreamOffset.create(SHORT_LINK_STATS_STREAM_TOPIC_KEY, ReadOffset.lastConsumed()), shortLinkStatsSaveConsumer);
        return streamMessageListenerContainer;
    }
}