package com.co2app.model;

import com.co2app.dao.CountryDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class AdminBean implements Serializable {
    @Inject
    private CountryDAO countryDAO;

    private Country selectedCountry = new Country();

    public List<Country> getCountries() {
        return countryDAO.getAllCountries();
    }

    public void addCountry() {
        countryDAO.addCountry(selectedCountry);
        selectedCountry = new Country(); // Leere das Eingabeformular nach dem Speichern
    }

    public void updateCountry() {
        countryDAO.updateCountry(selectedCountry);
    }

    public void deleteCountry(Long id) {
        countryDAO.deleteCountry(id);
    }

    public Country getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(Country selectedCountry) {
        this.selectedCountry = selectedCountry;
    }
}
