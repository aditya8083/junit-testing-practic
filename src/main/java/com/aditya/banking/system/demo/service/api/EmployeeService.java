package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.Employee;

public interface EmployeeService {
    /**
     * Create bank employee by admin only
     *
     * @param employee employee entity object
     */
    Employee createEmployee(Employee employee);


    /**
     * Update bank employee by Admin only
     *
     * @param employee   employee entity object \
     * @param employeeId employeeId
     */
    Employee updateEmployee(Employee employee, Long employeeId);

    /**
     * Delete bank employee by Admin only
     *
     * @param employeeId employeeId
     */
    void deleteEmployee(Long employeeId);

    /**
     * Get bank employee Details by Admin only
     *
     * @param employeeId employeeId
     */
    Employee getEmployeeDetails(Long employeeId);
}
