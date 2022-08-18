package com.arneca.evyap.ui.adapter.cmx;/*
 * Created by Sinan KUTAS on 8/15/22.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arneca.evyap.R;
import com.arneca.evyap.api.response.cmx.LoginResponse;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuGridAdapter extends RecyclerView.Adapter<MenuGridAdapter.ViewHolder> {

    private  List<LoginResponse.ResultBean.ModulYetkileriBean> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public MenuGridAdapter(Context context, List<LoginResponse.ResultBean.ModulYetkileriBean> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cmxmenu_grid_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myTextView.setText(mData.get(position).getModul_adi());
        Glide.with(context).load(this.mData.get(position).getIcon()).into(holder.imgLogo);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView imgLogo;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.item_title);
            imgLogo = itemView.findViewById(R.id.icon_menu);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.get(id).getTip();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}