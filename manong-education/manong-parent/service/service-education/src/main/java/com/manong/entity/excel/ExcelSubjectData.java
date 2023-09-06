package com.manong.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelSubjectData {
    @ExcelProperty("一级分类")
    private String levelFirstTitle;//一级分类
    @ExcelProperty("二级分类")
    private String levelSecondTitle;//二级分类
}
