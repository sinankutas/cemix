package com.arneca.evyap.api;/*
 * Created by Sinan KUTAS on 27.01.2021.
 */

import java.util.ArrayList;

public class ReportMap {
    private boolean isExpanded;
    private ArrayList<ReportModel> reportModels;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public ArrayList<ReportModel> getReportModels() {
        return reportModels;
    }

    public void setReportModels(ArrayList<ReportModel> reportModels) {
        this.reportModels = reportModels;
    }
}
