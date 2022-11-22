package com.owem.wecare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Owem
 * @date 2022/11/22 23:52
 * @description TODO
 **/
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "success!";
    }
}
