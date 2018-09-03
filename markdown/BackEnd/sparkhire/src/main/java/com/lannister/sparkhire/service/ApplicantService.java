package com.lannister.sparkhire.service;

import com.lannister.sparkhire.entity.Applicant;

import java.util.List;

/**
 * Created by yang_fa-pc on 2018/8/29.
 */
public interface ApplicantService {
    Applicant findApplicantByUsername(String username);
    Applicant findApplicantByUsernameOrEmail(String username, String email);
    Applicant findApplicantById(Long applicantId);
    List<Applicant> findAllApplicant();
    void createOrUpdateApplicant(Applicant applicant);
}
