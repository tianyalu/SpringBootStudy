package com.sty.hello.controller;

import com.sty.hello.bean.Article;
import com.sty.hello.bean.Person;
import com.sty.hello.bean.Person2;
import com.sty.hello.bean.PersonX;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    @Autowired
    private Person person;
    @Autowired
    private Person2 person2;
    @Autowired
    private PersonX personX;

    // http://localhost:8081/hello
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World! by 天涯路";
    }

    // http://localhost:8081/person
    @ResponseBody
    @RequestMapping("/person")
    public Person person() {
        return person;
    }

    // http://localhost:8081/person2
    @ResponseBody
    @RequestMapping("/person2")
    public Person2 person2() {
        return person2;
    }

    // http://localhost:8081/personx
    @ResponseBody
    @RequestMapping("/personx")
    public PersonX personX() {
        return personX;
    }

    // http://10.16.1.51:8084/abc/article?key=ladkfj
    @ResponseBody
    @GetMapping("/article")
    public List<Article> getArticle(@RequestParam(value = "key", defaultValue = "你好啊！") String key) {
        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            articles.add(new Article(i, (i+1) + " 坎坎坷坷扩扩坎坎坷坷扩 " + key));
        }
        return articles;
    }
}
