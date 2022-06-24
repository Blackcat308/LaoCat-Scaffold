package org.laocat.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @see MetaObjectHandler
 * @description: 新增 修改字段自动填充类
 * @author: LaoCat
 * @date: 2022/6/23
 */
@Configuration
public class LaoCatMetaObjectHandler implements MetaObjectHandler {

    /**
     * @description:  新增自动填充
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: void
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject,"gmtTime", Date.from(Instant.now()));
    }

    /**
     * @description: 修改自动新增
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: void
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject,"modifyTime",Date.from(Instant.now()));
    }
}
