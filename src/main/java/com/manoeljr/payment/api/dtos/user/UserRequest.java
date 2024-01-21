package com.manoeljr.payment.api.dtos.user;

import com.manoeljr.payment.domain.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotNull(message = "NAME is not NULL")
        @NotBlank(message = "NAME is not EMPTY")
        @Size(min = 2, message = "NAME is not have min 2 caracter")
        String name,

        @Email
        @NotNull(message = "EMAIL is not NULL")
        String email,

        @NotBlank(message = "PASSWORD is not EMPTY")
        @NotNull(message = "PASSWORD is not NULL")
        @Size(min = 8, message = "Password not have min 8 caracter ")
        String password
) {

    public User fromDtoToEntity() {
        User user = new User();
        user.setName(name());
        user.setEmail(email());
        user.setPassword(password());
        return user;
    }

}
