package com.arneca.evyap.ui.adapter.cmx;/*
 * Created by Sinan KUTAS on 8/15/22.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arneca.evyap.R;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.ui.activity.cmx.OpenDocListActivity;
import com.arneca.evyap.ui.activity.cmx.OpenDocRecordsActivity;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class StandartListAdapter extends RecyclerView.Adapter<StandartListAdapter.ViewHolder>{
    private ArrayList<String> listdata;
    private Context context;
    private String activeDocType;

    // RecyclerView recyclerView;
    public StandartListAdapter(Context context, ArrayList<String> listdata) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.cmx_standart_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String myListData = listdata.get(position);
        holder.textView.setText(listdata.get(position));
        holder.lytLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String viewTitle1 = "";
                String viewTitle2 = "";
                if (PreferencesHelper.getActiveDocType().equals("satis")){
                    viewTitle1 = "Satışa Devam";
                    viewTitle2 = "Yeni Satış";
                }else if(PreferencesHelper.getActiveDocType().equals("siparis")){
                    viewTitle1 = "Siparişe Devam";
                    viewTitle2 = "Yeni Sipariş";
                }else if(PreferencesHelper.getActiveDocType().equals("teklif")){
                    viewTitle1 = "Teklife Devam";
                    viewTitle2 = "Yeni Teklife";
                }
                if (position==0){
                    Intent intent = new Intent(context, OpenDocListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("viewTitle",viewTitle1);
                    context.startActivity(intent);
                }else if (position==1){
                    Intent intent = new Intent(context, OpenDocRecordsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("viewTitle",viewTitle2);
                    context.startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public LinearLayout lytLinear;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.txtMenuTitle);
            this.lytLinear = (LinearLayout) itemView.findViewById(R.id.lytLinear);
        }
    }
}
