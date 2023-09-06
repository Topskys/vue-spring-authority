package com.manong.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 重新封装课程返回值对象
 */
@Data
public class CourseVo {
    private Integer id;
    private String title;
    private String teacherName;
    private String subjectParentTitle;
    private String subjectTitle;
    private BigDecimal price;
    private Integer lessonNum;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private Date modifyDate;
}