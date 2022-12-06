package cn.owem.wecare.service;

import cn.owem.wecare.mapper.ChannelCategoryMapper;
import cn.owem.wecare.mapper.ChannelMapper;
import cn.owem.wecare.pojo.Channel;
import cn.owem.wecare.pojo.ChannelCategory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Owem
 * @date 2022/11/25 13:40
 * @description TODO
 **/
@Service
public class ShopService {
    @Value("${userSetting.localSrc}")
    String localSrc;
    @Resource
    private ChannelMapper channelMapper;
    @Resource
    private ChannelCategoryMapper channelCategoryMapper;

    public List<Channel> selectAllSwiperData() {
        return channelMapper.selectSwiperChannel();
    }

    public List<ChannelCategory> selectAllChannelCategory() {
        return channelCategoryMapper.selectList(null);
    }

    public List<Channel> searchAllChannelByName(String query) {
        return channelMapper.searchChannelByName(query);
    }

    public Channel selectChannelById(Long channelId) throws IOException {
        Channel channel = channelMapper.selectById(channelId);
        if (channel.getDetailSrc() != null) {
            String path = localSrc + channel.getDetailSrc();
            String introduce = Files.readString(Paths.get(path));
            channel.setChannelIntroduce(introduce);
        }
        return channel;
    }
}
