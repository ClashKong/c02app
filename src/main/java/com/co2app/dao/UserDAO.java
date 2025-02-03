package com.co2app.dao;

import java.util.List;

import com.co2app.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.getTransaction().begin();
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        em.getTransaction().commit();
    }

    public void update(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public User getUserById(Long userId) {
        return em.find(User.class, userId);
    }

    public List<User> getAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User findByUsernameAndPassword(String username, String password) {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public User findByUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
