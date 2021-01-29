package com.arneca.evyap.ui.adapter;/*
 * Created by Sinan KUTAS on 25.01.2021.
 */

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.arneca.evyap.R;
import com.arneca.evyap.api.DataModel;
import com.arneca.evyap.api.ReportMap;
import com.arneca.evyap.api.ReportModel;
import com.arneca.evyap.api.response.GetAllLineInfo;
import com.arneca.evyap.helper.AutoFitGridLayoutManager;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.ReportEnum;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class  RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder>  implements RecyclerViewAdapter.ItemListener{

    private GetAllLineInfo lineInfo;
    private ReportMap reportMap;
    private boolean isNormalReportActive;

    public RecAdapter(GetAllLineInfo lines, boolean isNormalReportActive) {
        this.lineInfo = lines;
        this.isNormalReportActive = isNormalReportActive;
    }

    public RecAdapter(ReportMap reportMap, boolean isNormalReportActive) {
        this.reportMap = reportMap;
        this.isNormalReportActive = isNormalReportActive;
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.product_line_item, parent, false);

        return new RecViewHolder(view,parent.getContext());
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {

        if (isNormalReportActive){
            holder.bind(lineInfo, position);
            holder.itemView.setOnClickListener(v -> {
                boolean expanded = lineInfo.isExpanded();
                lineInfo.setExpanded(!expanded);
                notifyItemChanged(position);
            });
        }else{
            holder.bind(reportMap, position);
            holder.itemView.setOnClickListener(v -> {
                boolean expanded = reportMap.isExpanded();
                reportMap.setExpanded(!expanded);
                notifyItemChanged(position);
            });
        }

    }

    @Override
    public int getItemCount() {

        if (isNormalReportActive)
                 return lineInfo.getData() == null ? 0 : lineInfo.getData().size();
        else
            return reportMap.getReportModels() == null ? 0 : reportMap.getReportModels().size();

    }

    public class RecViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private View subItem;
        RecyclerView gridView;
        ArrayList<DataModel> arrayList;
        private  Context context;
        public RecViewHolder(View itemView, Context context) {
            super(itemView);

            this.context = context;
            title = itemView.findViewById(R.id.item_title);
         //   genre = itemView.findViewById(R.id.sub_item_genre);
          //  year = itemView.findViewById(R.id.sub_item_year);
            subItem = itemView.findViewById(R.id.sub_item);



        }


        private void bind(ReportMap reportMap, int position) {
            boolean expanded = reportMap.isExpanded();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);
            title.setText(reportMap.getReportModels().get(position).getReportName());
            title.setTextColor(this.context.getResources().getColor(R.color.grayTextDark));


            gridView = (RecyclerView) itemView.findViewById(R.id.gridView);
            arrayList = new ArrayList<>();

         /*   for (ReportModel reportMapP: reportMap.getReportModels()) {

            }*/

            for (DataModel model:reportMap.getReportModels().get(position).getModels()) {
                arrayList.add(new DataModel(model.title, ""+model.value,false));
            }

            RecyclerViewAdapter adapterGrid = new RecyclerViewAdapter(context, arrayList);
            gridView.setAdapter(adapterGrid);

            /**
             AutoFitGridLayoutManager that auto fits the cells by the column width defined.
             **/

            AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(context, 500);
            gridView.setLayoutManager(layoutManager);
          //  gridView.setBackgroundColor(this.context.getResources().getColor(R.color.red));


            /**
             Simple GridLayoutManager that spans two columns
             **/
            GridLayoutManager manager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
            gridView.setLayoutManager(manager);
        }



        private void bind(GetAllLineInfo lines, int position) {
            boolean expanded = lines.isExpanded();
            boolean isRedColorActive ;

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);
            title.setText(lines.getData().get(position).getLineName());

            if (lines.getData().get(position).getCurrentStopDurationStr().equals("")||lines.getData().get(position).getCurrentStopDurationStr().equals("0")){
                title.setTextColor(this.context.getResources().getColor(R.color.greenText));
                isRedColorActive = false;
            }else{
                title.setTextColor(this.context.getResources().getColor(R.color.redText));
                isRedColorActive = true;
            }

            ArrayList<ReportModel> reportNames = PreferencesHelper.getReportModels();

            reportNames.size();
            gridView = (RecyclerView) itemView.findViewById(R.id.gridView);
            arrayList = new ArrayList<>();

            if (reportNames.get(0).isPrefSelected())
                arrayList.add(new DataModel(reportNames.get(0).getReportName(), ""+lineInfo.getData().get(position).getPreviousShiftScrapAmount(),false));

            if (reportNames.get(1).isPrefSelected())
                arrayList.add(new DataModel(reportNames.get(1).getReportName(), ""+lineInfo.getData().get(position).getCurrentShiftScrapAmount(),false));

            if (reportNames.get(2).isPrefSelected())
                arrayList.add(new DataModel(reportNames.get(2).getReportName(),  ""+lineInfo.getData().get(position).getPreviousShiftTotalProduction(),false));

            if (reportNames.get(3).isPrefSelected())
                arrayList.add(new DataModel(reportNames.get(3).getReportName(), ""+lineInfo.getData().get(position).getCurrentShiftTotalProduction(),false));

            if (reportNames.get(4).isPrefSelected())
                arrayList.add(new DataModel(reportNames.get(4).getReportName(),"% "+lineInfo.getData().get(position).getCurrentShiftOEEStr(),false));

            if (reportNames.get(5).isPrefSelected())
                arrayList.add(new DataModel(reportNames.get(5).getReportName(), " "+lineInfo.getData().get(position).getProductName(),false));

            if (reportNames.get(6).isPrefSelected())
                arrayList.add(new DataModel(reportNames.get(6).getReportName(),""+lineInfo.getData().get(position).getCurrentStopReason(),isRedColorActive));

            if (reportNames.get(7).isPrefSelected())
                arrayList.add(new DataModel(reportNames.get(7).getReportName(), " "+lineInfo.getData().get(position).getCurrentStopDurationStr(),isRedColorActive));

            RecyclerViewAdapter adapterGrid = new RecyclerViewAdapter(context, arrayList);
            gridView.setAdapter(adapterGrid);

            /**
             AutoFitGridLayoutManager that auto fits the cells by the column width defined.
             **/

            AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(context, 500);
            gridView.setLayoutManager(layoutManager);


            /**
             Simple GridLayoutManager that spans two columns
                 **/
        GridLayoutManager manager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
            gridView.setLayoutManager(manager);

        }
    }

    @Override
    public void onItemClick(DataModel item) {

    }
}