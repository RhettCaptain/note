package com.lannister.sparkhire.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class StaffVo {
    private Integer userid;
    private String username;
    private String realname;
    private String password;
    private Integer roleid;
    private String phone;
    private String email;
    private String portrait;

    public StaffVo(){}
    
    public StaffVo(Integer userid,String username,String realname,String password,
    		Integer roleid,String phone,String email,String portrait){
    	this.userid = userid;
    	this.username = username;
    	this.realname = realname;
    	this.password = password;
    	this.roleid = roleid;
    	this.phone = phone;
    	this.email = email;
    	this.portrait = portrait;
    }
}
