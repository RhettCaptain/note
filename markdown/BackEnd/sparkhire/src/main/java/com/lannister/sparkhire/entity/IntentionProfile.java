package com.lannister.sparkhire.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by yang_fa-pc on 2018/8/30.
 */
@Data
@Entity
@Table(name="intention_profile")
public class IntentionProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String currentsalary;

    @Column
    @NotNull
    private String expectsalary;

    @Column
    @Temporal(TemporalType.DATE)
    private Date avaliabledate;

    @Column
    private String expectcity;

    @Column
    private String expectjob;

    @Column
    @Lob
    private String seftevaluation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="applicant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Applicant applicant;

    public IntentionProfile(){}
}
