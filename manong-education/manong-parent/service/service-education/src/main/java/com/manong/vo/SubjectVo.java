package com.manong.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectVo {
    private Integer id;
    private String title;
    private Integer sort;
    //子分类列表
    private List<SubjectVo> children = new ArrayList<SubjectVo>();
}
