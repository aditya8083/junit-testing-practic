package com.aditya.banking.system.demo.utils;

import com.aditya.banking.system.demo.exception.RequestMappingException;
import com.aditya.banking.system.demo.entity.constant.enums.ResponseCode;
import com.aditya.banking.system.demo.entity.dao.*;
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
            LOG.error("request mapping exception :{}", e);
            throw new RequestMappingException(ResponseCode.REQUEST_MAPPING_EXCEPTION.getCode(), ResponseCode.REQUEST_MAPPING_EXCEPTION.getMessage());
        }
    }

    public Customer mapCustomerModelRequest(CustomerModel customerModel) {
        try {
            return new DozerBeanMapper().map(customerModel, Customer.class);
        } catch (MappingException e) {
            LOG.error("request mapping exception :{}", e);
            throw new RequestMappingException(ResponseCode.REQUEST_MAPPING_EXCEPTION.getCode(), ResponseCode.REQUEST_MAPPING_EXCEPTION.getMessage());
        }
    }

    public Branch mapBranchModelRequest(BranchModel branchModel) {
        try {
            return new DozerBeanMapper().map(branchModel, Branch.class);
        } catch (MappingException e) {
            LOG.error("request mapping exception :{}", e);
            throw new RequestMappingException(ResponseCode.REQUEST_MAPPING_EXCEPTION.getCode(), ResponseCode.REQUEST_MAPPING_EXCEPTION.getMessage());
        }
    }

    public Bank mapBankModelRequest(BankModel bankModel) {
        try {
            return new DozerBeanMapper().map(bankModel, Bank.class);
        } catch (MappingException e) {
            LOG.error("request mapping exception :{}", e);
            throw new RequestMappingException(ResponseCode.REQUEST_MAPPING_EXCEPTION.getCode(), ResponseCode.REQUEST_MAPPING_EXCEPTION.getMessage());
        }
    }

    public UserAccount mapUserAccountModelRequest(UserAccountModel accountModel) {
        try {
            return new DozerBeanMapper().map(accountModel, UserAccount.class);
        } catch (MappingException e) {
            LOG.error("request mapping exception :{}", e);
            throw new RequestMappingException(ResponseCode.REQUEST_MAPPING_EXCEPTION.getCode(), ResponseCode.REQUEST_MAPPING_EXCEPTION.getMessage());
        }
    }
}
