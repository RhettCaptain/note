package com.lannister.sparkhire.repository;

import com.lannister.sparkhire.entity.LeaderProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yang_fa-pc on 2018/8/30.
 */
@Repository
public interface LeaderProfileRepository extends JpaRepository<LeaderProfile, Long>{
    List<LeaderProfile> findByApplicantId(Long applicantId);
}
