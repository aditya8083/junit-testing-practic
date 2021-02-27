package com.aditya.banking.system.demo.service.impl;

import com.aditya.banking.system.demo.dao.UserAccountRepository;
import com.aditya.banking.system.demo.entity.constant.enums.ResponseCode;
import com.aditya.banking.system.demo.entity.dao.Customer;
import com.aditya.banking.system.demo.entity.dao.UserAccount;
import com.aditya.banking.system.demo.exception.BusinessLogicException;
import com.aditya.banking.system.demo.service.api.UserAccountService;
import com.aditya.banking.system.demo.utils.MappingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private static final Logger LOG = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @Autowired
    MappingUtils mappingUtils;

    @Override
    public void register(Long clientId, UserAccount userAccount){
        Optional<UserAccount> accountOptional = userAccountRepository.findByEmail(userAccount.getEmail());
        if (!accountOptional.isPresent()) {
            if (!userAccount.isEmployee()) {
                try {
                    Customer customer = mappingUtils.mapUserAccountToCustomerEntity(userAccount);
                    customerServiceImpl.saveCustomer(clientId, customer);
                }  catch (Exception exception) {
                    LOG.error("Exception while saving the customer data to database : {}", userAccount);
                    throw new BusinessLogicException(ResponseCode.SYSTEM_ERROR.getCode(), exception.getMessage());
                }
            }
            userAccount.setLoggedIn(true);
            userAccountRepository.save(userAccount);
        } else {
           LOG.info("Account Already exists in database : {}", userAccount);
           throw new BusinessLogicException(ResponseCode.ACCOUNT_ALREADY_EXISTS.getCode(), ResponseCode.ACCOUNT_ALREADY_EXISTS.getMessage());
        }
    }

    @Override
    public void login(Long userId, String password) {
        Optional<UserAccount> account = userAccountRepository.findByIdAndPassword(userId, password);
        if (account.isPresent()) {
            account.get().setLoggedIn(true);
            userAccountRepository.save(account.get());
        } else {
            LOG.info("Account does not exists in database : {}", userId);
            throw new BusinessLogicException(ResponseCode.ACCOUNT_DOES_NOT_EXISTS.getCode(), ResponseCode.ACCOUNT_DOES_NOT_EXISTS.getMessage());
        }
    }

    @Override
    public void logout(Long userId) {
        Optional<UserAccount> account = userAccountRepository.findById(userId);
        if (account.isPresent()) {
            account.get().setLoggedIn(false);
            userAccountRepository.save(account.get());
        } else {
            LOG.info("Account does not exists in database : {}", userId);
            throw new BusinessLogicException(ResponseCode.ACCOUNT_DOES_NOT_EXISTS.getCode(), ResponseCode.ACCOUNT_DOES_NOT_EXISTS.getMessage());
        }
    }
}
