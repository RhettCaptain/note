package com.lannister.sparkhire.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by yang_fa-pc on 2018/8/29.
 */
@Data
@Entity
@Table(name="applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String username;

    @Column(nullable = false)
    @NotNull
    private String password;

    @Column
    private String name;

    @Column
    private String email;

    //手机号
    @Column
    @Size(max=15)
    private String phonenumber;

    //身份证
    @Column
    private String identityid;

    //生日
    @Temporal(TemporalType.DATE)
    @Column
    private Date birthdate;

    //性别
    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

    //最高学历
    @Enumerated(EnumType.STRING)
    @Column
    private Education education;

    //所在城市
    @Column
    private String city;

    public Applicant(){}

    public Applicant(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }



}
