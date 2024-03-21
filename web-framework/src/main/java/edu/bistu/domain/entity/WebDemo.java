package edu.bistu.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * (WebDemo)实体类
 *
 * @author makejava
 * @since 2024-03-20 19:09:24
 */

@Data
@TableName("web_demo")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebDemo {

    private Integer id;

    private String title;

    private Date time;

    private String group;

    private String introduction;
    /**
     * 换行分割
     */
    @Schema(title = "视频的链接,换行分割,或者任意分割方式,数据库统一使用字符串存储")
    private String videoUrls;
    /**
     * 换行分割
     */
    @Schema(title = "照片的链接,换行分割,或者任意分割方式,数据库统一使用字符串存储")
    private String photoUrls;

    private String location;

    private String content;
    /**
     * ,分割
     */
    @Schema(title = "关键字,分割,数据库统一使用字符串存储")
    private String keywords;
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

