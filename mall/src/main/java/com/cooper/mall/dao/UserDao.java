package com.cooper.mall.dao;

import com.cooper.mall.dto.UserRegisterRequest;
import com.cooper.mall.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);

    User getUserByEmail(String email);
}
