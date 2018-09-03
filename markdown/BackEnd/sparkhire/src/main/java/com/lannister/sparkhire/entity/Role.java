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
@Table(name="roles")
public class Role {
	@Id
    private Integer id;

    @Column
    private String role;
    
    public Role(){}
    
    public Role(Integer id, String role){
    	this.id = id;
    	this.role = role;
    }
}
