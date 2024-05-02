package com.javahomework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 来自
     */
    private String form;

    /**
     * 发送给，-1为所有人
     */
    @TableField(value = "toUser")
    private Integer toUser;

    /**
     * 内容
     */
    private String txt;

    /**
     * 内容
     */
    private Integer state;
}
