package com.manoeljr.payment.domain.service.user;

import com.manoeljr.payment.api.dtos.user.UserRequest;
import com.manoeljr.payment.domain.entity.User;
import com.manoeljr.payment.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User registerUser(UserRequest userRequest) {
        User user = userRequest.mapperToUserEntity();
        if (repository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("This email already exists !");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}
