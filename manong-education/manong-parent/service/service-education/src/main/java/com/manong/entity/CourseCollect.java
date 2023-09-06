package com.manong.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("edu_course_collect")
public class CourseCollect{


    /**
     * 收藏ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程讲师ID
     */
    @TableField("courseId")
    private Integer courseId;

    /**
     * 课程专业ID
     */
    @TableField("memberId")
    private Integer memberId;

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
