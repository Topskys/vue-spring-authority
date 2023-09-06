package com.manong.controller;

import com.manong.service.VideoService;
import com.manong.utils.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/vod/media")
public class VideoController {

    @Resource
    private VideoService videoService;

    /**
     * 上传视频
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result uploadVideo(MultipartFile file){
        try {
            //上传视频
            String videoId = videoService.uploadVideo(file.getInputStream(), file.getOriginalFilename());
            return Result.ok().message("视频上传成功").data("videoId",videoId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error().message("视频上传失败");
    }

    /**
     * 删除视频
     * @param videoId
     * @return
     */
    @DeleteMapping("/deleteById/{videoId}")
    public Result deleteVideoById(@PathVariable String videoId){
        try {
            videoService.removeVideo(videoId);
            return Result.ok().message("视频删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("视频删除失败");
        }
    }


    /**
     * 批量删除视频
     * @param videoIdList
     * @return
     */
    @DeleteMapping("/deleteVideoByIdList")
    public Result deleteVideoByIdList(@RequestBody List<String> videoIdList){
        try {
            //调用根据阿里云视频ID批量删除视频信息
            videoService.removeVideoByIdList(videoIdList);
            return Result.ok().message("视频删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("视频删除失败");
        }
    }

}
