package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.Employee;

public interface AdminService {
    Employee saveEmployee(Long userId, Employee employee);

    Employee updateEmployee(Long userId, Employee employee, Long employeeId);

    void deleteEmployee(Long userId, Long employeeId);

    Employee getEmployeeDetails(Long userId, Long employeeId);
}
