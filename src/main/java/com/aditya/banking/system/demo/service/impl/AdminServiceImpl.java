package com.aditya.banking.system.demo.service.impl;

import com.aditya.banking.system.demo.configuration.BusinessLogicException;
import com.aditya.banking.system.demo.dao.EmployeeRepository;
import com.aditya.banking.system.demo.entity.constant.enums.ResponseCode;
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
            try {
                employee.setCreatedBy(userId);
                employee.setCreatedDate(new Date());
                employee.setUpdatedBy(userId);
                employee.setUpdatedDate(new Date());
                return employeeRepository.save(employee);
            } catch (Exception e) {
                LOG.error("Error in saving the employee details ");
                throw new BusinessLogicException(ResponseCode.SYSTEM_ERROR.getCode(), ResponseCode.SYSTEM_ERROR.getMessage());
            }
        } else {
            LOG.info("You are not an admin : {}", userId);
            throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED_ERROR.getCode(), ResponseCode.NOT_AUTHORIZED_ERROR.getMessage());
        }
    }

    @Override
    public Employee updateEmployee(Long userId, Employee employee, Long employeeId) {
        if (isAdminUser(userId)) {
            if (employeeRepository.existsById(employeeId)) {
                Employee savedEmployee = employeeRepository.findById(employeeId).get();
                employee.setId(employeeId);
                employee.setCreatedBy(savedEmployee.getCreatedBy());
                employee.setCreatedDate(savedEmployee.getCreatedDate());
                employee.setUpdatedBy(userId);
                employee.setUpdatedDate(new Date());
                return employeeRepository.save(employee);
            } else {
                LOG.info("Employee does not exists : {} ", employeeId);
                throw new BusinessLogicException(ResponseCode.USER_DOES_NOT_EXISTS.getCode(), ResponseCode.USER_DOES_NOT_EXISTS.getMessage());
            }
        } else {
            LOG.info("You are not admin");
            throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED_ERROR.getCode(), ResponseCode.NOT_AUTHORIZED_ERROR.getMessage());
        }
    }

    @Override
    public void deleteEmployee(Long userId, Long employeeId) {
        if (isAdminUser(userId)) {
            if (employeeRepository.existsById(employeeId)) {
                employeeRepository.deleteById(employeeId);
            } else {
                LOG.info("Employee does not exists : {} ", employeeId);
                throw new BusinessLogicException(ResponseCode.USER_DOES_NOT_EXISTS.getCode(), ResponseCode.USER_DOES_NOT_EXISTS.getMessage());
            }
        } else {
            LOG.info("You are not admin");
            throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED_ERROR.getCode(), ResponseCode.NOT_AUTHORIZED_ERROR.getMessage());
        }
    }

    @Override
    public Employee getEmployeeDetails(Long userId, Long employeeId) {
        if (isAdminUser(userId)) {
            if (employeeRepository.existsById(employeeId)) {
                return employeeRepository.findById(employeeId).get();
            } else {
                LOG.info("Employee does not exists : {} ", employeeId);
                throw new BusinessLogicException(ResponseCode.USER_DOES_NOT_EXISTS.getCode(), ResponseCode.USER_DOES_NOT_EXISTS.getMessage());
            }
        } else {
            LOG.info("You are not admin");
            throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED_ERROR.getCode(), ResponseCode.NOT_AUTHORIZED_ERROR.getMessage());
        }
    }
}
