package com.cooper.mall.service.ProductServiceImpl;

import com.cooper.mall.dao.UserDao;
import com.cooper.mall.dto.UserRegisterRequest;
import com.cooper.mall.model.User;
import com.cooper.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServerImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }


}
