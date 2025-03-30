package com.cooper.mall.service;

import com.cooper.mall.dto.UserRegisterRequest;
import com.cooper.mall.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
