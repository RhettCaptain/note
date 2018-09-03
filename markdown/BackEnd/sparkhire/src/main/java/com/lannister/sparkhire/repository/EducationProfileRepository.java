package com.lannister.sparkhire.repository;

import com.lannister.sparkhire.entity.EducationProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yang_fa-pc on 2018/8/30.
 */
public interface EducationProfileRepository extends JpaRepository<EducationProfile,Long> {
    List<EducationProfile> findByApplicantIdOrderByStartdateDesc(Long applicationId);
}
