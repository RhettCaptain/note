package com.worksap.stm2017.controller;

import com.worksap.stm2017.model.Person;
import com.worksap.stm2017.model.Person2;

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

import javax.security.auth.message.callback.PrivateKeyCallback.Request;


@Controller
public class SampleController {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SampleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping("/hello")
    public ModelAndView greeting() {
    //	model.addAttribute("xname", name);
    	System.out.println("hello teset");
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("index.html");
    	return mv;
    }
    
    @RequestMapping("/love")
    @ResponseBody
    public String love() {
    //	model.addAttribute("xname", name);
    	return "love";
    }
    
    @RequestMapping("/hate")
    @ResponseBody
    public void hate(@RequestBody Person p) {
    //	model.addAttribute("xname", name);
    	System.out.println("________________");
    	System.out.println(p.getCity() + p.getPersonId());
    	
    }
    
    @RequestMapping("/")
    @ResponseBody
    List<Person> home() {
        List<Person> personList = jdbcTemplate.query("select * from persons", BeanPropertyRowMapper.newInstance(Person.class));
        return personList;
    }
}
