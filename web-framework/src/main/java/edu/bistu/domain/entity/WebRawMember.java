package edu.bistu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (WebRawMember)表实体类
 *
 * @author makejava
 * @since 2024-04-14 15:09:41
 */
@Data
@TableName("web_raw_member")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebRawMember {
    //学工号
    @TableId
    private String number;
    //身份
    private String identity;

}

