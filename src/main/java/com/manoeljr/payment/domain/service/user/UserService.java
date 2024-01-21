package com.manoeljr.payment.domain.service.user;

import com.manoeljr.payment.api.dtos.user.UserRequest;
import com.manoeljr.payment.domain.entity.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface UserService {
    User registerUser(UserRequest userRequest) throws MessagingException, UnsupportedEncodingException;
}
