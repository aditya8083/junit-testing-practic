package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.Branch;

public interface BranchService {

    /**
     * Create Branch details by Admin only
     *
     * @param userId admin userId
     * @param branch branchDetails
     * @return saved branchDetails
     */
    Branch saveBranch(String userId, Branch branch);

    /**
     * Get Branch details by Admin only
     *
     * @param userId   admin userId
     * @param branchId branchId
     * @return branchDetails
     */
    Branch getBranchDetails(String userId, Long branchId);

    /**
     * Update Branch details by Admin only
     *
     * @param userId admin userId
     * @param branch branchDetails
     * @return updated branchDetails
     */
    Branch updateBranch(String userId, Branch branch, Long branchId);

    /**
     * Create Branch details by Admin only
     *
     * @param userId   admin userId
     * @param branchId branchId
     * @return delete branch
     */
    void deleteBranch(String userId, Long branchId);
}
