package com.manong.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("edu_chapter")
public class Chapter implements Serializable {


    /**
     * 章节ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程ID
     */
    @TableField("courseId")
    private Integer courseId;

    /**
     * 章节名称
     */
    private String title;

    /**
     * 显示排序
     */
    private Integer sort;

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
