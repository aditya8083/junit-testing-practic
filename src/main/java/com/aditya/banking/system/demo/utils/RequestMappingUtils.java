package com.aditya.banking.system.demo.utils;


import com.aditya.banking.system.demo.entity.dao.Employee;
import com.aditya.banking.system.demo.model.request.EmployeeModel;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RequestMappingUtils {

    public Employee mapEmployeeModelRequest(EmployeeModel employeeModel) {
        return new DozerBeanMapper().map(employeeModel, Employee.class);
    }
}
