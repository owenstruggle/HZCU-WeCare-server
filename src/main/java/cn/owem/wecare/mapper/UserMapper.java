package cn.owem.wecare.mapper;

import cn.owem.wecare.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Owem
 * @date 2022/12/2 11:22
 * @description TODO
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Update("UPDATE `user` SET nick_name = '${nickName}', gender = ${gender}, phone_number = '${phoneNumber}', city = '${city}', province = '${province}', country = '${country}' WHERE user_id = '${userId}'")
    Long updateUser(User updatedUser);

    @Select("SELECT * FROM `user` WHERE user_id IN (SELECT accept_user_id FROM relationship WHERE user_id = '${userId}') OR user_id IN (SELECT user_id FROM relationship WHERE accept_user_id = '${userId}')")
    List<User> selectAllContacts(String userId);

    @Update("update user set avatar_url = '${avatarUrl}' where user_id = '${userId}'")
    Long uploadHeadPortrait(String userId, String avatarUrl);
}
