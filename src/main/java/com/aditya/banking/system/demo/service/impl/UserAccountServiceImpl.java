package com.aditya.banking.system.demo.service.impl;

import com.aditya.banking.system.demo.dao.UserAccountRepository;
import com.aditya.banking.system.demo.entity.dao.Customer;
import com.aditya.banking.system.demo.entity.dao.UserAccount;
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
    public void register(Long clientId, UserAccount userAccount) throws Exception {
        Optional<UserAccount> accountOptional = userAccountRepository.findById(userAccount.getId());
        if (!accountOptional.isPresent()) {
            if (!userAccount.isEmployee()) {
                Customer customer = mappingUtils.mapUserAccountToCustomerEntity(userAccount);
                customerServiceImpl.saveCustomer(clientId, customer);
            }
            userAccount.setLoggedIn(true);
            userAccountRepository.save(userAccount);
        } else {
            throw new Exception();
        }
    }

    @Override
    public void login(Long userId, String password) {
        Optional<UserAccount> account = userAccountRepository.findByIdAndPassword(userId, password);
        if (account.isPresent()) {
            account.get().setLoggedIn(true);
            userAccountRepository.save(account.get());
        } else {
        }
    }

    @Override
    public void logout(Long userId) {
        Optional<UserAccount> account = userAccountRepository.findById(userId);
        if (account.isPresent()) {
            account.get().setLoggedIn(false);
            userAccountRepository.save(account.get());
        } else {

        }
    }
}
