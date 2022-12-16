package cn.owem.wecare.controller;

import cn.owem.wecare.pojo.*;
import cn.owem.wecare.service.MyService;
import cn.owem.wecare.utils.BusinessException;
import cn.owem.wecare.utils.UploadUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Owem
 * @date 2022/12/1 21:45
 * @description TODO
 **/
@RestController
public class MyController {
    @Resource
    private MyService myService;
    @Resource
    UploadUtil uploadUtil;

    @PutMapping("/my/agree/{relationshipId}")
    public Long updateRelationshipAccept(@PathVariable Long relationshipId) {
        return myService.updateRelationshipAccept(relationshipId);
    }

    @GetMapping("/my/application")
    public List<Relationship> selectAllApplications(@RequestParam(value = "userId") String userId) {
        return myService.selectAllApplications(userId);
    }

    @PostMapping("/my/uploadHeadPortrait")
    public String uploadHeadPortrait(@RequestParam("fileUpload") MultipartFile fileUpload, HttpServletRequest request, String userId) {
        HashMap<String, String> map = uploadUtil.uploadMedia(fileUpload, request, "user/head_portrait/");
        if (myService.uploadHeadPortrait(userId, map.get("url").split("share")[1]) == 1L) {
            return map.get("url");
        } else {
            return null;
        }
    }

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

    @GetMapping("/my/user")
    public User getUser(String userId) {
        return myService.getUser(userId);
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
    public PageInfo<Posting> selectAllPosting(String userId,
                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Posting> list = myService.selectAllPosting(userId);
        return new PageInfo<>(list);
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
