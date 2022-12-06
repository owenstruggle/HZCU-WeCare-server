package cn.owem.wecare.service;

import cn.owem.wecare.mapper.PostingMapper;
import cn.owem.wecare.pojo.Posting;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Owem
 * @date 2022/12/7 02:05
 * @description TODO
 **/
@Service
public class HomeService {
    @Resource
    PostingMapper postingMapper;

    public List<Posting> selectAllTodayPosting(String user) {
        return postingMapper.selectAllTodayPostingById(user);
    }
}
