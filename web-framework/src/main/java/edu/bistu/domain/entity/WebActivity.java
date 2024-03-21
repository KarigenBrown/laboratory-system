package edu.bistu.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * (WebActivity)实体类
 *
 * @author makejava
 * @since 2024-03-20 19:09:12
 */

@Data
@TableName("web_activity")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebActivity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;
    /**
     * 换行分割
     */
    @Schema(title = "照片的链接,换行分割,或者任意分割方式,数据库统一使用字符串存储")
    private String urls;

    private String introduction;

    @Schema(title = "保留字段")
    private String content;
    /**
     * 逻辑删除标志(0代表未删除,1代表已删除)
     */
    @TableLogic
    private Integer deleted;
    @TableField(fill = FieldFill.INSERT)
    private Integer createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}

