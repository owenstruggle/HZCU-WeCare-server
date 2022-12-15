package cn.owem.wecare.mapper;

import cn.owem.wecare.pojo.Comment;
import cn.owem.wecare.pojo.Trace;
import cn.owem.wecare.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Owem
 * @date 2022/12/16 00:18
 * @description TODO
 **/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select * from comment, posting, user, trace where comment.posting_id = posting.posting_id and comment.trace_id = trace.trace_id and trace.user_id = user.user_id and comment.posting_id = ${postingId}")
    @Results({
            @Result(column = "comment_id", property = "commentId"),
            @Result(column = "trace_id", property = "traceId"),
            @Result(column = "posting_id", property = "postingId"),
            @Result(column = "content", property = "content"),
            @Result(column = "trace_id", property = "trace", javaType = Trace.class,
                    one = @One(select = "cn.owem.wecare.mapper.TraceMapper.selectById")),
            @Result(column = "user_id", property = "user", javaType = User.class,
                    one = @One(select = "cn.owem.wecare.mapper.UserMapper.selectById")),
    })
    List<Comment> selectAllCommentByPostingId(Long postingId);
}
