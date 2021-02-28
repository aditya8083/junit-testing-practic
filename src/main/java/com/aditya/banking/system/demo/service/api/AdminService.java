package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.Employee;

public interface AdminService {
    Employee saveEmployee( Employee employee);

    Employee updateEmployee( Employee employee, Long employeeId);

    void deleteEmployee( Long employeeId);

    Employee getEmployeeDetails( Long employeeId);
}
