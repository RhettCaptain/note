package com.lannister.sparkhire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lannister.sparkhire.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer>{
	Staff findByUsernameAndPassword(String username, String password);
	List<Staff> findByRoleidOrderByUserid(Integer roleId);
}
