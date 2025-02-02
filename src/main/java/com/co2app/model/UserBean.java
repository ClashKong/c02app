package com.co2app.model;

import com.co2app.dao.UserDAO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Named
@SessionScoped
public class UserBean implements Serializable {
    @Inject
    private UserDAO userDAO;

    private User user = new User();
    private boolean loggedIn = false;

    public String register() {
        user.setPassword(hashPassword(user.getPassword())); // Passwort hashen
        userDAO.addUser(user);
        return "login.xhtml?faces-redirect=true"; // Nach Registrierung zur Login-Seite
    }

    public String login() {
        User dbUser = userDAO.findByUsername(user.getUsername());
        if (dbUser != null && dbUser.getPassword().equals(hashPassword(user.getPassword()))) {
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", dbUser);
            return "index.xhtml?faces-redirect=true"; // Weiter zur Hauptseite
        }
        return "login.xhtml?faces-redirect=true"; // Fehlerhafte Anmeldung
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        loggedIn = false;
        return "login.xhtml?faces-redirect=true";
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Fehler beim Hashing", e);
        }
    }

    // Getter & Setter
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public boolean isLoggedIn() { return loggedIn; }
}
