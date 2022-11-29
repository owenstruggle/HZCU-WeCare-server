package com.owem.wecare.pojo;

import lombok.Data;

/**
 * @author Owem
 * @date 2022/11/25 13:43
 * @description TODO
 **/
@Data
public class ChannelPojo {
    private Long cId;
    private String cAccount;
    private String cName;
    private String imageSrc;
    private boolean isSwiper;
    private String cDescription;
    private String cContentAddress;
}
