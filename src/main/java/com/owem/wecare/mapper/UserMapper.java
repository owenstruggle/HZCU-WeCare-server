package com.owem.wecare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.owem.wecare.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Owem
 * @date 2022/12/2 11:22
 * @description TODO
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
