package com.manong.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author KazuGin
 * @since 2021-03-02
 */
@TableName("edu_course")
@Data
public class Course{


    /**
     * 课程ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程讲师ID
     */
    @TableField("teacherId")
    private Integer teacherId;

    /**
     * 课程专业ID
     */
    @TableField("subjectId")
    private Integer subjectId;

    /**
     * 课程专业父级ID
     */
    @TableField("subjectParentId")
    private Integer subjectParentId;

    /**
     * 课程标题
     */
    private String title;

    /**
     * 课程销售价格，设置为0则可免费观看
     */
    private BigDecimal price;

    /**
     * 总课时
     */
    @TableField("lessonNum")
    private Integer lessonNum;

    /**
     * 课程封面图片路径
     */
    private String cover;

    /**
     * 销售数量
     */
    @TableField("buyCount")
    private Long buyCount;

    /**
     * 浏览数量
     */
    @TableField("viewCount")
    private Long viewCount;

    /**
     * 课程状态 1未发布  2已发布
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "createDate",fill = FieldFill.INSERT)
    private Date createDate;

    /**
     * 更新时间
     */
    @TableField(value = "modifyDate",fill = FieldFill.INSERT_UPDATE)
    private Date modifyDate;



}
