package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.Branch;

public interface BranchService {
    Branch saveBranch(String userId, Branch branch);

    Branch getBranchDetails(String userId, Long branchId);

    Branch updateBranch(String userId, Branch branch, Long branchId);

    void deleteBranch(String userId, Long branchId);
}
