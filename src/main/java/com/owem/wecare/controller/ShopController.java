package com.owem.wecare.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.owem.wecare.pojo.Channel;
import com.owem.wecare.pojo.ChannelCategory;
import com.owem.wecare.service.ShopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author Owem
 * @date 2022/11/25 13:46
 * @description TODO
 **/
@RestController
public class ShopController {
    @Resource
    private ShopService shopService;

    @GetMapping("/shop/swiperdata")
    public List<Channel> getShopSwiperData() {
        return shopService.selectAllSwiperData();
    }

    @GetMapping("/shop/category")
    public List<ChannelCategory> getChannelCategoryData() {
        return shopService.selectAllChannelCategory();
    }

    @GetMapping("shop/search")
    public PageInfo searchAllChannelData(@RequestParam(value = "query") String query,
                                         @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Channel> list = shopService.searchAllChannelByName(query);
        return new PageInfo<>(list);
    }

    @GetMapping("shop/channel/{channelId}")
    public Channel selectChannel(@PathVariable Long channelId) throws IOException {
        return shopService.selectChannelById(channelId);
    }
}
