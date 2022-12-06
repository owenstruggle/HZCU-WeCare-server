package cn.owem.wecare.pojo;

import lombok.Data;

/**
 * @author Owem
 * @date 2022/12/1 21:48
 * @description TODO
 **/
@Data
public class WXUserInfo {
    private String code;
    private String encryptedData;
    private String iv;
    private String rawData;
    private String signature;
}
