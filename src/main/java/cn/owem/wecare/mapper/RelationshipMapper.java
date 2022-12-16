package cn.owem.wecare.mapper;

import cn.owem.wecare.pojo.Relationship;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Owem
 * @date 2022/12/16 08:11
 * @description TODO
 **/
@Mapper
public interface RelationshipMapper extends BaseMapper<Relationship> {

    @Delete("delete from relationship where (user_id = '${userId}' and accept_user_id = '${acceptUserId}') Or (user_id = '${acceptUserId}' and accept_user_id = '${userId}') ")
    Long deleteRelationship(String userId, String acceptUserId);

    @Insert("insert into relationship (user_id, accept_user_id) value ('${userId}', '${acceptUserId}')")
    Long addRelationship(String userId, String acceptUserId);

    @Select("select * from relationship where (user_id = '${userId}' and accept_user_id = '${acceptUserId}') or (user_id = '${acceptUserId}' and accept_user_id = '${userId}')")
    List<Relationship> isFriend(String userId, String acceptUserId);
}
