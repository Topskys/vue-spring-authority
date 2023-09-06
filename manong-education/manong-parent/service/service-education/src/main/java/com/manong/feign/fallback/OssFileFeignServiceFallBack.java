package com.manong.feign.fallback;

import com.manong.feign.OSSFileFeignService;
import com.manong.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OssFileFeignServiceFallBack implements OSSFileFeignService {
    /**
     * 测试远程调用
     *
     * @return
     */
    @Override
    public Result test() {
        log.error("远程调用出错，开启熔断保护");
        return Result.error().message("远程调用出错，开启熔断保护");
    }

    /**
     * 删除讲师头像
     *
     * @param url
     * @return
     */
    @Override
    public Result deleteFile(String url) {
        log.error("远程调用出错，开启熔断保护");
        return Result.error().message("远程调用出错，开启熔断保护");
    }
}
