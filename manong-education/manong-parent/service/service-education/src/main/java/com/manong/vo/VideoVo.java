package com.manong.vo;

import lombok.Data;

@Data
public class VideoVo {
    private Integer id;
    private String title;
    private Boolean isFree;//1-免费  0 -收费
    private Integer sort;
    //阿里云视频地址
    private String videoSourceId;
}

