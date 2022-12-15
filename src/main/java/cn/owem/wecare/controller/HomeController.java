package cn.owem.wecare.controller;

import cn.owem.wecare.pojo.Comment;
import cn.owem.wecare.pojo.Posting;
import cn.owem.wecare.service.HomeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/home/todayposting")
    public PageInfo<Posting> selectAllTodayPosting(@RequestParam(value = "userId") String userId,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Posting> list = homeService.selectAllTodayPosting(userId);
        return new PageInfo<>(list);
    }

    @GetMapping("/home/comment/{postingId}")
    public List<Comment> selectAllCommentByPostingId(@PathVariable Long postingId) {
        return homeService.selectAllCommentByPostingId(postingId);
    }
}
