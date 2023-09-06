package com.manong.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manong.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manong.vo.TeacherQueryVo;
import org.apache.ibatis.annotations.Param;

/**
 * 讲师Mapper接口
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

    /**
     * 分页查询讲师信息
     * @param page
     * @param teacherQueryVo
     * @return
     */
    IPage<Teacher> findTeacherListByPage(@Param("page") IPage page, @Param("teacher")TeacherQueryVo teacherQueryVo);

}
