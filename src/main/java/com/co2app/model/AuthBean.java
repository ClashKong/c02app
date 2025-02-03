package com.co2app.model;

import java.io.Serializable;

import org.mindrot.jbcrypt.BCrypt;

import com.co2app.dao.UserDAO;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class AuthBean implements Serializable {
    @Inject
    private UserDAO userDAO;

    private User currentUser;
    private String username;
    private String password;
    private String roleString; 

    public String login() {
        User user = userDAO.findByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            this.currentUser = user;
            return "index.xhtml?faces-redirect=true";
        }
        return "login.xhtml?error=true";
    }

    public String register() {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        try {
            newUser.setRole(User.Role.valueOf(roleString.toUpperCase()));
        } catch (IllegalArgumentException e) {
            newUser.setRole(User.Role.USER);
        }

        userDAO.save(newUser); // Hier war der Fehler (vorher: userDAO.save(user);)
        return "login.xhtml?faces-redirect=true";
    }

    public String logout() {
        currentUser = null;
        return "login.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public boolean isAdmin() {
        return isLoggedIn() && "ADMIN".equals(currentUser.getRole().name());
    }

    // Getter & Setter
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRoleString() { return roleString; }
    public void setRoleString(String roleString) { this.roleString = roleString; }
    public User getCurrentUser() { return currentUser; }
}
