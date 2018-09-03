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
@Table(name="training_profile")
public class TrainingProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column
    private Date startdate;

    @Temporal(TemporalType.DATE)
    @Column
    private Date enddate;

    @Column
    private String coursetitle;

    @Column
    private String organization;

    @Column
    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="applicant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Applicant applicant;

    public TrainingProfile(){}
}
