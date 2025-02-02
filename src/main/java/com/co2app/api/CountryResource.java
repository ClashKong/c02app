package com.co2app.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import com.co2app.model.Country;
import com.co2app.service.CountryService;
import java.util.List;

@Path("/countries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CountryResource {

    @Inject
    private CountryService countryService;

    @GET
    public List<Country> getCountries() {
        return countryService.getAllCountries();
    }

    @GET
    @Path("/{id}")
    public Country getCountryById(@PathParam("id") Long id) {
        return countryService.getById(id);
    }

    @POST
    public void addCountry(Country country) {
        countryService.addCountry(country);
    }

    @PUT
    public void updateCountry(Country country) {
        countryService.updateCountry(country);
    }

    @DELETE
    @Path("/{id}")
    public void deleteCountry(@PathParam("id") Long id) {
        countryService.deleteCountry(id);
    }
}
