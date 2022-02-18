package com.sty.hello.config;

import com.sty.hello.service.PersonService;
import com.sty.hello.service.impl.PersonServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全注解的方式加载 Spring 配置
 *
 * @Configuration 注解用于定义一个配置类，相当于Spring的配置文件
 * 配置类中包含一个或多个被 @Bean 注解的方法，该方法相当于Spring配置文件中的<Bean>标签定义的组件
 */
@Configuration
public class MyAppConfig {

    /**
     * 与<bean id="personService" class="PersonServiceImpl"></bean> 等价
     * 该方法返回值以组件的形式添加到容器中
     * 方法名是组件 id(相当于<bean>标签的属性id)
     * @return
     */
    @Bean
    public PersonService personService() {
        System.out.println("在容器中添加了一个组件 :personService");
        return new PersonServiceImpl();
    }
}
