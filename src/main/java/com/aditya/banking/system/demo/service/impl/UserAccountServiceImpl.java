package com.aditya.banking.system.demo.service.impl;

import com.aditya.banking.system.demo.dao.RoleRepository;
import com.aditya.banking.system.demo.dao.UserAccountRepository;
import com.aditya.banking.system.demo.entity.constant.enums.ERole;
import com.aditya.banking.system.demo.entity.constant.enums.ResponseCode;
import com.aditya.banking.system.demo.entity.dao.Customer;
import com.aditya.banking.system.demo.entity.dao.Role;
import com.aditya.banking.system.demo.entity.dao.UserAccount;
import com.aditya.banking.system.demo.exception.BusinessLogicException;
import com.aditya.banking.system.demo.model.request.LoginRequest;
import com.aditya.banking.system.demo.model.request.UserAccountModel;
import com.aditya.banking.system.demo.service.api.UserAccountService;
import com.aditya.banking.system.demo.utils.MappingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private static final Logger LOG = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @Autowired
    MappingUtils mappingUtils;

    @Autowired
    RoleRepository roleRepository;

    @Autowired(required=true)
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    public void register(UserAccountModel userAccountModel) {

        UserAccount userAccount = new UserAccount(userAccountModel.getUsername(), userAccountModel.getEmail(), encoder.encode(userAccountModel.getPassword()));

        Set<String> strRoles = userAccountModel.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
    /*    try {
            Customer customer = mappingUtils.mapUserAccountToCustomerEntity(userAccountModel);
            customerServiceImpl.saveCustomer(Long.valueOf(userAccountModel.getUsername().hashCode()), customer);
        } catch (Exception exception) {
            LOG.error("Exception while saving the customer data to database : {}", userAccount);
            throw new BusinessLogicException(ResponseCode.DUPLICATE_REQUEST_BODY_FIELDS.getCode(), ResponseCode.DUPLICATE_REQUEST_BODY_FIELDS.getMessage());
        }*/
        userAccount.setRoles(roles);
        userAccountRepository.save(userAccount);

    }

   /* @Override
    public UserDetailsImpl login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Optional<UserAccount> account = userAccountRepository.findByIdAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (account.isPresent()) {
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
    }*/
}
