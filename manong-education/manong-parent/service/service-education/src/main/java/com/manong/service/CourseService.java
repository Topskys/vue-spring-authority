package com.manong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.entity.Course;
import com.manong.entity.form.CourseInfoForm;
import com.manong.vo.CourseQueryVo;
import com.manong.vo.CourseVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author KazuGin
 * @since 2021-03-02
 */
public interface CourseService extends IService<Course> {

    /**
     * 添加课程信息
     * @param courseInfoForm
     * @return
     */
    Integer saveCourseInfo(CourseInfoForm courseInfoForm);

    /**
     * 查询课程详情
     * @param id
     * @return
     */
    CourseInfoForm getCourseInfoById(Integer id);

    /**
     * 修改课程信息
     * @param courseInfoForm
     */
    void updateCourseInfo(CourseInfoForm courseInfoForm);

    /**
     * 查询课程列表
     * @param page
     * @param courseQueryVo
     * @return
     */
    IPage<CourseVo> findCourseListByPage(IPage<CourseVo> page,  CourseQueryVo courseQueryVo);

    /**
     * 删除课程封面
     * @param id
     * @return
     */
    boolean deleteCoverByCourseId(Integer id);

    /**
     * 删除课程信息
     * @param id
     * @return
     */
    boolean deleteCourseById(Integer id);

    /**
     * 查询课程发布信息
     * @param id
     * @return
     */
    CourseVo getCoursePublishInfoById(Integer id);

    /**
     * 根据课程ID发布课程信息
     * @param id
     * @return
     */
    boolean publishCourseById(Integer id);
}
