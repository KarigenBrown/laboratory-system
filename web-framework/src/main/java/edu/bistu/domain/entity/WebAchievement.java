package edu.bistu.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 论文管理(WebAchievement)表实体类
 *
 * @author makejava
 * @since 2024-03-20 19:08:54
 */

@Data
@TableName("web_achievement")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebAchievement {

    @TableId(type = IdType.AUTO)
    private Integer id;
    //新闻标题
    private String title;
    //期刊
    private String journal;
    //第一作者
    private String author;
    //其他作者
    private String authors;
    //时间
    private String date;

    private String link;

    private String papercode;
    //论文年份
    private String theyear;
    //摘要
    @TableField("abstract")
    private String paperAbstract;
    //类别
    private String category;
    //论文首字母
    private String initials;
    //是否为实验室内部论文
    private Integer internal;
    //论文状态
    private String articleStatus;
    //是否隐藏
    private Integer hidden;
    //技术状态
    private String techniqueStatus;
    //类别
    private String achievementCategory;
    //地址
    private String address;
    //逻辑删除标志(0代表未删除,1代表已删除)
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

