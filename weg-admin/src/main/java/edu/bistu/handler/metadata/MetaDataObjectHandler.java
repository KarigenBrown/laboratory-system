package edu.bistu.handler.metadata;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import edu.bistu.domain.entity.WebManager;
import edu.bistu.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Karigen B
 * @create 2022-11-06 17:56
 */
@Component
public class MetaDataObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Integer userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception exception) {
            exception.printStackTrace();
            userId = -1;// 表示是自己创建的
        }
        this.setFieldValByName("createTime", new Date(), metaObject)
                .setFieldValByName("createBy", userId, metaObject)
                .setFieldValByName("updateTime", new Date(), metaObject)
                .setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject)
                .setFieldValByName("updateBy", SecurityUtils.getUserId(), metaObject);
    }
}
