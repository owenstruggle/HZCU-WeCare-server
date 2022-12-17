package cn.owem.wecare.service;

import cn.owem.wecare.mapper.PostingMapper;
import cn.owem.wecare.mapper.RelationshipMapper;
import cn.owem.wecare.mapper.SubscriptionMapper;
import cn.owem.wecare.mapper.UserMapper;
import cn.owem.wecare.pojo.*;
import cn.owem.wecare.utils.BusinessException;
import cn.owem.wecare.utils.WeChatUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    PostingMapper postingMapper;
    @Resource
    SubscriptionMapper subscriptionMapper;
    @Resource
    WeChatUtil weChatUtil;
    @Resource
    RelationshipMapper relationshipMapper;

    public Long updateRelationshipAccept(Long relationshipId) {
        return relationshipMapper.updateRelationshipAccept(relationshipId);
    }

    public List<Relationship> selectAllApplications(String userId) {
        return relationshipMapper.selectAllApplications(userId);
    }

    public User getUser(String userId) {
        return userMapper.selectById(userId);
    }

    public Long uploadHeadPortrait(String userId, String avatarUrl) {
        return userMapper.uploadHeadPortrait(userId, avatarUrl);
    }

    public Long updateSubscriptionState(Long subscriptionId, boolean isAccept) {
        return subscriptionMapper.updateSubscriptionState(subscriptionId, isAccept);
    }

    public List<Subscription> selectAllSubscription(String userId) {
        return subscriptionMapper.selectAllSubscriptionsById(userId);
    }

    public List<Posting> selectAllPosting(String userId) {
        return postingMapper.selectAllPostingById(userId);
    }

    public User updateUser(User updateUser) throws BusinessException {
        if (updateUser.getUserId().equals("") || userMapper.selectCount(new QueryWrapper<User>().eq("user_id", updateUser.getUserId())) == 0L) {
            throw new BusinessException("传入数据有误或数据不存在!");
        }
        if (userMapper.updateUser(updateUser) == 1L) {
            return userMapper.selectOne(new QueryWrapper<User>().eq("user_id", updateUser.getUserId()));
        } else {
            throw new BusinessException("更新失败!");
        }
    }

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
                                 "/user/head_portrait/default.png",  // userInfo.getString("avatarUrl"),
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
