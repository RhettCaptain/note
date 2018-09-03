package com.lannister.sparkhire.repository;

import com.lannister.sparkhire.entity.TrainingProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yang_fa-pc on 2018/8/30.
 */
public interface TrainingProfileRepository extends JpaRepository<TrainingProfile, Long> {
    List<TrainingProfile> findByApplicantId(Long applicantId);
}
