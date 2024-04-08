package edu.bistu.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * (WebProject)实体类
 *
 * @author makejava
 * @since 2024-03-20 19:09:56
 */
@Schema(title = "项目实体")
@Data
@TableName("web_project")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebProject {
    private Integer id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目链接
     */
    private String link;
    /**
     * 项目年份
     */
    private String theyear;

    private String content;
    /**
     * 项目状态
     */
    @Schema(title = "项目状态,分为在研、结题")
    private Integer status;
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

