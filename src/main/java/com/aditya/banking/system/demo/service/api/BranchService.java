package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.Branch;

public interface BranchService {
    Branch saveBranch(Long userId, Branch branch);

    Branch getBranchDetails(Long userId, Long branchId);

    Branch updateBranch(Long userId, Branch branch, Long branchId);

    void deleteBranch(Long userId, Long branchId);
}
