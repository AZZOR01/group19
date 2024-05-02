package com.javahomework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author com
 * @since 2024-04-25
 */
@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 账号
     */
    private String no;

    /**
     * 密码
     */
    private String password;

    /**
     * male,emale
     */
    private String sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 星级
     */
    private Integer star;

    /**
     * 角色 0管理, 1教练, 2用户
     */
    private Integer roleId;

    /**
     * 预约剩余次数
     */
    private Integer num;

    /**
     * 执教时长
     */
    private Double time;

    /**
     * 评分
     */
    private Double score;

    /**
     * 所获奖项
     */
    private String awards;

    /**
     * 擅长项目
     */
    private String good;

    /**
     * 最后登陆
     */
    private String lastTime;

    /**
     * 过期时间
     */
    private Long expiration;
}
