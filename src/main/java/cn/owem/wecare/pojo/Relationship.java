package cn.owem.wecare.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Owem
 * @date 2022/12/16 08:09
 * @description TODO
 **/
@Data
public class Relationship {
    @TableId
    private Long relationshipId;
    private String userId;
    private String acceptUserId;
    private boolean isAccept;
}
