package com.owem.wecare.controller;

import com.owem.wecare.pojo.ChannelPojo;
import com.owem.wecare.service.ShopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/shop/swiperdata")
    public List<ChannelPojo> getShopSwiperData() {
        return shopService.selectAllSwiperData();
    }
}
