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
@Table(name="project_profile")
public class ProjectProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //项目/活动名称
    @Column
    private String title;

    //项目/活动起始时间
    @Column
    @Temporal(TemporalType.DATE)
    private Date startDate;

    //项目/活动终止时间
    @Column
    @Temporal(TemporalType.DATE)
    private Date endDate;

    //项目/活动简介
    @Column
    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="applicant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Applicant applicant;

    public ProjectProfile(){}
}
