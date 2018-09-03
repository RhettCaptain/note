package com.lannister.sparkhire.repository;

import com.lannister.sparkhire.entity.CompetitionProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yang_fa-pc on 2018/8/30.
 */
@Repository
public interface CompetitionProfileRepository extends JpaRepository<CompetitionProfile, Long> {
    List<CompetitionProfile> findByApplicantIdOrderByCompetitiontimeDesc(Long applicationId);
}
