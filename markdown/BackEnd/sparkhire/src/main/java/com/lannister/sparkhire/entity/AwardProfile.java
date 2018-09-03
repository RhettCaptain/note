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
@Table(name="award_profile")
public class AwardProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //奖励来源（国际级/国家级/省市级/校级/院级）
    @Column
    private String source;

    //奖励名称
    @Column
    private String title;

    //获奖时间
    @Column
    @Temporal(TemporalType.DATE)
    private Date awardtime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="applicant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Applicant applicant;

    public AwardProfile(){}
}
