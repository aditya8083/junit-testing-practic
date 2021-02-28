package com.aditya.banking.system.demo.service.impl;

import com.aditya.banking.system.demo.dao.EmployeeRepository;
import com.aditya.banking.system.demo.entity.constant.enums.ResponseCode;
import com.aditya.banking.system.demo.entity.dao.Employee;
import com.aditya.banking.system.demo.exception.BusinessLogicException;
import com.aditya.banking.system.demo.service.api.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger LOG = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        try {
            employee.setCreatedBy(employee.getEmail());
            employee.setCreatedDate(new Date());
            employee.setUpdatedBy(employee.getEmail());
            employee.setUpdatedDate(new Date());
            return employeeRepository.save(employee);
        } catch (Exception e) {
            LOG.error("Error in saving the employee details ");
            throw new BusinessLogicException(ResponseCode.DUPLICATE_REQUEST_BODY_FIELDS.getCode(), ResponseCode.DUPLICATE_REQUEST_BODY_FIELDS.getMessage());
        }

    }

    @Override
    public Employee updateEmployee(Employee employee, Long employeeId) {
        if (employeeRepository.existsById(employeeId)) {
            Employee savedEmployee = employeeRepository.findById(employeeId).get();
            employee.setId(employeeId);
            employee.setCreatedBy(savedEmployee.getCreatedBy());
            employee.setCreatedDate(savedEmployee.getCreatedDate());
            employee.setUpdatedBy(employeeId.toString());
            employee.setUpdatedDate(new Date());
            return employeeRepository.save(employee);
        } else {
            LOG.info("Employee does not exists : {} ", employeeId);
            throw new BusinessLogicException(ResponseCode.EMPLOYEE_DOES_NOT_EXISTS.getCode(), ResponseCode.EMPLOYEE_DOES_NOT_EXISTS.getMessage());
        }
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
        } else {
            LOG.info("Employee does not exists : {} ", employeeId);
            throw new BusinessLogicException(ResponseCode.EMPLOYEE_DOES_NOT_EXISTS.getCode(), ResponseCode.EMPLOYEE_DOES_NOT_EXISTS.getMessage());
        }
    }

    @Override
    public Employee getEmployeeDetails(Long employeeId) {

        if (employeeRepository.existsById(employeeId)) {
            return employeeRepository.findById(employeeId).get();
        } else {
            LOG.info("Employee does not exists : {} ", employeeId);
            throw new BusinessLogicException(ResponseCode.EMPLOYEE_DOES_NOT_EXISTS.getCode(), ResponseCode.EMPLOYEE_DOES_NOT_EXISTS.getMessage());
        }
    }
}
