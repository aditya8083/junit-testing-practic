package com.aditya.banking.system.demo.service.impl;

import com.aditya.banking.system.demo.dao.BranchRepository;
import com.aditya.banking.system.demo.entity.dao.Branch;
import com.aditya.banking.system.demo.service.api.BranchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BranchServiceImpl implements BranchService {
    private static final Logger LOG = LoggerFactory.getLogger(BranchServiceImpl.class);

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    AdminServiceImpl adminService;

    @Override
    public Branch saveBranch(Long userId, Branch branch) {
        if(adminService.isAdminUser(userId)){
            branch.setCreatedBy(userId);
            branch.setCreatedDate(new Date());
            branch.setUpdatedBy(userId);
            branch.setUpdatedDate(new Date());
            branchRepository.save(branch);
        }
        return null;
    }

    @Override
    public Branch getBranchDetails(Long userId, Long branchId) {
        if(adminService.isAdminUser(userId)){
            return branchRepository.findById(branchId).get();
        }
        return null;
    }

    @Override
    public Branch updateBranch(Long userId, Branch branch, Long branchId) {
        if(adminService.isAdminUser(userId)){
            Branch savedBranch = branchRepository.findById(branchId).get();
            branch.setId(branchId);
            branch.setUpdatedBy(userId);
            branch.setUpdatedDate(new Date());
            branch.setCreatedBy(savedBranch.getCreatedBy());
            branch.setCreatedDate(savedBranch.getCreatedDate());
            return branchRepository.save(branch);
        }
        return null;
    }

    @Override
    public void deleteBranch(Long userId, Long branchId) {
        if(adminService.isAdminUser(userId)) {
            branchRepository.deleteById(branchId);
        }
    }
}
