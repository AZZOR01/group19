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
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 教练id
     */
    private Integer coachId;

    /**
     * 教练名称
     */
    private String coachName;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 场地
     */
    private String field;

    /**
     * 状态,0未确认，1已确认，2已完成,-1以取消
     */
    private Integer state;

    /**
     * 修改次数
     */
    private Integer num;
}
