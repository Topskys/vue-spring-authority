package com.manong.entity.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 封装课程信息表单数据
 */
@Data
public class CourseInfoForm {
    private Integer id;
    private Integer teacherId;
    private Integer subjectId;
    private Integer subjectParentId;
    private String title;
    private BigDecimal price;
    private Integer lessonNum;
    private String cover;
    private String description;
}
