package com.arneca.evyap.ui.adapter.cmx;/*
 * Created by Sinan KUTAS on 10/10/22.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arneca.evyap.R;
import com.arneca.evyap.api.response.cmx.LoginResponse;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.ui.fragment.CompanyBottomFragment;
import com.arneca.evyap.ui.fragment.CountryBottomFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryBottomSheetAdapter extends RecyclerView.Adapter<CountryBottomSheetAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private OpenDocListAdapter.ItemClickListener mClickListener;
    public List<LoginResponse.ResultBean.UlkelerBean> ulkelerBeans = new ArrayList<>();
    private Context context;
    public CountryBottomFragment fragment;

    // data is passed into the constructor
    public CountryBottomSheetAdapter(Context context, List<LoginResponse.ResultBean.UlkelerBean> ulkelerBeans, CountryBottomFragment fragment) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.ulkelerBeans = ulkelerBeans;
        this.fragment = fragment;
    }

    public void setData(List<LoginResponse.ResultBean.UlkelerBean> ulkelerBeans){
        this.ulkelerBeans.clear();
        this.ulkelerBeans.addAll(ulkelerBeans);
        notifyDataSetChanged();
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public CountryBottomSheetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.company_bottomsheet_item, parent, false);
        return new CountryBottomSheetAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull CountryBottomSheetAdapter.ViewHolder holder, int position) {
        holder.txtCompanyName.setText(this.ulkelerBeans.get(position).getUlke());

        holder.lnrLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment.dissmisView(ulkelerBeans.get(position).getUlke());
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
        return ulkelerBeans.size();
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