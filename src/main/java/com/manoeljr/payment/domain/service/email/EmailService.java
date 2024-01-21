package com.manoeljr.payment.domain.service.email;

import com.manoeljr.payment.domain.entity.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendVerificationEmail(User user) throws MessagingException, UnsupportedEncodingException;
}
