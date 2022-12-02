package com.owem.wecare.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.owem.wecare.mapper.UserMapper;
import com.owem.wecare.pojo.User;
import com.owem.wecare.pojo.WXUserInfo;
import com.owem.wecare.utils.BusinessException;
import com.owem.wecare.utils.WeChatUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Owem
 * @date 2022/12/1 21:56
 * @description TODO
 **/
@Service
public class MyService {
    @Resource
    UserMapper userMapper;
    @Resource
    WeChatUtil weChatUtil;

    public User wxUserInfoTransform(WXUserInfo wxUserInfo) throws BusinessException {
        JSONObject sessionKeyAndOpenid = weChatUtil.getSessionKeyAndOpenid(wxUserInfo.getCode());
        String openid = sessionKeyAndOpenid.getString("openid");
        String sessionKey = sessionKeyAndOpenid.getString("session_key");

        JSONObject userInfo = JSONObject.parseObject(weChatUtil.getUserInfo(wxUserInfo.getEncryptedData(), sessionKey, wxUserInfo.getIv()));

        // 查询用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        List<User> users = userMapper.selectList(wrapper);

        // 数据库不存在该用户, 新建用户
        if (users.size() == 0) {
            User user = new User("wx_" + openid,
                                 openid,
                                 userInfo.getString("nickName"),
                                 userInfo.getString("avatarUrl"),
                                 userInfo.getBoolean("gender"),
                                 userInfo.getString("city"),
                                 userInfo.getString("province"),
                                 userInfo.getString("country"));
            if (userMapper.insert(user) == 0) {
                throw new BusinessException("新用户注册失败");
            }
            return user;
        }
        return users.get(0);
    }
}
