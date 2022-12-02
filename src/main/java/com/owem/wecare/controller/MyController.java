package com.owem.wecare.controller;

import com.owem.wecare.pojo.User;
import com.owem.wecare.pojo.WXUserInfo;
import com.owem.wecare.service.MyService;
import com.owem.wecare.utils.BusinessException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

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
}
