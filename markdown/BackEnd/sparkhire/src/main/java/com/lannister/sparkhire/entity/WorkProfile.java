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
@Table(name="work_profile")
public class WorkProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //公司名称
    @Column
    private String companyname;

    //工作性质（全职/兼职/实习/创业）
    @Column
    private String worktype;

    //工作起始时间
    @Temporal(TemporalType.DATE)
    @Column
    private Date startdate;

    //工作终止时间
    @Temporal(TemporalType.DATE)
    @Column
    private Date endDate;

    //部门
    @Column
    private String department;

    //岗位
    @Column
    private String job;

    //工作职责
    @Column
    @Lob
    private String workdescription;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="applicant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Applicant applicant;

    public WorkProfile(){

    }
}
