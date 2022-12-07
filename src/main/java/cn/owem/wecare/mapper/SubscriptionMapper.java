package cn.owem.wecare.mapper;

import cn.owem.wecare.pojo.Channel;
import cn.owem.wecare.pojo.Subscription;
import cn.owem.wecare.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Owem
 * @date 2022/12/7 11:30
 * @description TODO
 **/
@Mapper
public interface SubscriptionMapper {
    @Select("select * from subscription where accept_user_id = '${userId}' or user_id = '${userId}'")
    @Results({
            @Result(column = "subscription_id", property = "subscriptionId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "accept_user_id", property = "acceptUserId"),
            @Result(column = "channel_id", property = "channelId"),
            @Result(column = "subscription_time", property = "subscriptionTime"),
            @Result(column = "is_accept", property = "isAccept"),
            @Result(column = "user_id", property = "user", javaType = User.class,
                    one = @One(select = "cn.owem.wecare.mapper.UserMapper.selectById")),
            @Result(column = "accept_user_id", property = "acceptUser", javaType = User.class,
                    one = @One(select = "cn.owem.wecare.mapper.UserMapper.selectById")),
            @Result(column = "channel_id", property = "channel", javaType = Channel.class,
                    one = @One(select = "cn.owem.wecare.mapper.ChannelMapper.selectById"))
    })
    List<Subscription> selectAllSubscriptionsById(String userId);

    @Update("update subscription set is_accept = ${isAccept} where subscription_id = ${subscriptionId}")
    Long updateSubscriptionState(Long subscriptionId, boolean isAccept);
}
