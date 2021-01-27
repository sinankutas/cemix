package com.arneca.evyap.helper;/*
 * Created by Sinan KUTAS on 27.01.2021.
 */

import com.arneca.evyap.api.DataModel;
import com.arneca.evyap.api.ReportModel;
import com.arneca.evyap.api.response.GetAllLineInfo;

import java.util.ArrayList;

public class ReportIndex {
    private static ReportIndex singleton = null;

    private ReportIndex()
    {
    }

    public static ReportIndex getInstance()
    {
        if (singleton == null)
            singleton = new ReportIndex();
        return singleton;
    }

    public ArrayList<ReportModel> getReportModel(){
        ReportModel preStrackAmount = new ReportModel();
        preStrackAmount.setReportName(ReportEnum.preStrackAmount.toString());
        ReportModel currStrackAmount = new ReportModel();
        currStrackAmount.setReportName(ReportEnum.currStrackAmount.toString());
        ReportModel prePorduct = new ReportModel();
        prePorduct.setReportName(ReportEnum.prePorduct.toString());
        ReportModel currPorduct = new ReportModel();
        currPorduct.setReportName(ReportEnum.currPorduct.toString());
        ReportModel vardiyaOEE = new ReportModel();
        vardiyaOEE.setReportName(ReportEnum.vardiyaOEE.toString());
        ReportModel prePorduct1 = new ReportModel();
        prePorduct1.setReportName(ReportEnum.prePorduct.toString());
        ReportModel currentStop = new ReportModel();
        currentStop.setReportName(ReportEnum.currentStop.toString());
        ReportModel totalStop = new ReportModel();
        totalStop.setReportName(ReportEnum.totalStop.toString());


        ArrayList<ReportModel> reportModels = new ArrayList<>();
        reportModels.add(preStrackAmount);
        reportModels.add(currStrackAmount);
        reportModels.add(prePorduct);
        reportModels.add(currPorduct);
        reportModels.add(vardiyaOEE);
        reportModels.add(prePorduct1);
        reportModels.add(currentStop);
        reportModels.add(totalStop);

        return reportModels;
    }

    public ArrayList<ReportModel> configureReportModel(GetAllLineInfo lineInfoP) {

        ArrayList<ReportModel> rpList = new ArrayList<>();
        ReportModel rp1 = new ReportModel();
        ReportModel rp2 = new ReportModel();
        ReportModel rp3 = new ReportModel();
        ReportModel rp4 = new ReportModel();
        ReportModel rp5 = new ReportModel();
        ReportModel rp6 = new ReportModel();
        ReportModel rp7 = new ReportModel();
        ReportModel rp8 = new ReportModel();
        rp1.setReportName(ReportEnum.preStrackAmount.toString());
        rp2.setReportName(ReportEnum.currStrackAmount.toString());
        rp3.setReportName(ReportEnum.prePorduct.toString());
        rp4.setReportName(ReportEnum.currPorduct.toString());
        rp5.setReportName(ReportEnum.currPorduct.toString());
        rp6.setReportName(ReportEnum.vardiyaOEE.toString());
        rp7.setReportName(ReportEnum.currentStop.toString());
        rp8.setReportName(ReportEnum.totalStop.toString());
        ArrayList<DataModel> dmList = new ArrayList<>();
        ArrayList<DataModel> dmList1 = new ArrayList<>();
        ArrayList<DataModel> dmList2 = new ArrayList<>();
        ArrayList<DataModel> dmList3 = new ArrayList<>();
        ArrayList<DataModel> dmList4 = new ArrayList<>();
        ArrayList<DataModel> dmList5 = new ArrayList<>();
        ArrayList<DataModel> dmList6 = new ArrayList<>();
        ArrayList<DataModel> dmList7 = new ArrayList<>();
        for (GetAllLineInfo.DataBean lineInfo:lineInfoP.getData()) {
            dmList.add(new DataModel(lineInfo.getLineName(),""+lineInfo.getPreviousShiftScrapAmount(),false));
            dmList1.add(new DataModel(lineInfo.getLineName(),""+lineInfo.getCurrentShiftScrapAmount(),false));
            dmList2.add(new DataModel(lineInfo.getLineName(),""+lineInfo.getPreviousShiftTotalProduction(),false));
            dmList3.add(new DataModel(lineInfo.getLineName(),""+lineInfo.getCurrentShiftTotalProduction(),false));
            dmList4.add(new DataModel(lineInfo.getLineName(),""+lineInfo.getCurrentShiftOEEStr(),false));
            dmList5.add(new DataModel(lineInfo.getLineName(),""+lineInfo.getProductName(),false));
            dmList6.add(new DataModel(lineInfo.getLineName(),""+lineInfo.getCurrentStopReason(),false));
            dmList7.add(new DataModel(lineInfo.getLineName(),""+lineInfo.getCurrentStopDurationStr(),false));
        }
        rp1.setModels(dmList);
        rp2.setModels(dmList1);
        rp3.setModels(dmList2);
        rp4.setModels(dmList3);
        rp5.setModels(dmList4);
        rp6.setModels(dmList5);
        rp7.setModels(dmList6);
        rp8.setModels(dmList7);

        rpList.add(rp1);
        rpList.add(rp2);
        rpList.add(rp3);
        rpList.add(rp4);
        rpList.add(rp5);
        rpList.add(rp6);
        rpList.add(rp7);
        rpList.add(rp8);
        return rpList;
    }
}
