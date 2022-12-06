package cn.owem.wecare.service;

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

    public List<User> selectAllContacts(String userId) {
        return userMapper.selectAllContacts(userId);
    }

    public List<Trace> selectAllTrace(String userId) {
        return traceMapper.selectAllTrace(userId);
    }
}
