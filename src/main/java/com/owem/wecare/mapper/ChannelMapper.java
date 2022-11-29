package com.owem.wecare.mapper;

import com.owem.wecare.pojo.ChannelPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Owem
 * @date 2022/11/25 13:42
 * @description TODO
 **/
@Mapper
public interface ChannelMapper {
    @Select("SELECT * FROM channel WHERE isSwiper = TRUE LIMIT 5")
    List<ChannelPojo> selectSwiperChannel();
}
