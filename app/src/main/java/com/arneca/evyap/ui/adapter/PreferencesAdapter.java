package com.arneca.evyap.ui.adapter;/*
 * Created by sinan KUTAS on 28.01.2021.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arneca.evyap.R;
import com.arneca.evyap.api.ReportModel;
import com.arneca.evyap.helper.ReportIndex;
import com.arneca.evyap.ui.activity.PreferencesActivity;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PreferencesAdapter extends RecyclerView.Adapter<PreferencesAdapter.RecViewHolder>  {

private ArrayList<ReportModel> remportNames = ReportIndex.getInstance().getReportModel();
    private Context context;

    public PreferencesAdapter(Context context) {
        this.context = context;
    }


    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.preferences_item, parent, false);

        return new RecViewHolder(view,parent.getContext());
        }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {

        holder.bind(remportNames, position);
        holder.itemView.setOnClickListener(v -> {
            if (remportNames.get(position).isPrefSelected()){
                remportNames.get(position).setPrefSelected(false);
                ((PreferencesActivity)context).changeTotalSelection(false);
            }else{
                remportNames.get(position).setPrefSelected(true);
                ((PreferencesActivity)context).changeTotalSelection(true);
            }
             notifyItemChanged(position);
        });
}


    @Override
    public int getItemCount() {
     return remportNames == null ? 0 : remportNames.size();
  }

  public class RecViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private LinearLayout lytBack;
    private Context context;
    public RecViewHolder(View itemView, Context context) {
        super(itemView);

        this.context = context;
        title = itemView.findViewById(R.id.item_title);
        lytBack = itemView.findViewById(R.id.lytBack);

    }


    private void bind(ArrayList<ReportModel> remportNames, int position) {
        title.setText(remportNames.get(position).getReportName());

        if (remportNames.get(position).isPrefSelected()){
            title.setTextColor(this.context.getResources().getColor(R.color.white));
            lytBack.setBackground(context.getResources().getDrawable(R.drawable.circle_darkblue_back));
        }else{
            title.setTextColor(this.context.getResources().getColor(R.color.grayTextDark));
            lytBack.setBackground(context.getResources().getDrawable(R.drawable.circle_row_item));
        }
    }

    }
}