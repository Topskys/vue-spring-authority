package com.manong.vo;

import com.manong.entity.Teacher;
import lombok.Data;

import java.util.Date;

@Data
public class TeacherQueryVo extends Teacher {
    //讲师姓名、讲师头衔、添加(开始时间和结束时间)
    private Date startDate;
    private Date endDate;
}
