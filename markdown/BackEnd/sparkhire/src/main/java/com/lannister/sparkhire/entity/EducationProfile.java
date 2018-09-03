package com.lannister.sparkhire.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yang_fa-pc on 2018/8/30.
 */
@Data
@Entity
@Table(name="education_profile")
public class EducationProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Education education;

    @Temporal(TemporalType.DATE)
    @Column
    private Date startdate;

    @Temporal(TemporalType.DATE)
    @Column
    private Date enddate;

    @Column
    private String school;

    @Column
    private String colledge;

    @Column
    private String major;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="applicant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Applicant applicant;

    public EducationProfile(){}

}
