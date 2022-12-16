package cn.owem.wecare.service;

import cn.owem.wecare.mapper.RelationshipMapper;
import cn.owem.wecare.mapper.TraceMapper;
import cn.owem.wecare.mapper.UserMapper;
import cn.owem.wecare.pojo.Trace;
import cn.owem.wecare.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Owem
 * @date 2022/12/7 00:03
 * @description TODO
 **/
@Service
public class ContactsService {
    @Resource
    UserMapper userMapper;
    @Resource
    TraceMapper traceMapper;
    @Resource
    RelationshipMapper relationshipMapper;

    public Long addRelationship(String userId, String phoneNumber) {
        // 查询是否存在 phoneNumber
        String acceptUserId = userMapper.selectByPhoneNumber(phoneNumber);
        if (acceptUserId != null && !acceptUserId.equals("") && !acceptUserId.equals(userId)) {
            // 判断是否为好友
            if (relationshipMapper.isFriend(userId, acceptUserId).size() == 0)
                return relationshipMapper.addRelationship(userId, acceptUserId);
        }
        return 0L;
    }

    public Long deleteRelationship(String userId, String acceptUserId) {
        return relationshipMapper.deleteRelationship(userId, acceptUserId);
    }

    public List<User> selectAllContacts(String userId) {
        return userMapper.selectAllContacts(userId);
    }

    public List<Trace> selectAllTrace(String userId) {
        return traceMapper.selectAllTrace(userId);
    }
}
