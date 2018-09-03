package com.lannister.sparkhire.repository;

import com.lannister.sparkhire.entity.AwardProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yang_fa-pc on 2018/8/30.
 */
@Repository
public interface AwardProfileRepository extends JpaRepository<AwardProfile, Long> {
    List<AwardProfile> findByApplicantIdOrderByAwardtimeDesc(Long applicationId);

}
