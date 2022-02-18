package com.sty.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @ResponseBody
    @RequestMapping("/test")
    public String hello() {
        return "hello Spring Boot! sty";
    }
}
