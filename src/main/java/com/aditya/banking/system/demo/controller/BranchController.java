package com.aditya.banking.system.demo.controller;

import com.aditya.banking.system.demo.dao.BranchRepository;
import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.entity.dao.Branch;
import com.aditya.banking.system.demo.model.request.BranchModel;
import com.aditya.banking.system.demo.service.api.BranchService;
import com.aditya.banking.system.demo.utils.RequestMappingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> addBranch(@RequestParam(value = "userId") Long userId,
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
    public ResponseEntity<Object> getBranchDetails(@RequestParam(value = "userId") Long userId,
                                                   @RequestParam(value = "branchId") Long branchId) {
        try {
            Branch savedBranchDetails = branchService.getBranchDetails(userId, branchId);
            return new ResponseEntity<>(savedBranchDetails, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in updating the bank branch details by User : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBranch(@RequestParam(value = "userId") Long userId,
                                               @RequestBody BranchModel branchModel, @RequestParam(value = "branchId") Long branchId) {
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
    public ResponseEntity<Object> deleteBranch(@RequestParam(value = "userId") Long userId,
                                               @RequestParam(value = "branchId") Long branchId) {
        try {
            branchService.deleteBranch(userId, branchId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in deleting bank branch by User : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

}
