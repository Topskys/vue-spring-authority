package com.manong.feign;


import com.manong.feign.fallback.OssFileFeignServiceFallBack;
import com.manong.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(value = "service-oss",fallback = OssFileFeignServiceFallBack.class)//指定服务提供者的应用名称
public interface OSSFileFeignService {

    /**
     * 测试远程调用
     * @return
     */
    @GetMapping("/admin/oss/file/test")
    Result test();

    /**
     * 删除讲师头像
     * @param url
     * @return
     */
    @DeleteMapping("/admin/oss/file/delete")
    Result deleteFile(String url);

}
