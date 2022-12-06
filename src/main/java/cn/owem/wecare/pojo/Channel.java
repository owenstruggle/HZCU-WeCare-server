package cn.owem.wecare.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Owem
 * @date 2022/11/25 13:43
 * @description TODO
 **/
@Data
@TableName("channel")
public class Channel {
    @TableId
    private Long channelId;
    private String userId;
    private Long channelCategoryId;
    private String channelName;
    private String channelImageSrc;
    private boolean isSwiper;
    private String channelDescription;
    private String detailSrc;
    @TableField(exist = false)
    private String channelIntroduce;
}
