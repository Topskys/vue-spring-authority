package com.manong.controller.api;

import com.manong.service.TeacherService;
import com.manong.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/education/teacher")
public class ApiTeacherController {

    @Resource
    private TeacherService teacherService;

    /**
     * 查询所有讲师列表
     * @return
     */
    @GetMapping("/getAllTeacherList")
    public Result getAllTeacherList(){
        return Result.ok().data("items",teacherService.list());
    }

    /**
     * 查看讲师详情信息
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        //调用根据讲师ID查询讲师详情的方法
        Map<String, Object> map = teacherService.selectTeacherInfoById(id);
        return Result.ok().data(map);
    }

}
