package edu.bistu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (WebLog)实体类
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:09:33
 */

@Data
@TableName("web_log")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebLog {

    private Integer id;

    private Date createTime;

    private Integer userid;

    private String log;
}

