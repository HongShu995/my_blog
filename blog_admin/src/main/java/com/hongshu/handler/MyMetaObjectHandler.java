package com.hongshu.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis plus自动填充
 *
 * @author Hong_Shu995
 * @date 2021-12-03
 **/
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler
{
    @Override
    public void insertFill(MetaObject metaObject)
    {
        log.info("star insert fill............");
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject)
    {
        log.info("star update fill............");
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
