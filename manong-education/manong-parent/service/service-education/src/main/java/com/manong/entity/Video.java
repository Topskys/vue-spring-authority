package com.manong.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("edu_video")
public class Video implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 视频ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程ID
     */
    @TableField("courseId")
    private Integer courseId;

    /**
     * 章节ID
     */
    @TableField("chapterId")
    private Integer chapterId;

    /**
     * 节点名称
     */
    private String title;

    /**
     * 云端视频资源
     */
    @TableField("videoSourceId")
    private String videoSourceId;

    /**
     * 原始文件名称
     */
    @TableField("videoOriginalName")
    private String videoOriginalName;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 播放次数
     */
    @TableField("playCount")
    private Long playCount;

    /**
     * 是否可以试听：0收费 1免费
     */
    @TableField("isFree")
    private Boolean isFree;

    /**
     * 视频时长（秒）
     */
    private Float duration;

    /**
     * 状态
     */
    private String status;

    /**
     * 视频源文件大小（字节）
     */
    private Long size;

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
