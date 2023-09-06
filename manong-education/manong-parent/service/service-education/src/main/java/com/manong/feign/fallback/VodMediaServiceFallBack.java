package com.manong.feign.fallback;

import com.manong.feign.VodMediaFeignService;
import com.manong.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VodMediaServiceFallBack implements VodMediaFeignService {
    /**
     * 删除视频
     *
     * @param videoId
     * @return
     */
    @Override
    public Result deleteVideoById(String videoId) {
        log.info("触发熔断保护");
        return Result.error();
    }

    @Override
    public Result deleteVideoByIdList(List<String> videoIdList) {
        log.info("触发熔断保护");
        return Result.error();
    }
}
