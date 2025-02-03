package com.co2app.service;

import com.co2app.dao.UserDAO;
import com.co2app.model.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;

    public void save(User user) {
        userDAO.save(user);
    }

    public void update(User user) {
        userDAO.update(user);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userDAO.findByUsernameAndPassword(username, password);
    }

    public List<User> getAll() {
        return userDAO.getAll();
    }
}
