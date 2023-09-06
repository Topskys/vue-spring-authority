package com.manong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.entity.Subject;
import com.manong.vo.SubjectVo;

import java.io.InputStream;
import java.util.List;


public interface SubjectService extends IService<Subject> {

    /**
     * 批量导入课程分类
     * @param inputStream
     */
    void batchImport(InputStream inputStream);

    /**
     * 根据父分类ID查询分类列表
     * @param parentId
     * @return
     */
    List<SubjectVo> selectNestedListByParentId(Integer parentId);
}
