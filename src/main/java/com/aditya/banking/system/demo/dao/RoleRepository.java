package com.aditya.banking.system.demo.dao;

import java.util.Optional;

import com.aditya.banking.system.demo.entity.constant.enums.ERole;
import com.aditya.banking.system.demo.entity.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
