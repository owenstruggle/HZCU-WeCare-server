package cn.owem.wecare.mapper;

import cn.owem.wecare.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author Owem
 * @date 2022/12/2 11:22
 * @description TODO
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Update("UPDATE `user` SET nick_name = '${nickName}', gender = ${gender}, phone_number = '${phoneNumber}', city = '${city}', province = '${province}', country = '${country}' WHERE user_id = '${userId}'")
    Long updateUser(User updatedUser);
}
