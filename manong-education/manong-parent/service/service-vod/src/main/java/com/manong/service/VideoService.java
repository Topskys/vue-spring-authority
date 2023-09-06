package com.manong.service;

import java.io.InputStream;
import java.util.List;

public interface VideoService {

    /**
     * 上传视频
     * @param inputStream
     * @param originalFilename
     * @return
     */
    String uploadVideo(InputStream inputStream, String originalFilename);


    /**
     * 删除视频
     * @param videoId
     */
    void removeVideo(String videoId);

    /**
     * 批量删除阿里云视频信息
     * @param videoIdList
     */
    void removeVideoByIdList(List<String> videoIdList);
}
