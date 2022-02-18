package com.sty.hello.service.impl;

import com.sty.hello.bean.Person;
import com.sty.hello.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonServiceImpl implements PersonService {
    @Autowired
    private Person person;

    @Override
    public Person getPersonInfo() {
        return person;
    }
}
