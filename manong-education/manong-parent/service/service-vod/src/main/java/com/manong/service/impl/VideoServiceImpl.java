package com.manong.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.manong.service.VideoService;
import com.manong.utils.AliyunVodSDKUtils;
import com.manong.utils.VodProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    @Resource
    private VodProperties vodProperties;


    /**
     * 上传视频
     *
     * @param inputStream
     * @param originalFilename
     * @return
     */
    @Override
    public String uploadVideo(InputStream inputStream, String originalFilename) {
        //获取文件名
        String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        //创建UploadStreamRequest对象
        UploadStreamRequest request = new UploadStreamRequest(vodProperties.getKeyId(), vodProperties.getKeySecret(),
                title, originalFilename, inputStream);
        //创建UploadVideoImpl对象
        UploadVideoImpl upload = new UploadVideoImpl();
        //创建UploadStreamResponse对象
        UploadStreamResponse response = upload.uploadStream(request);
        //获取视频的videoId
        String videoId = response.getVideoId();
        //判断videoId是否为空，为空表示上传失败
        if(StringUtils.isEmpty(videoId)){
            throw new RuntimeException("视频上传失败");
        }
        return videoId;
    }

    @Override
    public void removeVideo(String videoId) {
        try {
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    vodProperties.getKeyId(),
                    vodProperties.getKeySecret());

            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeVideoByIdList(List<String> videoIdList) {
        try {
            //初始化client对象
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    vodProperties.getKeyId(),
                    vodProperties.getKeySecret());
            DeleteVideoRequest request = new DeleteVideoRequest();
            //获取视频数量
            int size = videoIdList.size();//10
            StringBuffer idListStr = new StringBuffer();
            for (int i = 0; i < size; i++) {
                //获取每一个视频
                idListStr.append(videoIdList.get(i));
                //判断视频集合中的数量是否为最大数（20）
                if(i == size -1 || i % 20 == 19){
                    //支持传入多个视频ID，多个用逗号分隔，最多20个
                    request.setVideoIds(idListStr.toString());
                    client.getAcsResponse(request);
                    //删除完成后重置列表
                    idListStr = new StringBuffer();
                    //如果集合中的视频数量小于20个，则直接使用,拼接
                }else if(i % 20 < 19){
                    idListStr.append(",");
                }
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }


}
