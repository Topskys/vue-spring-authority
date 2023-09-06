package com.manong.controller;

import com.manong.service.FileService;
import com.manong.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/admin/oss/file")
public class OSSController {

    @Resource
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     * @param module
     * @return
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file,String module){
        //返回上传到oss的路径
        String url = fileService.upload(file,module);
        return Result.ok().message("文件上传成功").data("url",url);
    }

    /**
     * 测试远程调用
     * @return
     */
    @GetMapping("/test")
    public Result test(){
        log.info("测试OpenFeign的使用");
        try {
            //TimeUnit.SECONDS.sleep(3);
            //使用线程模拟业务处理超时时间
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok().message("测试OpenFeign的使用");
    }

    /**
     * 删除讲师头像
     * @param url
     * @return
     */
    @DeleteMapping("/delete")
    public Result deleteFile(@RequestBody String url){
        try {
            //调用删除讲师头像的方法
            fileService.deleteFile(url);
            return Result.ok().message("文件删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error().message("文件删除失败");
    }

}
