package com.co2app.service;

import java.util.List;

import com.co2app.dao.CountryDAO;
import com.co2app.model.Country;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class CountryService {

    @Inject
    private CountryDAO countryDAO;

    public List<Country> getAllCountries() {
        return countryDAO.getAll();
    }

    public void addCountry(Country country) {
        countryDAO.save(country);
    }

    public void updateCountry(Country country) {
        countryDAO.update(country);
    }

    public void deleteCountry(Long id) {
        countryDAO.delete(id);
    }
    public Country getById(Long id) {
        return countryDAO.findById(id);
    }
    
}
