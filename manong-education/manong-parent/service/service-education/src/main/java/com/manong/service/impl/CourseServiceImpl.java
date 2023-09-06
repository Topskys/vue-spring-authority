package com.manong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manong.dao.*;
import com.manong.entity.*;
import com.manong.entity.form.CourseInfoForm;
import com.manong.feign.OSSFileFeignService;
import com.manong.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.utils.Result;
import com.manong.vo.CourseQueryVo;
import com.manong.vo.CourseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseDescriptionMapper courseDescriptionMapper;

    @Resource
    private OSSFileFeignService ossFileFeignService;

    @Resource
    private CourseCollectMapper courseCollectMapper;
    @Resource
    private VideoMapper videoMapper;
    @Resource
    private ChapterMapper chapterMapper;
    @Resource
    private CommentMapper commentMapper;



    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Integer saveCourseInfo(CourseInfoForm courseInfoForm) {
        //保存课程信息
        Course course = new Course();
        course.setStatus(1);//1-未发布 2-已发布
        //利用BeanUtils工具类进行赋值
        BeanUtils.copyProperties(courseInfoForm,course);//参数1：源对象 参数2：目标对象
        //保存课程信息
        baseMapper.insert(course);

        //课程简介对象
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());//课程简介
        courseDescription.setCourseId(course.getId());//课程ID
        //保存课程简介
        courseDescriptionMapper.insert(courseDescription);
        //返回课程ID
        return course.getId();
    }

    @Override
    public CourseInfoForm getCourseInfoById(Integer id) {
        //查询课程信息
        Course course = baseMapper.selectById(id);
        //查询课程简介(根据课程ID查询课程简介信息)
        QueryWrapper<CourseDescription> queryWrapper = new QueryWrapper<CourseDescription>();
        queryWrapper.eq("courseId",id);
        CourseDescription courseDescription = courseDescriptionMapper.selectOne(queryWrapper);

        //封装课程信息表单对象
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course,courseInfoForm);
        courseInfoForm.setDescription(courseDescription.getDescription());

        return courseInfoForm;
    }

    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoForm) {
        //修改课程信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        baseMapper.updateById(course);

        //修改简介信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());//课程简介
        courseDescription.setCourseId(course.getId());//课程ID
        //保存课程简介
        QueryWrapper<CourseDescription> queryWrapper = new QueryWrapper<CourseDescription>();
        queryWrapper.eq("courseId",course.getId());
        courseDescriptionMapper.update(courseDescription,queryWrapper);
    }

    @Override
    public IPage<CourseVo> findCourseListByPage(IPage<CourseVo> page, CourseQueryVo courseQueryVo) {
        return baseMapper.findCourseListByPage(page,courseQueryVo);
    }

    /**
     * 删除课程封面
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteCoverByCourseId(Integer id) {
        //查询课程信息
        Course course = baseMapper.selectById(id);
        //判断课程是否为空
        if(course!=null){
            //获取课程封面
            String cover = course.getCover();
            //判断该课程是否存在课程封面
            if(StringUtils.isNotEmpty(cover)){
                //远程调用删除OSS文件的方法
                Result result = ossFileFeignService.deleteFile(cover);
                return result.getSuccess();
            }
        }
        return false;
    }

    /**
     * 删除课程信息
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCourseById(Integer id) {
        //收藏信息（edu_course_collect）
        QueryWrapper<CourseCollect> courseCollectQueryWrapper = new QueryWrapper<CourseCollect>();
        courseCollectQueryWrapper.eq("courseId",id);
        courseCollectMapper.delete(courseCollectQueryWrapper);
        //评论信息（edu_course_comment）
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<Comment>();
        commentQueryWrapper.eq("courseId",id);
        commentMapper.delete(commentQueryWrapper);
        //课时信息（edu_course_video）
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<Video>();
        videoQueryWrapper.eq("courseId",id);
        videoMapper.delete(videoQueryWrapper);
        //章节信息（edu_course_chapter）
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<Chapter>();
        chapterQueryWrapper.eq("courseId",id);
        chapterMapper.delete(chapterQueryWrapper);
        //章节简介（edu_course_description)
        QueryWrapper<CourseDescription> descriptionQueryWrapper = new QueryWrapper<CourseDescription>();
        descriptionQueryWrapper.eq("courseId",id);
        courseDescriptionMapper.delete(descriptionQueryWrapper);
        //课程信息（edu_course）
        return this.removeById(id);
    }

    @Override
    public CourseVo getCoursePublishInfoById(Integer id) {
        return baseMapper.getCoursePublishInfoById(id);
    }

    @Override
    public boolean publishCourseById(Integer id) {
        //创建课程对象
        Course course = new Course();
        course.setStatus(2);//2为已发布
        course.setId(id);
        return this.updateById(course);
    }
}
