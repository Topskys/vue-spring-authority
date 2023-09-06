package com.manong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.entity.Teacher;
import com.manong.vo.TeacherQueryVo;

import java.util.Map;

/**
 * 讲师服务层接口
 */
public interface TeacherService extends IService<Teacher> {
    /**
     * 分页查询讲师信息
     * @param page
     * @param teacherQueryVo
     * @return
     */
    IPage<Teacher> findTeacherListByPage(IPage page, TeacherQueryVo teacherQueryVo);

    /**
     * 根据讲师ID删除讲师头像
     * @param id
     * @return
     */
    boolean deleteAvatarById(Integer id);

    /**
     * 根据讲师ID查询讲师详情信息（讲师信息、该讲师的课程列表）
     * @param id
     * @return
     */
    Map<String, Object> selectTeacherInfoById(Integer id);
}
