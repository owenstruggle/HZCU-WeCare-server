package cn.owem.wecare.controller;

import cn.owem.wecare.pojo.Channel;
import cn.owem.wecare.pojo.ChannelCategory;
import cn.owem.wecare.pojo.Subscription;
import cn.owem.wecare.service.ShopService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @PostMapping("/shop/subscription")
    public Long addSubscription(@RequestBody Subscription requestBody) {
        return shopService.addSubscription(requestBody.getUserId(), requestBody.getAcceptUserId(), requestBody.getChannelId(), requestBody.getSubscriptionTime());
    }

    @GetMapping("/shop/swiperdata")
    public List<Channel> getShopSwiperData() {
        return shopService.selectAllSwiperData();
    }

    @GetMapping("/shop/category")
    public List<ChannelCategory> getChannelCategoryData() {
        return shopService.selectAllChannelCategory();
    }

    @GetMapping("shop/search")
    public PageInfo<Channel> searchAllChannelData(@RequestParam(value = "query") String query,
                                                  @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Channel> list = shopService.searchAllChannelByName(query);
        return new PageInfo<>(list);
    }

    @GetMapping("shop/channel/{channelId}")
    public Channel selectChannel(@PathVariable Long channelId) {
        return shopService.selectChannelById(channelId);
    }
}
