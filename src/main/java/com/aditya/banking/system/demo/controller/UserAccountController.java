package com.aditya.banking.system.demo.controller;


import com.aditya.banking.system.demo.dao.RoleRepository;
import com.aditya.banking.system.demo.dao.UserAccountRepository;
import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.entity.constant.enums.ERole;
import com.aditya.banking.system.demo.entity.dao.Role;
import com.aditya.banking.system.demo.entity.dao.UserAccount;
import com.aditya.banking.system.demo.exception.BusinessLogicException;
import com.aditya.banking.system.demo.model.request.LoginRequest;
import com.aditya.banking.system.demo.model.request.UserAccountModel;
import com.aditya.banking.system.demo.model.response.JwtResponse;
import com.aditya.banking.system.demo.security.jwt.JwtUtils;
import com.aditya.banking.system.demo.security.services.UserDetailsImpl;
import com.aditya.banking.system.demo.service.api.UserAccountService;
import com.aditya.banking.system.demo.utils.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(ApiPath.ACCOUNT)
public class UserAccountController {

    private static final Logger LOG = LoggerFactory.getLogger(UserAccountController.class);


    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping(value = "/register")
    public ResponseEntity<Object> register(@RequestBody UserAccountModel userAccountModel) {
        if (userAccountRepository.existsByUsername(userAccountModel.getUsername())) {
            return ResponseEntity.badRequest().body(new BusinessLogicException("412", "Error: Username is already taken!"));
        }
        if (userAccountRepository.existsByEmail(userAccountModel.getEmail())) {
            return ResponseEntity.badRequest().body(new BusinessLogicException("412", "Error: Email is already in use!"));
        }
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
        userAccount.setRoles(roles);
        userAccountRepository.save(userAccount);
        return ResponseEntity.ok(new MessageResponse("201", "User registered successfully!"));

    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


/*
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    public ResponseEntity<Object> login( @RequestBody LoginRequest loginRequest) throws IOException {
        try {
            userAccountService.login(loginRequest);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in login for user : {}, {}", loginRequest.getUsername(), exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    public ResponseEntity<Object> logout(@RequestParam Long userId) throws IOException {
        try {
            userAccountService.logout(userId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in logout for user : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }*/


}
