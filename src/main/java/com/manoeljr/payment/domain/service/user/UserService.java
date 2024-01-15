package com.manoeljr.payment.domain.service.user;

import com.manoeljr.payment.api.dtos.user.UserRequest;
import com.manoeljr.payment.domain.entity.User;

public interface UserService {
    User registerUser(UserRequest userRequest);
}
