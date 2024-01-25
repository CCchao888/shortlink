package com.chao.shortlink.admin.dao.entity;

/**
 * Author:chao
 * Date:2024-01-22
 * Description: 用户持久层实体
 */

import com.baomidou.mybatisplus.annotation.TableName;
import com.chao.shortlink.admin.common.database.BaseDO;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@TableName("t_user")
@Data
public class UserDO  extends BaseDO implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    //@TableId(type = IdType.AUTO)
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 注销时间戳
     */
    private Long deletionTime;


}
