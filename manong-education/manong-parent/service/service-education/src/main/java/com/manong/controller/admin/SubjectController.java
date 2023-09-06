package com.manong.controller.admin;


import com.manong.service.SubjectService;
import com.manong.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;


@RestController
@RequestMapping("/admin/education/subject")
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    /**
     * 批量导入
     * @param file
     * @return
     */
    @PostMapping("/batchImport")
    public Result batchImport(MultipartFile file){
        try {
            //调用批量导入方法
            subjectService.batchImport(file.getInputStream());
            return Result.ok().message("导入成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error().message("导入失败");
    }

    /**
     * 查询分类树形节点列表
     * @return
     */
    @GetMapping("/nestedList")
    public Result nestedList(){
        return Result.ok().data("items",subjectService.selectNestedListByParentId(0));
    }

}

