package com.manong.service;

import com.manong.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author KazuGin
 * @since 2021-03-03
 */
public interface VideoService extends IService<Video> {
    /**
     * 根据视频ID删除阿里云的视频
     * @param videoId
     */
    void removeMediaVideoByVideoId(String videoId);

    /**
     * 删除章节Chapter的同时删除视频
     * 根据章节ID删除该章节下对应的课时及阿里云的视频
     * @param chapterId
     */
    void removeMediaVideoByChapterId(Integer chapterId);

    /**
     * 根据课程ID删除对应的视频信息
     * @param courseId
     */
    void removeMediaVideoByCourseId(Integer courseId);
}
