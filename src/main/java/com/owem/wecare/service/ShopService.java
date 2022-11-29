package com.owem.wecare.service;

import com.owem.wecare.mapper.ChannelMapper;
import com.owem.wecare.pojo.ChannelPojo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Owem
 * @date 2022/11/25 13:40
 * @description TODO
 **/
@Service
public class ShopService {
    @Resource
    private ChannelMapper channelMapper;

    public List<ChannelPojo> selectAllSwiperData() {
        return channelMapper.selectSwiperChannel();
    }
}
