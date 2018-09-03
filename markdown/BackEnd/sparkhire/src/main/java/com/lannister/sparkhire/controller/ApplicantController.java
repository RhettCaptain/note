package com.lannister.sparkhire.controller;

import com.lannister.sparkhire.App;
import com.lannister.sparkhire.entity.Applicant;
import com.lannister.sparkhire.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by yang_fa-pc on 2018/8/30.
 */
@Controller
@RequestMapping(value = "/applicant")
public class ApplicantController {
    @Autowired
    ApplicantService applicantService;

    //展示个人信息页面
    @RequestMapping(value="/resume")
    public String personalPageDirect(ModelMap map, HttpSession session){
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        if(applicant==null){
            map.addAttribute("applicant", new Applicant());
            return "redirect:./login";
        }
        map.addAttribute("applicant", applicant);
        return "views/applicant/applicantresume";
    }

    //个人信息页面填写提交
    @RequestMapping(value = "/resume", method = RequestMethod.POST)
    public String personInfoPost(ModelMap map, @RequestBody Applicant applicant, HttpSession session){
        Applicant applicantLogin = (Applicant) session.getAttribute("applicant");
        if(applicantLogin == null){
            map.addAttribute("applicant", new Applicant());
            return "login";
        }
        applicantLogin.setName(applicant.getName());
        applicantLogin.setBirthdate(applicant.getBirthdate());
        applicantLogin.setCity(applicant.getCity());
        applicantLogin.setEducation(applicant.getEducation());
        applicantLogin.setGender(applicant.getGender());
        applicantLogin.setIdentityid(applicant.getIdentityid());
        applicantLogin.setPhonenumber(applicant.getPhonenumber());
        applicantService.createOrUpdateApplicant(applicantLogin);
        return "views/applicant/applicantresume";
    }
}
