package com.co2app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ChartBean implements Serializable {
    private BarChartModel barModel;
    private PieChartModel pieModel;

    @PostConstruct
    public void init() {
        createBarModel();
        createPieModel();
    }

    private void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet dataSet = new BarChartDataSet();
        dataSet.setLabel("CO₂-Emissionen");

        List<Number> values = new ArrayList<>();
        values.add(800);
        values.add(1600);
        values.add(2500);
        values.add(1200);
        dataSet.setData(values);

        List<String> labels = new ArrayList<>();
        labels.add("Deutschland");
        labels.add("USA");
        labels.add("China");
        labels.add("Indien");

        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        barModel.setData(data);
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(800);
        values.add(1600);
        values.add(2500);
        values.add(1200);
        dataSet.setData(values);

        List<String> labels = new ArrayList<>();
        labels.add("Deutschland");
        labels.add("USA");
        labels.add("China");
        labels.add("Indien");

        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        pieModel.setData(data);
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }
}
