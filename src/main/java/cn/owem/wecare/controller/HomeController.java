package cn.owem.wecare.controller;

import cn.owem.wecare.pojo.Comment;
import cn.owem.wecare.pojo.Posting;
import cn.owem.wecare.pojo.Trace;
import cn.owem.wecare.service.HomeService;
import cn.owem.wecare.utils.UploadUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    @Resource
    UploadUtil uploadUtil;

    @PostMapping("/home/comment")
    public Long createComment(@RequestBody Comment comment) {
        return homeService.createComment(comment);
    }

    @PostMapping("/home/posting")
    public Long createPosting(@RequestBody Posting posting) {
        return homeService.createPosting(posting);
    }

    @PostMapping("/home/uploadPostingMedia")
    public String uploadHeadPortrait(@RequestParam("fileUpload") MultipartFile fileUpload, HttpServletRequest request) {
        HashMap<String, String> map = uploadUtil.uploadMedia(fileUpload, request, "posting/media/");
        return map.get("url");
    }

    @PostMapping("/home/trace")
    public Trace createTrace(@RequestBody Trace trace) {
        return homeService.createTrace(trace);
    }

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
