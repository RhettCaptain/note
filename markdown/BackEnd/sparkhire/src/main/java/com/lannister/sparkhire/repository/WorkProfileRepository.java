package com.lannister.sparkhire.repository;

import com.lannister.sparkhire.entity.WorkProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yang_fa-pc on 2018/8/30.
 */
@Repository
public interface WorkProfileRepository extends JpaRepository<WorkProfile, Long>{
    List<WorkProfile> findByApplicantId(Long applicantId);
}
