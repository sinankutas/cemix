package com.arneca.evyap.ui.adapter;/*
 * Created by Sinan KUTAS on 25.01.2021.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arneca.evyap.R;
import com.arneca.evyap.api.DataModel;
import com.arneca.evyap.api.Movie;
import com.arneca.evyap.api.response.GetAllLineInfo;
import com.arneca.evyap.api.response.GetLines;
import com.arneca.evyap.helper.AutoFitGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class  RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder>  implements RecyclerViewAdapter.ItemListener{

    private GetAllLineInfo lineInfo;


    public RecAdapter(GetAllLineInfo lines) {
        this.lineInfo = lines;
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

        holder.bind(lineInfo, position);

        holder.itemView.setOnClickListener(v -> {
            boolean expanded = lineInfo.isExpanded();
            lineInfo.setExpanded(!expanded);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return lineInfo.getData() == null ? 0 : lineInfo.getData().size();
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

                     gridView = (RecyclerView) itemView.findViewById(R.id.gridView);
            arrayList = new ArrayList<>();

            arrayList.add(new DataModel("Önceki Fire Miktarı", ""+lineInfo.getData().get(position).getPreviousShiftScrapAmount(),false));
            arrayList.add(new DataModel("Anlık Makine Firesi", ""+lineInfo.getData().get(position).getCurrentShiftScrapAmount(),false));
            arrayList.add(new DataModel("Önceki Vardiya Üretim",  ""+lineInfo.getData().get(position).getPreviousShiftTotalProduction(),false));
            arrayList.add(new DataModel("Anlık Net Üretim", ""+lineInfo.getData().get(position).getCurrentShiftTotalProduction(),false));
            arrayList.add(new DataModel("Vardiya OEE","% "+lineInfo.getData().get(position).getCurrentShiftOEEStr(),false));
            arrayList.add(new DataModel("Çalışılan ürün", " "+lineInfo.getData().get(position).getProductName(),false));
            arrayList.add(new DataModel("Mevcut Duruş",""+lineInfo.getData().get(position).getCurrentStopReason(),isRedColorActive));
            arrayList.add(new DataModel("Duruş Süresi", " "+lineInfo.getData().get(position).getCurrentStopDurationStr(),isRedColorActive));

            RecyclerViewAdapter adapterGrid = new RecyclerViewAdapter(context, arrayList);
            gridView.setAdapter(adapterGrid);

            /**
             AutoFitGridLayoutManager that auto fits the cells by the column width defined.
             **/

            AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(context, 500);
            gridView.setLayoutManager(layoutManager);
            gridView.setBackgroundColor(this.context.getResources().getColor(R.color.red));


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