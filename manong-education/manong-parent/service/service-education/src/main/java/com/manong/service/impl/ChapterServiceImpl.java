package com.manong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manong.dao.VideoMapper;
import com.manong.entity.Chapter;
import com.manong.dao.ChapterMapper;
import com.manong.entity.Video;
import com.manong.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.vo.ChapterVo;
import com.manong.vo.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author KazuGin
 * @since 2021-03-03
 */
@Service
@Transactional
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Resource
    private VideoMapper videoMapper;

    @Override
    public boolean deleteChapterById(Integer id) {
        //删除edu_video课时信息
        QueryWrapper<Video> queryWrapper = new QueryWrapper<Video>();
        queryWrapper.eq("chapterId",id);//根据章节ID删除
        videoMapper.delete(queryWrapper);

        //删除章节信息
        return this.removeById(id);
    }

    @Override
    public List<ChapterVo> nestedList(Integer courseId) {
        //创建集合保存数据
        List<ChapterVo> chapterVoList = new ArrayList<ChapterVo>();
        //获取章节列表
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<Chapter>();
        chapterQueryWrapper.eq("courseId",courseId);
        chapterQueryWrapper.orderByAsc("sort","id");
        List<Chapter> chapterList = baseMapper.selectList(chapterQueryWrapper);//章节列表

        //获取课时列表
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<Video>();
        videoQueryWrapper.eq("courseId",courseId);
        videoQueryWrapper.orderByAsc("sort","id");
        List<Video> videoList = videoMapper.selectList(videoQueryWrapper);//课时列表

        //填充chapterVoList集合数据
        for (int i = 0; i < chapterList.size(); i++) {
            Chapter chapter = chapterList.get(i);//获取章节对象
            //创建ChapterVo对象
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            chapterVoList.add(chapterVo);

            //填充VideoVo数据
            List<VideoVo> videoVoList = new ArrayList<VideoVo>();
            for (int j = 0; j < videoList.size(); j++) {
                Video video = videoList.get(j);
                //判断章节ID是否与当前课时的所属章节ID一致
                if(chapter.getId() == video.getChapterId()){
                    //创建VideoVo对象
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoVoList.add(videoVo);
                }
            }

            //将课时集合添加到章节接口的children属性中
            chapterVo.setChildren(videoVoList);
        }

        return chapterVoList;
    }
}
