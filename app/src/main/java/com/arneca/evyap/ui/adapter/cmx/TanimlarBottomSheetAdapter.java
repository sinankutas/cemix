package com.arneca.evyap.ui.adapter.cmx;/*
 * Created by Sinan KUTAS on 8/23/22.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arneca.evyap.R;
import com.arneca.evyap.api.response.cmx.LoginResponse;
import com.arneca.evyap.api.response.cmx.TanimlarResponse;
import com.arneca.evyap.api.response.cmx.TanimlarResultModel;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.ui.fragment.CompanyBottomFragment;
import com.arneca.evyap.ui.fragment.TanimBottomSheetFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TanimlarBottomSheetAdapter extends RecyclerView.Adapter<TanimlarBottomSheetAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private OpenDocListAdapter.ItemClickListener mClickListener;
    public List<TanimlarResultModel> tanimlar = new ArrayList<>();
    private Context context;
    public TanimBottomSheetFragment fragment;

    // data is passed into the constructor
    public TanimlarBottomSheetAdapter(Context context, List<TanimlarResultModel> tanimlar, TanimBottomSheetFragment fragment) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.tanimlar = tanimlar;
        this.fragment = fragment;
    }

    public void setData(List<TanimlarResultModel> tanimlar){
        this.tanimlar.clear();
        this.tanimlar.addAll(tanimlar);
        notifyDataSetChanged();
    }
    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public TanimlarBottomSheetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.company_bottomsheet_item, parent, false);
        return new TanimlarBottomSheetAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull TanimlarBottomSheetAdapter.ViewHolder holder, int position) {
        if (this.tanimlar.size()>0)
          holder.txtCompanyName.setText(this.tanimlar.get(position).getAd());

        holder.lnrLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesHelper.setTanimlarResultModel(tanimlar.get(position));
                fragment.dissmisView();
            }
        });
    }


    // total number of cells
    @Override
    public int getItemCount() {
        if (tanimlar.size()>0)
        return tanimlar.size();
        else
            return 0;
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