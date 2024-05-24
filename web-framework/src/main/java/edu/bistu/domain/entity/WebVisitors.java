package edu.bistu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 访问管理(WebVisitors)实体类
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:10:07
 */

@Data
@TableName("web_visitors")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebVisitors {
    private Integer id;
    /**
     * 访问量
     */
    private Integer visitornum;
}

