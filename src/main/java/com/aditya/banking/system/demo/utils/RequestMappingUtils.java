package com.aditya.banking.system.demo.utils;

import com.aditya.banking.system.demo.entity.constant.enums.ResponseCode;
import com.aditya.banking.system.demo.entity.dao.*;
import com.aditya.banking.system.demo.exception.RequestMappingException;
import com.aditya.banking.system.demo.model.request.*;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RequestMappingUtils {

    private static final Logger LOG = LoggerFactory.getLogger(RequestMappingUtils.class);

    public Employee mapEmployeeModelRequest(EmployeeModel employeeModel) {
        try {
            return new DozerBeanMapper().map(employeeModel, Employee.class);
        } catch (MappingException e) {
            LOG.error("request mapping exception for employeeModel :{}", e);
            throw new RequestMappingException(ResponseCode.REQUEST_MAPPING_EXCEPTION.getCode(), ResponseCode.REQUEST_MAPPING_EXCEPTION.getMessage());
        }
    }

    public Customer mapCustomerModelRequest(CustomerModel customerModel) {
        try {
            return new DozerBeanMapper().map(customerModel, Customer.class);
        } catch (MappingException e) {
            LOG.error("request mapping exception for customerModel :{}", e);
            throw new RequestMappingException(ResponseCode.REQUEST_MAPPING_EXCEPTION.getCode(), ResponseCode.REQUEST_MAPPING_EXCEPTION.getMessage());
        }
    }

    public Branch mapBranchModelRequest(BranchModel branchModel) {
        try {
            return new DozerBeanMapper().map(branchModel, Branch.class);
        } catch (MappingException e) {
            LOG.error("request mapping exception for branchModel :{}", e);
            throw new RequestMappingException(ResponseCode.REQUEST_MAPPING_EXCEPTION.getCode(), ResponseCode.REQUEST_MAPPING_EXCEPTION.getMessage());
        }
    }

    public Bank mapBankModelRequest(BankModel bankModel) {
        try {
            return new DozerBeanMapper().map(bankModel, Bank.class);
        } catch (MappingException e) {
            LOG.error("request mapping exception for bankModel :{}", e);
            throw new RequestMappingException(ResponseCode.REQUEST_MAPPING_EXCEPTION.getCode(), ResponseCode.REQUEST_MAPPING_EXCEPTION.getMessage());
        }
    }

    public UserAccount mapUserAccountModelRequest(UserAccountModel userAccountModel) {
        try {
            return new DozerBeanMapper().map(userAccountModel, UserAccount.class);
        } catch (MappingException e) {
            LOG.error("request mapping exception for userAccountModel :{}", e);
            throw new RequestMappingException(ResponseCode.REQUEST_MAPPING_EXCEPTION.getCode(), ResponseCode.REQUEST_MAPPING_EXCEPTION.getMessage());
        }
    }

    public BankAccount mapBankAccountModelRequest(BankAccountModel bankAccountModel) {
        try {
            return new DozerBeanMapper().map(bankAccountModel, BankAccount.class);
        } catch (MappingException e) {
            LOG.error("request mapping exception for bankAccountModel :{}", e);
            throw new RequestMappingException(ResponseCode.REQUEST_MAPPING_EXCEPTION.getCode(), ResponseCode.REQUEST_MAPPING_EXCEPTION.getMessage());
        }

    }
}
