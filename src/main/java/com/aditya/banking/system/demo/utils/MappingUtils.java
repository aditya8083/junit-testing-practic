package com.aditya.banking.system.demo.utils;

import com.aditya.banking.system.demo.entity.dao.Customer;
import com.aditya.banking.system.demo.entity.dao.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MappingUtils {

    public Customer mapUserAccountToCustomerEntity(UserAccount userAccount) {
        return new DozerBeanMapper().map(userAccount, Customer.class);
    }
}
