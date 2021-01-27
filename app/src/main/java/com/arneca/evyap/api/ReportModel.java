package com.arneca.evyap.api;/*
 * Created by Sinan KUTAS on 27.01.2021.
 */

import java.util.ArrayList;

public class ReportModel {
    String reportName;
    ArrayList<DataModel> models;


    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public ArrayList<DataModel> getModels() {
        return models;
    }

    public void setModels(ArrayList<DataModel> models) {
        this.models = models;
    }
}
