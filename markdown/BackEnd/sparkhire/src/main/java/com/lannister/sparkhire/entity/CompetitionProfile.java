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
@Table(name="competition_profile")
public class CompetitionProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //竞赛名称
    @Column
    private String title;

    @Column
    @Temporal(TemporalType.DATE)
    private Date competitiontime;

    //竞赛成绩（一等，二等，三等，入围）
    @Column
    private String grade;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="applicant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Applicant applicant;

    public CompetitionProfile(){}
}
