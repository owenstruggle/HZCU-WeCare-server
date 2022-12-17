package cn.owem.wecare.mapper;

import cn.owem.wecare.pojo.Trace;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Owem
 * @date 2022/12/7 00:50
 * @description TODO
 **/
@Mapper
public interface TraceMapper extends BaseMapper<Trace> {
    @Select("SELECT * FROM trace WHERE user_id = '${userId}'")
    List<Trace> selectAllTrace(String userId);

    @Options(useGeneratedKeys = true, keyColumn = "trace_id", keyProperty = "traceId")
    @Insert("insert into trace(user_id, trace_time, longitude, latitude) values(#{userId}, now(), #{longitude}, #{latitude})")
    Long insertTrace(Trace trace);
}
