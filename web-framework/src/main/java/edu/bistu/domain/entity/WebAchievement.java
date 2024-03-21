package edu.bistu.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
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

@Schema(title = "成就实体")
@Data
@TableName("web_achievement")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebAchievement {

    @TableId(type = IdType.AUTO)
    private Integer id;
    //新闻标题
    @Schema(title = "论文标题与原achievement的name字段合并,包括其他一系列成就的名称")
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
    @Schema(title = "原article的abstract字段,与Java关键字冲突")
    @TableField("abstract")
    private String paperAbstract;
    //类别
    @Schema(title = "成就的种类,除去项目,包括论文、专利、著作、软著、技术标准、竞赛获奖")
    private String category;
    //论文首字母
    @Schema(title = "论文首字母")
    private String initials;
    //是否为实验室内部论文
    @Schema(title = "是否为实验室内部论文,0不是,1是")
    private Integer internal;
    //论文状态
    @Schema(title = "论文状态,包括草稿、已发布")
    private String articleStatus;
    //是否隐藏
    @Schema(title = "论文是否隐藏")
    private Integer hidden;
    //技术状态
    @Schema(title = "技术状态,分为申请中、已授权")
    private String techniqueStatus;
    //类别
    @Schema(title = "原成就的类别")
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

