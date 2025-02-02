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

    public void addCountry(Country country) {
        countryDAO.save(country);
    }

    public List<Country> getAllCountries() {
        return countryDAO.getAllCountries();
    }

    public Country getById(Long id) {  // NEUE METHODE
        return countryDAO.getById(id);
    }

    public void updateCountry(Country country) {  // NEUE METHODE
        countryDAO.updateCountry(country);
    }

    public void deleteCountry(Long id) {  // NEUE METHODE
        countryDAO.deleteCountry(id);
    }
}
