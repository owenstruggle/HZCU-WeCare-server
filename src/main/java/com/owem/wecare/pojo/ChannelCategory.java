package com.owem.wecare.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Owem
 * @date 2022/11/29 16:47
 * @description TODO
 **/
@Data
@TableName("channel_category")
public class ChannelCategory {
    @TableId
    private Long channelCategoryId;
    private String channelCategoryName;
    private String channelCategoryImageSrc;
}
