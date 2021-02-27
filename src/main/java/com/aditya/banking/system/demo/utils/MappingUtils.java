package com.aditya.banking.system.demo.utils;

import com.aditya.banking.system.demo.entity.dao.BankAccount;
import com.aditya.banking.system.demo.entity.dao.Customer;
import com.aditya.banking.system.demo.entity.dao.CustomerAccount;
import com.aditya.banking.system.demo.entity.dao.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MappingUtils {

    public Customer mapUserAccountToCustomerEntity(UserAccount userAccount) {
        return new DozerBeanMapper().map(userAccount, Customer.class);
    }

    public CustomerAccount getCustomerAccountEntity(BankAccount bankAccount, Long customerId) {
        CustomerAccount customerAccount  = new CustomerAccount();
        customerAccount.setBankId(bankAccount.getId());
        customerAccount.setCreatedBy(bankAccount.getCreatedBy());
        customerAccount.setCreatedDate(bankAccount.getCreatedDate());
        customerAccount.setUpdatedBy(customerId);
        customerAccount.setUpdatedDate(new Date());
        customerAccount.setCustomerId(customerId);
        return customerAccount;
    }

    public BankAccount mapBankAccountEntity(BankAccount previousAccount, double newBalance) {
        BankAccount bankAccount= new BankAccount();
        bankAccount.setBalance(newBalance);
        bankAccount.setId(previousAccount.getId());
        bankAccount.setInterestRate(previousAccount.getInterestRate());
        bankAccount.setNumber(previousAccount.getNumber());
        bankAccount.setBankBranchId(previousAccount.getBankBranchId());
        bankAccount.setStatus(previousAccount.getStatus());
        bankAccount.setType(previousAccount.getType());
        bankAccount.setUpdatedDate(new Date());
        bankAccount.setUpdatedBy(previousAccount.getUpdatedBy());
        bankAccount.setCreatedBy(previousAccount.getCreatedBy());
        bankAccount.setCreatedDate(previousAccount.getCreatedDate());
        return bankAccount;
    }
}
