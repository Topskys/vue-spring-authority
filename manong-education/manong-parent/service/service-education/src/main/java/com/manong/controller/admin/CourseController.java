package com.manong.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manong.entity.form.CourseInfoForm;
import com.manong.service.CourseService;
import com.manong.service.VideoService;
import com.manong.utils.Result;
import com.manong.vo.CourseQueryVo;
import com.manong.vo.CourseVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/education/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private VideoService videoService;

    /**
     * 添加课程信息
     * @param courseInfoForm
     * @return
     */
    @PostMapping("/saveCourseInfo")
    public Result saveCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        //调用新增课程信息的方法
        Integer courseId = courseService.saveCourseInfo(courseInfoForm);
        return Result.ok().data("courseId",courseId).message("保存成功");
    }

    /**
     * 查询课程详情
     * @param id
     * @return
     */
    @GetMapping("/getCourseInfoById/{id}")
    public Result getCourseInfoById(@PathVariable Integer id){
        //调用查询课程信息的方法
        CourseInfoForm courseInfoForm = courseService.getCourseInfoById(id);
        //判断对象是否为空
        if(courseInfoForm!=null){
            return Result.ok().data("item",courseInfoForm);
        }
        return Result.ok().message("数据不存在");
    }

    /**
     * 修改课程信息
     * @param courseInfoForm
     * @return
     */
    @PutMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        //调用修改课程信息的方法
        courseService.updateCourseInfo(courseInfoForm);
        //返回数据
        return Result.ok().message("修改成功");
    }

    /**
     * 查询
     * @param page
     * @param limit
     * @param courseQueryVo
     * @return
     */
    @GetMapping("/list/{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit, CourseQueryVo courseQueryVo){
        //创建分页对象
        IPage<CourseVo> courseVoIPage = new Page<CourseVo>(page,limit);
        //调用分页查询的方法
        courseService.findCourseListByPage(courseVoIPage,courseQueryVo);
        //返回数据
        return Result.ok().data("total",courseVoIPage.getTotal()).data("items",courseVoIPage.getRecords());
    }

    /**
     * 删除课程
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        //删除课程视频 TODO
        videoService.removeMediaVideoByCourseId(id);
        // 删除课程封面
        courseService.deleteCoverByCourseId(id);
        //删除课程
        if(courseService.deleteCourseById(id)){
            return Result.ok().message("删除成功");
        }else{
            return Result.error().message("删除失败");
        }
    }


    /**
     * 查询课程发布信息
     * @param id
     * @return
     */
    @GetMapping("/getCoursePublishInfoById/{id}")
    public Result getCoursePublishInfoById(@PathVariable Integer id){
        //调用根据课程ID查询课程信息的方法
        CourseVo courseVo = courseService.getCoursePublishInfoById(id);
        //判断对象是否为空
        if(courseVo!=null){
            return Result.ok().data("item",courseVo);
        }
        return Result.error().message("数据不存在");
    }

    /**
     * 根据课程ID发布课程信息
     * @param id
     * @return
     */
    @PutMapping("/publishCourse/{id}")
    public Result publishCourseById(@PathVariable Integer id){
        if(courseService.publishCourseById(id)){
            return Result.ok().message("课程发布成功");
        }
        return Result.error().message("课程发布失败");
    }
}

