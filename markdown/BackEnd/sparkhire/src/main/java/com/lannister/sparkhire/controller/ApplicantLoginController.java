package com.lannister.sparkhire.controller;

import com.lannister.sparkhire.App;
import com.lannister.sparkhire.entity.Applicant;
import com.lannister.sparkhire.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by yang_fa-pc on 2018/8/29.
 */
@Controller
@RequestMapping(value = "/applicant")
public class ApplicantLoginController {

    @Autowired
    ApplicantService applicantService;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        return "views/applicant/applicantindex";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPageDirect(ModelMap map) {
        map.addAttribute("applicant", new Applicant());
        return "views/applicant/applicantlogin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(ModelMap map, @ModelAttribute @Valid Applicant applicant, BindingResult bindingResult, HttpSession session) {
        Applicant applicantValidate = applicantService.findApplicantByUsername(applicant.getUsername());
        if (applicantValidate == null) {
            map.addAttribute("msg", "user doesn't exist!");
            map.addAttribute("applicant", new Applicant());
            return "views/applicant/applicantlogin";
        } else if (!applicantValidate.getPassword().equals(applicant.getPassword())) {
            map.addAttribute("msg", "wrong password!");
            map.addAttribute("applicant", new Applicant());
            return "views/applicant/applicantlogin";
        } else {
            session.setAttribute("applicantid", applicantValidate.getId());
            session.setAttribute("applicant", applicantValidate);
            return "redirect:indexAfterLogin";
        }
    }
    @RequestMapping(value = "/indexAfterLogin",method = RequestMethod.GET)
    public String getIndexAfterLogin(ModelMap map,HttpSession session){
        Applicant applicantValidate = (Applicant) session.getAttribute("applicant");
        map.addAttribute("applicant", applicantValidate);
        map.addAttribute("msg", "success");
        return "views/applicant/applicantindexSucessLogin";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPageDirect(ModelMap map) {
        map.addAttribute("newApplicant", new Applicant());
        return "views/applicant/applicantregister";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(ModelMap map, @ModelAttribute @Valid Applicant applicant, BindingResult bindingResult) {
        Applicant applicantValidate = applicantService.findApplicantByUsernameOrEmail(applicant.getUsername(), applicant.getEmail());
        //应聘者账号已注册
        if (applicantValidate != null) {
            map.addAttribute("msg", "user has been registered!");
            map.addAttribute("newApplicant", new Applicant());
            return "views/applicant/applicantregister";
        }
        //注册应聘者账号
        else {
            applicantService.createOrUpdateApplicant(applicant);
            map.addAttribute("msg", "success");
            map.addAttribute("applicant", new Applicant());
            return "redirect:login";
        }

    }
}
