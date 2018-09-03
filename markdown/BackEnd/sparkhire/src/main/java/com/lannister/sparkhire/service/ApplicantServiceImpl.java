package com.lannister.sparkhire.service;

import com.lannister.sparkhire.entity.Applicant;
import com.lannister.sparkhire.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yang_fa-pc on 2018/8/29.
 */
@Service
public class ApplicantServiceImpl implements ApplicantService{
    @Autowired
    ApplicantRepository applicantRepository;

    @Override
    public Applicant findApplicantByUsername(String username){
        return applicantRepository.findApplicantByUsername(username);
    }

    @Override
    public Applicant findApplicantByUsernameOrEmail(String username, String email){
        return applicantRepository.findApplicantByUsernameOrEmail(username, email);
    }

    @Override
    public Applicant findApplicantById(Long applicantId){
        return applicantRepository.findOne(applicantId);
    }
    @Override
    public List<Applicant> findAllApplicant(){
        return applicantRepository.findAll();
    }

    @Override
    public void createOrUpdateApplicant(Applicant applicant){
        applicantRepository.save(applicant);
    }

}
