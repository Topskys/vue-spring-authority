package com.manong.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class CommonMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //参数1：元数据对象
        //参数2：属性名称
        //参数3：类对象
        //参数4：当前系统时间
        this.strictInsertFill(metaObject, "createDate", Date.class, new Date());
        this.strictUpdateFill(metaObject, "modifyDate", Date.class, new Date());
    }

    /**
     * 修改
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "modifyDate", Date.class, new Date());
    }
}