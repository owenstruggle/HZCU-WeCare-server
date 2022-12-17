package cn.owem.wecare.mapper;

import cn.owem.wecare.pojo.Posting;
import cn.owem.wecare.pojo.Trace;
import cn.owem.wecare.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Owem
 * @date 2022/12/7 01:08
 * @description TODO
 **/
@Mapper
public interface PostingMapper extends BaseMapper<Posting> {

    @Select("SELECT * FROM posting, trace WHERE posting.trace_id = trace.trace_id AND user_id = '${userId}'")
    @Results({
            @Result(column = "posting_id", property = "postingId"),
            @Result(column = "trace_id", property = "traceId"),
            @Result(column = "posting_name", property = "postingName"),
            @Result(column = "posting_image_src", property = "postingImageSrc"),
            @Result(column = "posting_media_src", property = "postingMediaSrc"),
            @Result(column = "posting_description", property = "postingDescription"),
            @Result(column = "media_type", property = "mediaType"),
            @Result(column = "trace_id", property = "trace", javaType = Trace.class,
                    one = @One(select = "cn.owem.wecare.mapper.TraceMapper.selectById")),
            @Result(column = "user_id", property = "user", javaType = User.class,
                    one = @One(select = "cn.owem.wecare.mapper.UserMapper.selectById")),
    })
    List<Posting> selectAllPostingById(String userId);

    @Select("select * from posting, trace where trace.trace_id = posting.trace_id and date(trace_time) = date(now()) and (user_id in (select accept_user_id from relationship where relationship.user_id = '${userId}' and is_accept = true) or user_id in (select user_id from relationship where accept_user_id = '${userId}' and is_accept = true) or user_id = '${userId}')")
    @Results({
            @Result(column = "posting_id", property = "postingId"),
            @Result(column = "trace_id", property = "traceId"),
            @Result(column = "posting_name", property = "postingName"),
            @Result(column = "posting_image_src", property = "postingImageSrc"),
            @Result(column = "posting_media_src", property = "postingMediaSrc"),
            @Result(column = "posting_description", property = "postingDescription"),
            @Result(column = "media_type", property = "mediaType"),
            @Result(column = "trace_id", property = "trace", javaType = Trace.class,
                    one = @One(select = "cn.owem.wecare.mapper.TraceMapper.selectById")),
            @Result(column = "user_id", property = "user", javaType = User.class,
                    one = @One(select = "cn.owem.wecare.mapper.UserMapper.selectById")),
    })
    List<Posting> selectAllTodayPostingById(String userId);

    @Insert("insert into posting(trace_id, posting_name, posting_image_src, posting_media_src, posting_description, media_type) value(#{traceId}, #{postingName}, #{postingImageSrc}, #{postingMediaSrc}, #{postingDescription}, #{mediaType})")
    Long insertPosting(Posting posting);
}
