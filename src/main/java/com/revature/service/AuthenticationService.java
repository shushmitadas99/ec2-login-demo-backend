package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.exceptions.InvalidLoginException;
import com.revature.model.User;

import java.sql.SQLException;

public class AuthenticationService {

    private UserDao userDao;  //property

    //No-args constructor
    public AuthenticationService(){
        this.userDao = new UserDao(); //UserDao object
    }

    // We create a second parameterized constructor in order to "inject" a mock UserDao object into the service object
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String username, String password) throws SQLException, InvalidLoginException {
        User user = userDao.getUserByUsernameAndPassword(username, password);
        //user could be null or a User object
        //if null, throw InvalidLoginException
        if(user == null){
            throw new InvalidLoginException("Username and/or password is incorrect");
        }

        return user;
    }
}
