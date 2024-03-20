package edu.bistu.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * 成员管理(WebMember)实体类
 *
 * @author makejava
 * @since 2024-03-20 19:09:49
 */

@Data
@TableName("web_member")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebMember {
    private Integer id;
    /**
     * 身份
     */
    private String identity;
    /**
     * 姓名
     */
    private String name;
    /**
     * 联系方式
     */
    private String contact;
    /**
     * 研究方向
     */
    private String research;
    /**
     * 科研成果
     */
    private String achievement;
    /**
     * 个人经历
     */
    private String introduction;
    /**
     * 学工号
     */
    private String studentNumber;
    /**
     * 在校生年级
     */
    private String grade;
    /**
     * 隐藏字段,分割
     */
    private String hiddenFields;
    /**
     * 照片链接
     */
    private String photoUrl;
    /**
     * 毕业去向
     */
    private String graduationDestination;
    /**
     * 毕业时间
     */
    private Date graduationTime;
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

