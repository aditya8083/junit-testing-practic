package com.aditya.banking.system.demo.controller;

import com.aditya.banking.system.demo.dao.BranchRepository;
import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.entity.constant.enums.ResponseCode;
import com.aditya.banking.system.demo.entity.dao.Branch;
import com.aditya.banking.system.demo.model.request.BranchModel;
import com.aditya.banking.system.demo.service.api.BranchService;
import com.aditya.banking.system.demo.utils.RequestMappingUtils;

import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(ApiPath.BRANCH)
public class BranchController {

    private static final Logger LOG = LoggerFactory.getLogger(BranchController.class);

    @Autowired
    BranchRepository bankRepository;

    @Autowired
    BranchService branchService;

    @Autowired
    RequestMappingUtils requestMappingUtils;

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")

    @ApiOperation(value = "create bank Branch by Admin only")
    public ResponseEntity<Object> createBranch(@RequestHeader(value = "userId") String userId,
                                            @RequestBody BranchModel branchModel) {
        try {
            Branch branch = requestMappingUtils.mapBranchModelRequest(branchModel);
            Branch savedBranch = branchService.saveBranch(userId, branch);
            return new ResponseEntity<>(savedBranch, HttpStatus.CREATED);
        } catch (Exception exception) {
            LOG.error("Error in saving the bank branch details data by User : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get bank Branch by Admin only")
    public ResponseEntity<Object> getBranchDetails(@RequestHeader(value = "userId") String userId,
                                                   @RequestHeader(value = "branchId") Long branchId) {
        try {
            Branch savedBranchDetails = branchService.getBranchDetails(userId, branchId);
            return new ResponseEntity<>(savedBranchDetails, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in updating the bank branch details by User : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update bank Branch by Admin only")
    public ResponseEntity<Object> updateBranch(@RequestHeader(value = "userId") String userId,
                                               @RequestBody BranchModel branchModel, @RequestHeader(value = "branchId") Long branchId) {
        try {
            Branch bank = requestMappingUtils.mapBranchModelRequest(branchModel);
            Branch updatedBranch = branchService.updateBranch(userId, bank, branchId);
            return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in updating the bank branch details by User : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete bank Branch by Admin only")
    public ResponseEntity<Object> deleteBranch(@RequestHeader(value = "userId") String userId,
                                               @RequestHeader(value = "branchId") Long branchId) {
        try {
            branchService.deleteBranch(userId, branchId);
            return new ResponseEntity<>(ResponseCode.SUCCESS.getMessage(), HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in deleting bank branch by User : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

}
