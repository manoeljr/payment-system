package com.manoeljr.payment.domain.service.user;

import com.manoeljr.payment.api.dtos.user.UserRequest;
import com.manoeljr.payment.domain.entity.User;
import com.manoeljr.payment.domain.repository.UserRepository;
import com.manoeljr.payment.domain.service.email.EmailService;
import com.manoeljr.payment.utils.GenerateCodeActivationAccountUser;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    public User registerUser(UserRequest userRequest) throws MessagingException, UnsupportedEncodingException {
        var user = userRequest.fromDtoToEntity();

        if (repository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("This email already exists !");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setVerificationCode(GenerateCodeActivationAccountUser.generateRandomString(64));
        user.setEnabled(false);

        var userNew = repository.save(user);
        emailService.sendVerificationEmail(user);

        return userNew;
    }

    public boolean verifyCode(String code) {
        var userRepository =  repository.findByVerificationCode(code);

        if (userRepository == null || userRepository.isEnabled()) {
            return false;
        }

        userRepository.setVerificationCode(null);
        userRepository.setEnabled(true);
        repository.save(userRepository);

        return true;
    }


}
