package cn.owem.wecare.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Owem
 * @date 2022/12/7 00:55
 * @description TODO
 **/
@Data
public class Posting {
    @TableId
    private Long postingId;
    private Long traceId;
    @TableField(exist = false)
    private Trace trace;
    private String postingName;
    private String postingImageSrc;
    private String postingDescription;
    private String additionalContent;
    @TableField(exist = false)
    private User user;
}
