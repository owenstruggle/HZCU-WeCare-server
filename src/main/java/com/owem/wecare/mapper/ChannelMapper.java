package com.owem.wecare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.owem.wecare.pojo.Channel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Owem
 * @date 2022/11/25 13:42
 * @description TODO
 **/
@Mapper
public interface ChannelMapper extends BaseMapper<Channel> {
    @Select("SELECT * FROM channel WHERE is_swiper = TRUE LIMIT 5")
    List<Channel> selectSwiperChannel();

    @Select("SELECT * FROM channel, channel_category WHERE channel.channel_category_id = channel_category.channel_category_id AND (channel_name LIKE '%${query}%' OR channel_category_name = '${query}')")
    List<Channel> searchChannelByName(String query);
}
