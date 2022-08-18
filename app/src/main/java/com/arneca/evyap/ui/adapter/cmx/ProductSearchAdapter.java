package com.arneca.evyap.ui.adapter.cmx;/*
 * Created by Sinan KUTAS on 8/16/22.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arneca.evyap.R;
import com.arneca.evyap.api.response.cmx.OpenDocumentStockListResponse;
import com.arneca.evyap.api.response.cmx.ProductSearchResponse;
import com.arneca.evyap.ui.activity.cmx.RBMatrisActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.ViewHolder> {

    private ProductSearchResponse mData;
    private LayoutInflater mInflater;
    private OpenDocListAdapter.ItemClickListener mClickListener;
    private Context context;
    private String guid = "";
    private String docId = "";
    private String viewTitle = "";
    private boolean isStockActive;

    // data is passed into the constructor
    public ProductSearchAdapter(Context context, ProductSearchResponse data, String guid, String docId, String viewTitle, Boolean isStockActive) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
        this.docId = docId;
        this.guid = guid;
        this.viewTitle = viewTitle;
        this.isStockActive = isStockActive;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ProductSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.product_search_item, parent, false);
        return new ProductSearchAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ProductSearchAdapter.ViewHolder holder, int position) {
        holder.txtCode.setText(String.valueOf(mData.getResult().get(position).getKod()));
        holder.txtName.setText(String.valueOf(mData.getResult().get(position).getAd()));
        holder.txtPaket.setText(String.valueOf(mData.getResult().get(position).getPkadet()));
        holder.txtPrice.setText(String.valueOf(mData.getResult().get(position).getFiyat()));

        if (position!=0){
            holder.txtPaketTitle.setVisibility(View.GONE);
            holder.txtNameTitle.setVisibility(View.GONE);
            holder.txtCodeTitle.setVisibility(View.GONE);
            holder.txtPriceTitle.setVisibility(View.GONE);
        }

        holder.lnrLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RBMatrisActivity.class);
                intent.putExtra("bedenId",String.valueOf(mData.getResult().get(position).getBeden_id()));
                intent.putExtra("guid",guid);
                intent.putExtra("docId",docId);
                intent.putExtra("viewTitle",viewTitle);
                intent.putExtra("isStockActive",isStockActive);
                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.startActivity(intent);
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
        TextView txtPaket,txtName,txtCode,txtPrice,txtPaketTitle,txtNameTitle,txtCodeTitle,txtPriceTitle;
        LinearLayout lnrLyt;


        ViewHolder(View itemView) {
            super(itemView);
            txtCode = itemView.findViewById(R.id.txtCode);
            txtName = itemView.findViewById(R.id.txtName);
            txtPaket = itemView.findViewById(R.id.txtPaket);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtPaketTitle = itemView.findViewById(R.id.txtPaketTitle);
            txtNameTitle = itemView.findViewById(R.id.txtNameTitle);
            txtCodeTitle = itemView.findViewById(R.id.txtCodeTitle);
            txtPriceTitle = itemView.findViewById(R.id.txtPriceTitle);

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