package com.manong.controller.admin;


import com.manong.entity.Video;
import com.manong.service.VideoService;
import com.manong.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/education/video")
public class VideoController {
    @Resource
    private VideoService videoService;

    /**
     * 添加课时
     * @param video
     * @return
     */
    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody Video video){
        //调用保存的方法
        if(videoService.save(video)){
            return Result.ok().message("保存成功");
        }
        return Result.error().message("保存失败");
    }

    /**
     * 根据课时ID查询课时信息
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable Integer id){
        Video video = videoService.getById(id);
        if(video!=null){
            return Result.ok().data("item",video);
        }
        return Result.error().message("数据不存在");
    }

    /**
     * 修改课时
     * @param video
     * @return
     */
    @PutMapping("/updateVideo")
    public Result updateVideo(@RequestBody Video video){
        //调用修改的方法
        if(videoService.updateById(video)){
            return Result.ok().message("修改成功");
        }
        return Result.error().message("修改失败");
    }


    /**
     * 根据课时ID删除课时信息
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        //删除阿里云视频信息 TODO
        videoService.removeMediaVideoByVideoId(id.toString());
        if(videoService.removeById(id)){
            return Result.ok().message("删除成功");
        }
        return Result.error().message("删除失败");
    }
}

