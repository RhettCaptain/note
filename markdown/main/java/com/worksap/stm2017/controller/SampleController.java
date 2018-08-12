package com.worksap.stm2017.controller;

import com.worksap.stm2017.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// import javax.security.auth.message.callback.PrivateKeyCallback.Request;


//@Controller
public class SampleController {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SampleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    @RequestMapping("/")
    @ResponseBody
    List<Person> home() {
        List<Person> personList = jdbcTemplate.query("select * from persons", BeanPropertyRowMapper.newInstance(Person.class));
        return personList;
    }
}
