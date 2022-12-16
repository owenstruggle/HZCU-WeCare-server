package cn.owem.wecare.mapper;

import cn.owem.wecare.pojo.Relationship;
import cn.owem.wecare.pojo.Trace;
import cn.owem.wecare.pojo.User;
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

    @Delete("delete from relationship where (user_id = '${userId}' and accept_user_id = '${acceptUserId}' and is_accept = false) Or (user_id = '${acceptUserId}' and accept_user_id = '${userId}' and is_accept = false) ")
    Long deleteRelationship(String userId, String acceptUserId);

    @Insert("insert into relationship (user_id, accept_user_id) value ('${userId}', '${acceptUserId}')")
    Long addRelationship(String userId, String acceptUserId);

    @Select("select * from relationship where (user_id = '${userId}' and accept_user_id = '${acceptUserId}' and is_accept = false) or (user_id = '${acceptUserId}' and accept_user_id = '${userId}' and is_accept = false)")
    List<Relationship> isFriend(String userId, String acceptUserId);

    @Select("select * from relationship where (accept_user_id = '${userId}' and is_accept = false) or (user_id = '${userId}' and is_accept = false)")
    @Results({
            @Result(column = "relationship_id", property = "relationshipId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "accept_user_id", property = "acceptUserId"),
            @Result(column = "posting_image_src", property = "isAccept"),
            @Result(column = "user_id", property = "user", javaType = User.class,
                    one = @One(select = "cn.owem.wecare.mapper.UserMapper.selectById")),
            @Result(column = "accept_user_id", property = "acceptUser", javaType = User.class,
                    one = @One(select = "cn.owem.wecare.mapper.UserMapper.selectById")),
    })
    List<Relationship> selectAllApplications(String userId);

    @Update("update relationship set is_accept = true where relationship_id = ${relationshipId} and is_accept = false")
    Long updateRelationshipAccept(Long relationshipId);
}
