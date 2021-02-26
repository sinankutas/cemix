package com.arneca.evyap.api;/*
 * Created by Sinan KUTAS on 27.01.2021.
 */

import java.util.ArrayList;

public class ReportModel {
    String reportName;
    String reportId;
    ArrayList<DataModel> models;
    boolean isPrefSelected;
    private boolean isExpanded;
    public String getReportName() {
        return reportName;
    }

    public String getReportId() {
        if (reportId == null)
            reportId = "0423423423";
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
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

    public boolean isPrefSelected() {
        return isPrefSelected;
    }

    public void setPrefSelected(boolean prefSelected) {
        isPrefSelected = prefSelected;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
