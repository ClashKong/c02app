package com.co2app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;

import com.co2app.dao.CountryDAO;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ChartBean implements Serializable {
    @Inject
    private CountryDAO countryDAO;

    private BarChartModel barModel;

    public BarChartModel getBarModel() {
        if (barModel == null) {
            barModel = new BarChartModel();
            ChartData data = new ChartData();
            BarChartDataSet dataSet = new BarChartDataSet();
            
            List<Number> values = new ArrayList<>();
            List<String> labels = new ArrayList<>();

            countryDAO.getAllCountries().forEach(country -> {
                values.add(country.getCo2Emission());
                labels.add(country.getName());
            });

            dataSet.setData(values);
            dataSet.setLabel("CO₂-Emissionen");
            data.addChartDataSet(dataSet);
            data.setLabels(labels);

            barModel.setData(data);
        }
        return barModel;
    }
}
