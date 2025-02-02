package com.co2app.dao;

import java.util.List;

import com.co2app.model.Country;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class CountryDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Country> getAllCountries() {
        return em.createQuery("SELECT c FROM Country c", Country.class).getResultList();
    }

    public void addCountry(Country country) {
        em.persist(country);
    }

    public void updateCountry(Country country) {
        em.merge(country);
    }

    public void deleteCountry(Long id) {
        Country country = em.find(Country.class, id);
        if (country != null) {
            em.remove(country);
        }
    }

    public Country getById(Long id) {
        return em.find(Country.class, id);
    }

    public void save(Country country) {
        if (country.getId() == null) {
            em.persist(country);
        } else {
            em.merge(country);
        }
    }
}
