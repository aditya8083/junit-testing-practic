package com.aditya.banking.system.demo.controller;

import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.model.request.EmployeeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping(ApiPath.ADMIN)
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(value = "/bankEmployee", method = RequestMethod.POST)
    public ResponseEntity<Object> addBankEmployee(@RequestHeader(value = "userId") String userId,
                                                  @RequestBody EmployeeModel employeeModel) throws IOException {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/bankEmployee", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBankEmployee(@RequestHeader(value = "userId") String userId,
                                                     @RequestBody EmployeeModel employeeModel) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/bankEmployee", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBankEmployee(@RequestHeader(value = "userId") String userId,
                                                     @RequestParam String employeeId) throws IOException {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
