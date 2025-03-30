package com.cooper.mall.service.ProductServiceImpl;

import com.cooper.mall.dao.UserDao;
import com.cooper.mall.dto.UserRegisterRequest;
import com.cooper.mall.model.User;
import com.cooper.mall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServerImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServerImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        // 檢查註冊的email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        if (user != null){
            log.warn("該 email {} 已被註冊", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //創建帳號
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }


}
