package com.manong.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manong.entity.Teacher;
import com.manong.feign.OSSFileFeignService;
import com.manong.service.TeacherService;
import com.manong.utils.Result;
import com.manong.vo.TeacherQueryVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 讲师控制器类
 */
@RestController
@RequestMapping("/admin/education/teacher")
public class TeacherController {

    //注入TeacherService
    @Resource
    private TeacherService teacherService;

    @Resource
    private OSSFileFeignService ossFileFeignService;

    /**
     * 查询所有讲师列表
     * @return
     */
    @GetMapping("/list")
    public Result list(){
        return Result.ok().data("items",teacherService.list());
    }

    /**
     * 删除讲师
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        //调用删除讲师头像
        teacherService.deleteAvatarById(id);
        //删除讲师信息
        if(teacherService.removeById(id)){
            return Result.ok().message("删除成功");
        }
        return Result.error().message("删除失败");
    }

    /**
     * 新增讲师
     * @param teacher
     * @return
     */
    @PostMapping("/save")
    public Result add(@RequestBody Teacher teacher){
        //调用方法
        if(teacherService.save(teacher)){
            return Result.ok().message("添加成功");
        }
        return Result.error().message("添加失败");
    }

    /**
     * 修改讲师
     * @param teacher
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Teacher teacher){
        //调用方法
        if(teacherService.updateById(teacher)){
            return Result.ok().message("修改成功");
        }
        return Result.error().message("修改失败");
    }

    /**
     * 查询讲师详情
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result findById(@PathVariable Integer id){
        //调用根据主键查询的方法
        Teacher teacher = teacherService.getById(id);
        //判断对象是否为空
        if(teacher!=null){
            return Result.ok().data("item",teacher);
        }
        return Result.error().message("数据不存在");
    }


    /**
     * 分页查询
     * @param page
     * @param teacherQueryVo
     * @return
     */
    @GetMapping("/list/{page}/{limit}")
    public Result listPage(@PathVariable Integer page, @PathVariable Integer limit, TeacherQueryVo teacherQueryVo){
        //创建分页对象
        IPage<Teacher> pageModel = new Page<Teacher>(page,limit);
        //调用方法
        teacherService.findTeacherListByPage(pageModel,teacherQueryVo);
        //返回数据
        return Result.ok().data("total",pageModel.getTotal()).data("items",pageModel.getRecords());
    }

    /**
     * 批量删除
     * @param idList
     * @return
     */
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Integer> idList){
        //删除头像
        for (Integer id : idList) {
            teacherService.deleteAvatarById(id);
        }
        //调用批量删除的方法
        if(teacherService.removeByIds(idList)){
            return Result.ok().message("删除成功");
        }
        return Result.error().message("删除失败");
    }

    /**
     * 测试远程调用
     * @return
     */
    @GetMapping("/test")
    public Result test(){
        //远程调用OSS服务中test()方法
        ossFileFeignService.test();
        return Result.ok();
    }

    /**
     * 测试Sentinel的使用
     * @return
     */
    @GetMapping("/test1")
    public String test1(){
        return "测试Sentinel的使用-test1()方法";
    }

    /**
     * 测试Sentinel的使用
     * @return
     */
    @GetMapping("/test2")
    public String tes2(){
        return "测试Sentinel的使用-test2()方法";
    }
}

