package com.co2app.model;

import java.io.Serializable;
import java.util.List;

import com.co2app.service.CountryService;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class AdminBean implements Serializable {
    @Inject
    private CountryService countryService;

    private Country country = new Country();
    private List<Country> countries;

    @PostConstruct
    public void init() {
        countries = countryService.getAllCountries();
    }

    public void addCountry() {
        countryService.addCountry(country);
        countries = countryService.getAllCountries();
        country = new Country(); // Zurücksetzen des Formulars
    }

    public void deleteCountry(Long id) {
        countryService.deleteCountry(id);
        countries = countryService.getAllCountries();
    }

    public void updateCountry() {
        countryService.updateCountry(country);
        countries = countryService.getAllCountries();
    }

    // Getter und Setter
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Country> getCountries() {
        return countries;
    }
}
