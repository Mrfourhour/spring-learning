package com.xinyet.service;

import com.xinyet.dao.UserDao;
import com.xinyet.dao.UserDaoImpl;
import com.xinyet.dao.UserDaoMysqlImpl;

public class UserServiceImpl implements UserService {

//    private UserDao userDao = new UserDaoImpl();
//    private UserDao userDao = new UserDaoMysqlImpl();
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }
}
