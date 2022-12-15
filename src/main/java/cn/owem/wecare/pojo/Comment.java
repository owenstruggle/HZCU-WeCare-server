package cn.owem.wecare.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Owem
 * @date 2022/12/16 00:15
 * @description TODO
 **/
@Data
public class Comment {
    @TableId
    private Long commentId;
    private Long traceId;
    @TableField(exist = false)
    private Trace trace;
    @TableField(exist = false)
    private User user;
    private Long postingId;
    private String content;
}
