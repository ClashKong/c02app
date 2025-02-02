package com.co2app.model;

import java.io.Serializable;
import java.util.List;

import com.co2app.dao.CountryDAO;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class CountryBean implements Serializable {
    @Inject
    private CountryDAO countryDAO;

    private Country selectedCountry;

    public List<Country> getAllCountries() {
        return countryDAO.getAllCountries();
    }

    public Country getSelectedCountry() {
        if (selectedCountry == null) {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (id != null) {
                selectedCountry = countryDAO.getById(Long.valueOf(id));
            }
        }
        return selectedCountry;
    }
}
