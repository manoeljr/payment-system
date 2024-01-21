package com.manoeljr.payment.api.controller;

import com.manoeljr.payment.api.dtos.user.UserRequest;
import com.manoeljr.payment.api.dtos.user.UserResponse;
import com.manoeljr.payment.domain.entity.User;
import com.manoeljr.payment.domain.service.user.UserService;
import com.manoeljr.payment.domain.service.user.UserServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService service;
    private final UserServiceImpl verifyCode;

    public UserController(UserService service, UserServiceImpl userServiceImpl) {
        this.service = service;
        this.verifyCode = userServiceImpl;
    }

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest registerNewUser) throws MessagingException, UnsupportedEncodingException {
        User result = service.registerUser(registerNewUser);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (verifyCode.verifyCode(code)) {
            return "verify_success";
        }
        return "verify_fail";
    }

}
