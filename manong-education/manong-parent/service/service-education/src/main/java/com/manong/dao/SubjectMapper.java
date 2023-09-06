package com.manong.dao;

import com.manong.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manong.vo.SubjectVo;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author KazuGin
 * @since 2021-02-28
 */
public interface SubjectMapper extends BaseMapper<Subject> {

    /**
     * 根据父分类ID查询分类列表
     * @param parentId
     * @return
     */
    List<SubjectVo> selectNestedListByParentId(Integer parentId);

}
