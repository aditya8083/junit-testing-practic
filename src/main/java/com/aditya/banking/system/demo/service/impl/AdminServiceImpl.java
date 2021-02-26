package com.aditya.banking.system.demo.service.impl;

import com.aditya.banking.system.demo.dao.EmployeeRepository;
import com.aditya.banking.system.demo.entity.dao.Employee;
import com.aditya.banking.system.demo.service.api.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger LOG = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    EmployeeRepository employeeRepository;

    public boolean isAdminUser(Long userId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(userId);
        return employeeOptional.isPresent() && employeeOptional.get().isAdmin();
    }

    @Override
    public Employee saveEmployee(Long userId, Employee employee) {
        if (isAdminUser(userId)) {
            employee.setCreatedBy(userId);
            employee.setCreatedDate(new Date());
            employee.setUpdatedBy(userId);
            employee.setUpdatedDate(new Date());
            employeeRepository.save(employee);
        }
        return null;
    }

    @Override
    public Employee updateEmployee(Long userId, Employee employee, Long employeeId) {
        if (isAdminUser(userId)) {
            Employee savedEmployee = employeeRepository.getOne(employeeId);
            employee.setId(employeeId);
            employee.setCreatedBy(savedEmployee.getCreatedBy());
            employee.setCreatedDate(savedEmployee.getCreatedDate());
            employee.setUpdatedBy(userId);
            employee.setUpdatedDate(new Date());
            employeeRepository.save(employee);
        }
        return null;
    }

    @Override
    public void deleteEmployee(Long userId, Long employeeId) {
        if (isAdminUser(userId)) {
            employeeRepository.deleteById(employeeId);
        }
    }

    @Override
    public Employee getEmployeeDetails(Long userId, Long employeeId) {
        if (isAdminUser(userId)) {
            return employeeRepository.findById(employeeId).get();
        }
        return null;
    }
}
