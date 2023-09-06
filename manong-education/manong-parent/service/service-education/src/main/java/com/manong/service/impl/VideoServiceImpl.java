package com.manong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.dao.VideoMapper;
import com.manong.entity.Video;
import com.manong.feign.VodMediaFeignService;
import com.manong.service.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author KazuGin
 * @since 2021-03-03
 */
@Service
@Transactional
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Resource
    private VodMediaFeignService vodMediaFeignService;

    @Override
    public void removeMediaVideoByVideoId(String videoId) {
        //根据视频ID找到阿里云视频的videoSourceId
        Video video = baseMapper.selectById(videoId);
        //获取阿里云视频的videoSourceId
        String videoSourceId = video.getVideoSourceId();
        //远程调用删除阿里云视频的删除视频方法
        vodMediaFeignService.deleteVideoById(videoSourceId);
    }

    @Override
    public void removeMediaVideoByChapterId(Integer chapterId) {
        //创建条件构造器对象
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("videoSourceId");//阿里云视频ID
        queryWrapper.eq("chapterId", chapterId);//章节ID
        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        List<String> videoSourceIdList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            //获取每个课时对应的阿里云视频ID
            String videoSourceId = (String) map.get("videoSourceId");
            //将阿里云视频ID添加到集合中
            videoSourceIdList.add(videoSourceId);
        }
        //远程调用批量删除的方法
        vodMediaFeignService.deleteVideoByIdList(videoSourceIdList);
    }

    @Override
    public void removeMediaVideoByCourseId(Integer courseId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("videoSourceId");//阿里云视频ID
        queryWrapper.eq("courseId", courseId);//课程ID
        //查询数据列表
        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        //查询视频列表
        List<String> videoSourceIdList = this.getVideoSourceIdList(maps);
        //远程调用批量删除的方法
        vodMediaFeignService.deleteVideoByIdList(videoSourceIdList);
    }

    /**
     * 获取阿里云视频id列表
     */
    private List<String> getVideoSourceIdList(List<Map<String, Object>> maps) {
        List<String> videoSourceIdList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            //获取每个视频的视频ID
            String videoSourceId = (String) map.get("videoSourceId");
            videoSourceIdList.add(videoSourceId);
        }
        return videoSourceIdList;
    }
}
