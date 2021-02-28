package com.aditya.banking.system.demo.controller;

import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.entity.constant.enums.ResponseCode;
import com.aditya.banking.system.demo.entity.dao.Employee;
import com.aditya.banking.system.demo.model.request.EmployeeModel;
import com.aditya.banking.system.demo.service.api.EmployeeService;
import com.aditya.banking.system.demo.utils.RequestMappingUtils;

import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController(value = "Employee CRUD APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(ApiPath.ADMIN)
public class EmployeeController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    RequestMappingUtils requestMappingUtils;

    @Autowired
    EmployeeService adminService;

    @PostMapping(value = "/bankEmployee")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "create Bank Employee by Admin")
    public ResponseEntity<Object> createBankEmployee(@RequestBody EmployeeModel employeeModel) {
        try {
            Employee employee = requestMappingUtils.mapEmployeeModelRequest(employeeModel);
            Employee savedEmployee = adminService.createEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (Exception exception) {
            LOG.error("Error in saving the bank employee data by User : {}", exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping(value = "/bankEmployee")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "update Bank Employee by Admin")
    public ResponseEntity<Object> updateBankEmployee(@RequestBody EmployeeModel employeeModel, @RequestHeader Long employeeId) {
        try {
            Employee employee = requestMappingUtils.mapEmployeeModelRequest(employeeModel);
            Employee updatedEmployee = adminService.updateEmployee(employee, employeeId);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in updating the bank employee data by User : {}", exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping(value = "/bankEmployee")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "delete Bank Employee by Admin")
    public ResponseEntity<Object> deleteBankEmployee(@RequestHeader(value = "employeeId") Long employeeId) {
        try {
            adminService.deleteEmployee(employeeId);
            return new ResponseEntity<>(ResponseCode.SUCCESS.getMessage(), HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in deleting bank employee data by User : {}", exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = "/bankEmployee")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "get Bank Employee details by Admin")
    public ResponseEntity<Object> getBankEmployee(@RequestHeader(value = "employeeId") Long employeeId) {
        try {
            Employee employee = adminService.getEmployeeDetails(employeeId);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in getting the bank employee data by User : {}", exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

}
