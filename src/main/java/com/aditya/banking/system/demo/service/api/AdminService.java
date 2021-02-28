package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.Employee;

public interface AdminService {
    Employee saveEmployee(String userId, Employee employee);

    Employee updateEmployee(String userId, Employee employee, Long employeeId);

    void deleteEmployee(String userId, Long employeeId);

    Employee getEmployeeDetails(String userId, Long employeeId);
}
