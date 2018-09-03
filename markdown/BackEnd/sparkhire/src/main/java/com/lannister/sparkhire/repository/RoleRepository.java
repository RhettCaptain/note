package com.lannister.sparkhire.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lannister.sparkhire.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
}
