package com.arneca.evyap.helper;/*
 * Created by Sinan KUTAS on 27.01.2021.
 */

import com.arneca.evyap.api.DataModel;
import com.arneca.evyap.api.ReportModel;
import com.arneca.evyap.api.response.GetAllLineInfo;

import java.util.ArrayList;
import java.util.List;

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
        ReportModel porduct1 = new ReportModel();
        porduct1.setReportName(ReportEnum.product.toString());
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
        reportModels.add(porduct1);
        reportModels.add(currentStop);
        reportModels.add(totalStop);

        return reportModels;
    }

    public ArrayList<ReportModel> configureReportModel(List<GetAllLineInfo.DataBean.MyArrayListBean> lineInfoP) {

        ArrayList<ReportModel> reportNames = PreferencesHelper.getReportModels();

        ArrayList<DataModel> dmList = new ArrayList<>();
        ArrayList<DataModel> dmList1 = new ArrayList<>();
        ArrayList<DataModel> dmList2 = new ArrayList<>();
        ArrayList<DataModel> dmList3 = new ArrayList<>();
        ArrayList<DataModel> dmList4 = new ArrayList<>();
        ArrayList<DataModel> dmList5 = new ArrayList<>();
        ArrayList<DataModel> dmList6 = new ArrayList<>();
        ArrayList<DataModel> dmList7 = new ArrayList<>();

        for (GetAllLineInfo.DataBean.MyArrayListBean lineInfo:lineInfoP) {
            dmList.add(new DataModel(lineInfo.getMap().getLineName(),""+lineInfo.getMap().getPreviousShiftScrapAmount(),false));
            dmList1.add(new DataModel(lineInfo.getMap().getLineName(),""+lineInfo.getMap().getCurrentShiftScrapAmount(),false));
            dmList2.add(new DataModel(lineInfo.getMap().getLineName(),""+lineInfo.getMap().getPreviousShiftTotalProduction(),false));
            dmList3.add(new DataModel(lineInfo.getMap().getLineName(),""+lineInfo.getMap().getCurrentShiftTotalProduction(),false));
            dmList4.add(new DataModel(lineInfo.getMap().getLineName(),""+lineInfo.getMap().getCurrentShiftOEEStr(),false));
            dmList5.add(new DataModel(lineInfo.getMap().getLineName(),""+lineInfo.getMap().getProductName(),false));
            dmList6.add(new DataModel(lineInfo.getMap().getLineName(),""+lineInfo.getMap().getCurrentStopReason(),false));
            dmList7.add(new DataModel(lineInfo.getMap().getLineName(),""+lineInfo.getMap().getCurrentStopDurationStr(),false));
        }


        reportNames.get(0).setModels(dmList);
        reportNames.get(1).setModels(dmList1);
        reportNames.get(2).setModels(dmList2);
        reportNames.get(3).setModels(dmList3);
        reportNames.get(4).setModels(dmList4);
        reportNames.get(5).setModels(dmList5);
        reportNames.get(6).setModels(dmList6);
        reportNames.get(7).setModels(dmList7);

        return reportNames;
    }
}
