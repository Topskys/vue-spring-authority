package com.manong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.dao.CourseMapper;
import com.manong.dao.TeacherMapper;
import com.manong.entity.Course;
import com.manong.entity.Teacher;
import com.manong.feign.OSSFileFeignService;
import com.manong.service.TeacherService;
import com.manong.utils.Result;
import com.manong.vo.TeacherQueryVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 讲师 服务实现类
 */
@Service
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    private OSSFileFeignService ossFileFeignService;

    @Resource
    private CourseMapper courseMapper;

    @Override
    public IPage<Teacher> findTeacherListByPage(IPage page, TeacherQueryVo teacherQueryVo) {
        return baseMapper.findTeacherListByPage(page,teacherQueryVo);
    }

    @Override
    public boolean deleteAvatarById(Integer id) {
        //根据ID查询讲师详情
        Teacher teacher = baseMapper.selectById(id);
        //判断对象是否为空
        if(teacher!=null){
            //判断该讲师是否存在头像
            if(StringUtils.isNotEmpty(teacher.getAvatar())){
                //远程调用删除文件的方法
                Result result = ossFileFeignService.deleteFile(teacher.getAvatar());
                return result.getSuccess();
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> selectTeacherInfoById(Integer id) {
        //获取讲师信息
        Teacher teacher = baseMapper.selectById(id);
        //创建条件构造器对象
        QueryWrapper<Course> queryWrapper = new QueryWrapper<Course>();
        queryWrapper.eq("teacherId", id);//讲师ID
        //根据讲师id获取讲师课程
        List<Course> courseList =  courseMapper.selectList(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("teacher", teacher);//讲师信息
        map.put("courseList", courseList);//该讲师的课程列表
        return map;
    }
}
