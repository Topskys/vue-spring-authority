package com.manong.controller.admin;


import com.manong.entity.Chapter;
import com.manong.service.ChapterService;
import com.manong.service.VideoService;
import com.manong.utils.Result;
import com.manong.vo.ChapterVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/admin/education/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @Resource
    private VideoService videoService;

    /**
     * 添加章节
     * @param chapter
     * @return
     */
    @PostMapping("/addChapter")
    public Result addChapter(@RequestBody Chapter chapter){
        //调用保存的方法
        if(chapterService.save(chapter)){
            return Result.ok().message("保存成功");
        }
        return Result.error().message("保存失败");
    }

    /**
     * 根据章节ID查询章节信息
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable Integer id){
        Chapter chapter = chapterService.getById(id);
        if(chapter!=null){
            return Result.ok().data("item",chapter);
        }
        return Result.error().message("数据不存在");
    }

    /**
     * 修改章节
     * @param chapter
     * @return
     */
    @PutMapping("/updateChapter")
    public Result updateChapter(@RequestBody Chapter chapter){
        //调用修改的方法
        if(chapterService.updateById(chapter)){
            return Result.ok().message("修改成功");
        }
        return Result.error().message("修改失败");
    }


    /**
     * 根据章节ID删除章节信息
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        //删除阿里云视频信息 TODO
        videoService.removeMediaVideoByChapterId(id);

        //删除章节信息的方法
        if(chapterService.deleteChapterById(id)){
            return Result.ok().message("删除成功");
        }
        return Result.error().message("删除失败");
    }


    /**
     * 查询章节列表
     * @param courseId
     * @return
     */
    @GetMapping("/nestedList/{courseId}")
    public Result nestedList(@PathVariable Integer courseId){
        //查询章节列表
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
        return Result.ok().data("items",chapterVoList);
    }
}

