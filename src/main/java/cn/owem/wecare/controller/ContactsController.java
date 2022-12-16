package cn.owem.wecare.controller;

import cn.owem.wecare.pojo.Trace;
import cn.owem.wecare.pojo.User;
import cn.owem.wecare.service.ContactsService;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/contacts")
    public Long addRelationship(String userId, String phoneNumber) {
        return contactsService.addRelationship(userId, phoneNumber);
    }

    @DeleteMapping("/contacts")
    public Long deleteRelationship(String userId, String acceptUserId) {
        return contactsService.deleteRelationship(userId, acceptUserId);
    }

    @GetMapping("/contacts")
    public List<User> selectAllContacts(@RequestParam String userId) {
        return contactsService.selectAllContacts(userId);
    }

    @GetMapping("/contacts/trace")
    public List<Trace> selectAllTrace(@RequestParam String userId) {
        return contactsService.selectAllTrace(userId);
    }
}
