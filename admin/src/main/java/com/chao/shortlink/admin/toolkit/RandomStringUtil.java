package com.chao.shortlink.admin.toolkit;

/**
 * Author:chao
 * Date:2024-01-25
 * Description: 分组ID随机生成器
 */
import java.util.Random;

public final class RandomStringUtil {

    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int LENGTH = 6;

    public static String generateRandomString() {
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            randomString.append(CHARACTERS.charAt(randomIndex));
        }
        return randomString.toString();
    }
}
