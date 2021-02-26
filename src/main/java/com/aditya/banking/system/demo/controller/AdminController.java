package com.aditya.banking.system.demo.controller;

import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.entity.dao.Employee;
import com.aditya.banking.system.demo.model.request.EmployeeModel;
import com.aditya.banking.system.demo.service.api.AdminService;
import com.aditya.banking.system.demo.utils.RequestMappingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping(ApiPath.ADMIN)
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    RequestMappingUtils requestMappingUtils;

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/bankEmployee", method = RequestMethod.POST)
    public ResponseEntity<Employee> addBankEmployee(@RequestParam(value = "userId") Long userId,
                                                  @RequestBody EmployeeModel employeeModel) throws IOException {
        Employee employee = requestMappingUtils.mapEmployeeModelRequest(employeeModel);
        Employee savedEmployee = adminService.saveEmployee(userId, employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }

    @RequestMapping(value = "/bankEmployee", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateBankEmployee(@RequestParam(value = "userId") Long userId,
                                                     @RequestBody EmployeeModel employeeModel , @RequestParam Long employeeId) throws IOException {
        Employee employee = requestMappingUtils.mapEmployeeModelRequest(employeeModel);
        Employee updatedEmployee = adminService.updateEmployee(userId, employee, employeeId);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @RequestMapping(value = "/bankEmployee", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBankEmployee(@RequestParam(value = "userId") Long userId,
                                                     @RequestParam(value = "employeeId") Long employeeId) throws IOException {
        adminService.deleteEmployee(userId, employeeId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/bankEmployee", method = RequestMethod.GET)
    public ResponseEntity<Employee> getBankEmployee(@RequestParam(value = "userId") Long userId,
                                                     @RequestParam(value = "employeeId") Long employeeId) throws IOException {
        Employee employee = adminService.getEmployeeDetails(userId, employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

}
