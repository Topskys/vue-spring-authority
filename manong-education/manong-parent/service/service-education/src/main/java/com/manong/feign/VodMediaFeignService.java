package com.manong.feign;

import com.manong.feign.fallback.VodMediaServiceFallBack;
import com.manong.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 视频Feign远程调用接口
 */
@FeignClient(value = "service-vod",fallback = VodMediaServiceFallBack.class)
public interface VodMediaFeignService {

    /**
     * 删除视频远程调用
     * @param videoId
     * @return
     */
    @DeleteMapping("/admin/vod/media/deleteById/{videoId}")
    Result deleteVideoById(@PathVariable String videoId);

    /**
     * 批量删除视频
     * @param videoIdList
     * @return
     */
    @DeleteMapping("/admin/vod/media/deleteVideoByIdList")
    Result deleteVideoByIdList(@RequestBody List<String> videoIdList);

}
