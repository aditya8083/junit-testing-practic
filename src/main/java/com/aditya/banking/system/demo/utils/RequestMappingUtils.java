package com.aditya.banking.system.demo.utils;

import com.aditya.banking.system.demo.entity.dao.*;
import com.aditya.banking.system.demo.model.request.*;

import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RequestMappingUtils {

    public Employee mapEmployeeModelRequest(EmployeeModel employeeModel) {
        return new DozerBeanMapper().map(employeeModel, Employee.class);
    }

    public Customer mapCustomerModelRequest(CustomerModel customerModel) {
        return new DozerBeanMapper().map(customerModel, Customer.class);
    }

    public Branch mapBranchModelRequest(BranchModel branchModel) {
        return new DozerBeanMapper().map(branchModel, Branch.class);
    }

    public Bank mapBankModelRequest(BankModel bankModel) {
        return new DozerBeanMapper().map(bankModel, Bank.class);
    }

    public UserAccount mapUserAccountModelRequest(UserAccountModel accountModel) {
        return new DozerBeanMapper().map(accountModel, UserAccount.class);
    }
}
