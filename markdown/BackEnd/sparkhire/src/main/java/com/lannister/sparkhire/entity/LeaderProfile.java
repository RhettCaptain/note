package com.lannister.sparkhire.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yang_fa-pc on 2018/8/30.
 */
@Entity
@Data
@Table(name="leader_profile")
public class LeaderProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //职位名称
    @Column
    private String title;

    //就任起始时间
    @Column
    @Temporal(TemporalType.DATE)
    private Date startDate;

    //就任终止时间
    @Column
    @Temporal(TemporalType.DATE)
    private Date endDate;

    //工作职责描述
    @Column
    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="applicant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Applicant applicant;

    public LeaderProfile(){}
}
