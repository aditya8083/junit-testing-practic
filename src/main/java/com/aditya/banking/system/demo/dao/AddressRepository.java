package com.aditya.banking.system.demo.dao;

import com.aditya.banking.system.demo.entity.dao.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
