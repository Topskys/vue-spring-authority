package com.manong.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manong.entity.Course;
import com.manong.vo.CourseQueryVo;
import com.manong.vo.CourseVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author KazuGin
 * @since 2021-03-02
 */
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 查询课程列表
     * @param page
     * @param courseQueryVo
     * @return
     */
    IPage<CourseVo> findCourseListByPage(@Param("page") IPage<CourseVo> page, @Param("course") CourseQueryVo courseQueryVo);

    /**
     * 查询课程发布信息
     * @param id
     * @return
     */
    CourseVo getCoursePublishInfoById(Integer id);


}
