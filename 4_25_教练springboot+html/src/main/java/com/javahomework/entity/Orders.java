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
 * @since 2024-04-28
 */
@Getter
@Setter
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 时间
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * userId
     */
    private Integer userId;

    /**
     * user姓名
     */
    private String name;

    /**
     * 购买次数
     */
    private Integer frequency;

    /**
     * 金额
     */
    private Double money;
}
