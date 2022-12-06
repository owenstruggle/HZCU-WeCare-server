package cn.owem.wecare.controller;

import cn.owem.wecare.pojo.Trace;
import cn.owem.wecare.pojo.User;
import cn.owem.wecare.service.ContactsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Owem
 * @date 2022/12/7 00:01
 * @description TODO
 **/
@RestController
public class ContactsController {
    @Resource
    ContactsService contactsService;

    @GetMapping("/contacts")
    public List<User> selectAllContacts(@RequestParam String userId) {
        return contactsService.selectAllContacts(userId);
    }

    @GetMapping("/contacts/trace")
    public List<Trace> selectAllTrace(@RequestParam String userId) {
        return contactsService.selectAllTrace(userId);
    }
}
