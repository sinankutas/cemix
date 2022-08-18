package com.arneca.evyap.ui.adapter.cmx;/*
 * Created by Sinan KUTAS on 8/15/22.
 */

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arneca.evyap.R;
import com.arneca.evyap.api.response.cmx.LoginResponse;
import com.arneca.evyap.api.response.cmx.OpenDocumentListResponse;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.ui.activity.cmx.OpenDocStockListActivity;
import com.arneca.evyap.ui.fragment.CompanyBottomFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CompanyBottomSheetAdapter extends RecyclerView.Adapter<CompanyBottomSheetAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private OpenDocListAdapter.ItemClickListener mClickListener;
    public List<LoginResponse.ResultBean.CarilerBean> carilerBeans = new ArrayList<>();
    private Context context;
    public CompanyBottomFragment fragment;

    // data is passed into the constructor
    public CompanyBottomSheetAdapter(Context context, List<LoginResponse.ResultBean.CarilerBean> carilerBeans, CompanyBottomFragment fragment) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.carilerBeans = carilerBeans;
        this.fragment = fragment;
    }

    public void setData(List<LoginResponse.ResultBean.CarilerBean> carilerBeans){
        this.carilerBeans.clear();
        this.carilerBeans.addAll(carilerBeans);
        notifyDataSetChanged();
    }
    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public CompanyBottomSheetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.company_bottomsheet_item, parent, false);
        return new CompanyBottomSheetAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull CompanyBottomSheetAdapter.ViewHolder holder, int position) {
        holder.txtCompanyName.setText(this.carilerBeans.get(position).getAd());

         holder.lnrLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesHelper.setSelectedCompany(carilerBeans.get(position));
                fragment.dissmisView();
              /*  Intent intent = new Intent(context, OpenDocStockListActivity.class);
                intent.putExtra("guid",String.valueOf(mData.getResult().get(position).getGuid()));
                intent.putExtra("orderNo",String.valueOf(mData.getResult().get(position).getSira()));
                intent.putExtra("seriNo",String.valueOf(mData.getResult().get(position).getSeri()));
                intent.putExtra("docId",String.valueOf(mData.getResult().get(position).getBelge_id()));
                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.startActivity(intent);*/
            }
        });
    }


    // total number of cells
    @Override
    public int getItemCount() {
        return PreferencesHelper.getLoginResponse().getResult().getCariler().size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtCompanyName;
        LinearLayout lnrLyt;


        ViewHolder(View itemView) {
            super(itemView);
            txtCompanyName = itemView.findViewById(R.id.txtCompanyName);
            lnrLyt = itemView.findViewById(R.id.lytMain);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return PreferencesHelper.getLoginResponse().getResult().getCariler().get(id).getAd();
    }

    // allows clicks events to be caught
    public void setClickListener(OpenDocListAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}