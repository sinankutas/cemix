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
import com.arneca.evyap.api.response.cmx.OpenDocumentListResponse;
import com.arneca.evyap.api.response.cmx.OpenDocumentStockListResponse;
import com.arneca.evyap.ui.activity.cmx.OpenDocStockListActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OpenDocStockListAdapter extends RecyclerView.Adapter<OpenDocStockListAdapter.ViewHolder> {

    private OpenDocumentStockListResponse mData;
    private LayoutInflater mInflater;
    private OpenDocListAdapter.ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public OpenDocStockListAdapter(Context context, OpenDocumentStockListResponse data) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public OpenDocStockListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cmx_opendocstock_item, parent, false);
        return new OpenDocStockListAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull OpenDocStockListAdapter.ViewHolder holder, int position) {
        holder.txtCode.setText(String.valueOf(mData.getResult().get(position).getKod()));
        holder.txtName.setText(String.valueOf(mData.getResult().get(position).getAd()));
        holder.txtColor.setText(String.valueOf(mData.getResult().get(position).getRenk()));
        holder.txtAmount.setText(String.valueOf(mData.getResult().get(position).getMiktar()));

        if (position != 0){
            holder.txtCodeTitle.setVisibility(View.GONE);
            holder.txtNameTitle.setVisibility(View.GONE);
            holder.txtColorTitle.setVisibility(View.GONE);
            holder.txtAmountTitle.setVisibility(View.GONE);
        }
        holder.lnrLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        if (mData.getResult() == null)
            return 0;
        else
          return mData.getResult().size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtCode,txtName,txtColor,txtAmount,txtCodeTitle,txtNameTitle,txtColorTitle,txtAmountTitle;
        LinearLayout lnrLyt;


        ViewHolder(View itemView) {
            super(itemView);
            txtCode = itemView.findViewById(R.id.txtCode);
            txtName = itemView.findViewById(R.id.txtName);
            txtColor = itemView.findViewById(R.id.txtColor);
            txtAmount = itemView.findViewById(R.id.txtAmount);

            txtCodeTitle = itemView.findViewById(R.id.txtCodeTitle);
            txtNameTitle = itemView.findViewById(R.id.txtNameTitle);
            txtColorTitle = itemView.findViewById(R.id.txtColorTitle);
            txtAmountTitle = itemView.findViewById(R.id.txtAmountTitle);

            lnrLyt = itemView.findViewById(R.id.lnrLyt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.getResult().get(id).getAd();
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