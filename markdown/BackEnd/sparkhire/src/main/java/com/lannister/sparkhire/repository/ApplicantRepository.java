package com.lannister.sparkhire.repository;

import com.lannister.sparkhire.entity.Applicant;
import com.lannister.sparkhire.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by yang_fa-pc on 2018/8/29.
 */
@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Applicant findApplicantByUsername(String username);
    Applicant findApplicantByUsernameOrEmail(String username, String email);
}
