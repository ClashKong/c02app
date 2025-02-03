package com.co2app.model;

import java.io.Serializable;
import java.util.List;

import com.co2app.service.UserService;

import jakarta.enterprise.context.SessionScoped; // Falls nicht vorhanden, hinzugefügt
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class UserBean implements Serializable {

    private User user = new User();
    private List<User> userList;

    @Inject
    private UserService userService;

    public String login() {
        User loggedInUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            return "dashboard.xhtml?faces-redirect=true";
        }
        return "login.xhtml?error=true";
    }

    public String register() {
        userService.save(user);
        return "login.xhtml?faces-redirect=true";
    }

    public void updateUser() {
        userService.update(user);
    }

    public List<User> getAllUsers() {
        return userService.getAll();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getter für userList, damit die Variable verwendet wird
    public List<User> getUserList() {
        if (userList == null) {
            userList = userService.getAll();
        }
        return userList;
    }
}
