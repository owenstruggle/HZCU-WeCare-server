package cn.owem.wecare.service;

import cn.owem.wecare.mapper.CommentMapper;
import cn.owem.wecare.mapper.PostingMapper;
import cn.owem.wecare.mapper.TraceMapper;
import cn.owem.wecare.pojo.Comment;
import cn.owem.wecare.pojo.Posting;
import cn.owem.wecare.pojo.Trace;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
    @Resource
    CommentMapper commentMapper;
    @Resource
    TraceMapper traceMapper;

    public Long createPosting(Posting posting) {
        return postingMapper.insertPosting(posting);
    }

    public Trace createTrace(Trace trace) {
        if (traceMapper.insertTrace(trace) == 1L) {
            return trace;
        }
        return null;
    }

    public List<Posting> selectAllTodayPosting(String user) {
        return postingMapper.selectAllTodayPostingById(user);
    }

    public List<Comment> selectAllCommentByPostingId(Long postingId) {
        return commentMapper.selectAllCommentByPostingId(postingId);
    }
}
