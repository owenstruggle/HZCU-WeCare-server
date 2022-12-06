package cn.owem.wecare.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * @author Owem
 * @date 2022/12/7 00:45
 * @description TODO
 **/
@Data
public class Trace {
    @TableId
    private Long traceId;
    private String userId;
    private String location;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @TableField(jdbcType = JdbcType.TIMESTAMP)
    private Date traceTime;
}
