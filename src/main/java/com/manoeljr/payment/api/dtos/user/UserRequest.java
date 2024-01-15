package com.manoeljr.payment.api.dtos.user;

import com.manoeljr.payment.domain.entity.User;

public record UserRequest(String name, String email, String password) {

    public User mapperToUserEntity() {
        User user = new User();
        user.setName(name());
        user.setEmail(email());
        user.setPassword(password());
        return user;
    }

}
