package com.sty.hello;

import com.sty.hello.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class HelloWorldApplicationTests {
    @Autowired
    Person person;
    //IOC容器
    @Autowired
    ApplicationContext ioc;

    @Test
    public void testHelloService() {
        //校验 IOC 容器中是否包含组件 personService
        boolean b = ioc.containsBean("personService");
        if(b) {
            System.out.println("personService 已经添加到 IOC容器中");
        }else {
            System.out.println("personService 没有添加到 IOC容器中");
        }
    }
    @Test
    void contextLoads() {
        System.out.println(person);
    }

}
