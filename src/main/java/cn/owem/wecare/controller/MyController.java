package cn.owem.wecare.controller;

import cn.owem.wecare.pojo.Posting;
import cn.owem.wecare.pojo.Subscription;
import cn.owem.wecare.pojo.User;
import cn.owem.wecare.pojo.WXUserInfo;
import cn.owem.wecare.service.MyService;
import cn.owem.wecare.utils.BusinessException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Owem
 * @date 2022/12/1 21:45
 * @description TODO
 **/
@RestController
public class MyController {
    @Resource
    private MyService myService;

    @PostMapping("/my/user/wxlogin")
    public User wxLogin(@RequestBody WXUserInfo wxUserInfo) {
        User user = null;
        try {
            user = myService.wxUserInfoTransform(wxUserInfo);
        } catch (BusinessException e) {
            System.out.println(e.getDefaultMessage());
        }
        return user;
    }

    @PutMapping("/my/user")
    public User updateUser(@RequestBody User updateUser) {
        User user = null;
        try {
            user = myService.updateUser(updateUser);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return user;
    }

    @GetMapping("/my/posting")
    public List<Posting> selectAllPosting(String userId) {
        return myService.selectAllPosting(userId);
    }

    @GetMapping("/my/subscription")
    public List<Subscription> selectAllSubscription(String userId) {
        return myService.selectAllSubscription(userId);
    }

    @PutMapping("/my/subscription/state")
    public Long updateSubscriptionState(@RequestParam("subscriptionId") Long subscriptionId, @RequestParam("isAccept") Boolean isAccept) {
        return myService.updateSubscriptionState(subscriptionId, isAccept);
    }
}
