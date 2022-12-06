package cn.owem.wecare.controller;

import cn.owem.wecare.pojo.Posting;
import cn.owem.wecare.service.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Owem
 * @date 2022/12/7 02:04
 * @description TODO
 **/
@RestController
public class HomeController {
    @Resource
    HomeService homeService;

    @GetMapping("/home/posting")
    public List<Posting> selectAllTodayPosting(String userId) {
        return homeService.selectAllTodayPosting(userId);
    }
}
