package com.lannister.sparkhire.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="staffs")
public class Staff {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;

    @Column
    private String username;

    @Column
    private String realname;
    
    @Column
    private String password;
    
    @Column
    private Integer roleid;
    
    @Column
    private String phone;
    
    @Column
    private String email;
    
    @Column
    private String portrait;

    public Staff(){}
    
    public Staff(String username,String realname,String password,
    		Integer roleid,String phone,String email,String portrait){
    	this.username = username;
    	this.realname = realname;
    	this.password = password;
    	this.roleid = roleid;
    	this.phone = phone;
    	this.email = email;
    	this.portrait = portrait;
    }
}
