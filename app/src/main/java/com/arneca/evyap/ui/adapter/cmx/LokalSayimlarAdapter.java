package com.arneca.evyap.ui.adapter.cmx;/*
 * Created by Sinan KUTAS on 8/25/22.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.DocUpdateResponse;
import com.arneca.evyap.api.response.cmx.LoginResponse;
import com.arneca.evyap.api.response.cmx.NewSayimModel;
import com.arneca.evyap.api.response.cmx.OpenDocumentListResponse;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.cmx.OpenDocListActivity;
import com.arneca.evyap.ui.activity.cmx.OpenDocStockListActivity;
import com.arneca.evyap.ui.activity.cmx.SayimUrunEkleActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class LokalSayimlarAdapter extends RecyclerView.Adapter<LokalSayimlarAdapter.ViewHolder>  {

    private ArrayList<NewSayimModel> mData;
    private LayoutInflater mInflater;
    private OpenDocListAdapter.ItemClickListener mClickListener;
    private Context context;
    private String viewTitle;
    // data is passed into the constructor
    public LokalSayimlarAdapter(Context context, ArrayList<NewSayimModel> data, String viewTitle) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
        this.viewTitle = viewTitle;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public LokalSayimlarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cmx_opendoc_item, parent, false);
        return new LokalSayimlarAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull LokalSayimlarAdapter.ViewHolder holder, int position) {
        holder.txtDoc.setText(String.valueOf(mData.get(position).getId()));
        holder.txtSeri.setText(String.valueOf(mData.get(position).getDesc()));
        holder.txtKNumber.setText(String.valueOf(mData.get(position).getIdx()).substring(0,4)+"...");
        holder.txtDate.setText(String.valueOf(mData.get(position).getSubeCode()));
        holder.lytAmount.setVisibility(View.INVISIBLE);

        //     if (position!=0){
        holder. txtStockTitle.setVisibility(View.GONE);
        holder. txtOrderNoTitle.setVisibility(View.GONE);
        holder. txtKNumberTitle.setVisibility(View.GONE);
        holder. txtSeriTitle.setVisibility(View.GONE);
        holder. txtDocTitle.setVisibility(View.GONE);
        holder. txtDateTitle.setVisibility(View.GONE);
        //   }

        holder.lnrLyt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Tool.showInfo2action(context,"Bilgi",
                        mData.get(position).getId()+ " Belge Id' li satır silinsin mi?",
                        (dialog, which) -> gotoDelete(position),
                        (dialog, which) -> cancelDialog(),"Emin misiniz?","İptal");
                return false;
            }
        });

        holder.lnrLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SayimUrunEkleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("viewTitle", viewTitle);
                intent.putExtra("currentId", ""+mData.get(position).getId()); // yeni liste olduğu için boş yolla
                context.startActivity(intent);
                // goto lokal add product
           /*     Intent intent = new Intent(context, OpenDocStockListActivity.class);
                intent.putExtra("guid",String.valueOf(mData.getResult().get(position).getGuid()));
                intent.putExtra("docId",String.valueOf(mData.getResult().get(position).getBelge_id()));
                intent.putExtra("orderNo",String.valueOf(mData.getResult().get(position).getSira()));
                intent.putExtra("seriNo",String.valueOf(mData.getResult().get(position).getSeri()));
                intent.putExtra("cariKod",String.valueOf(mData.getResult().get(position).getCari_kod()));
                intent.putExtra("viewTitle",viewTitle);

                for (LoginResponse.ResultBean.CarilerBean carilerBean : PreferencesHelper.getLoginResponse().getResult().getCariler()){
                    if (carilerBean.getKod().equals(String.valueOf(mData.getResult().get(position).getCari_kod()))){
                        PreferencesHelper.setSelectedCompany(carilerBean);
                    }
                }

                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.startActivity(intent);*/
            }
        });
    }



    private void cancelDialog() {
    }

    private void gotoDelete(int position) {

    }

    // total number of cells
    @Override
    public int getItemCount() {
        if (mData!=null)
            return mData.size();
        else
            return 0;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtDoc,txtSeri,txtKNumber,txtDate,txtOrderNo,txtStock,
                txtStockTitle,txtOrderNoTitle,txtDateTitle,txtKNumberTitle,txtSeriTitle,txtDocTitle;
        LinearLayout lnrLyt;
        LinearLayout lytAmount;


        ViewHolder(View itemView) {
            super(itemView);
            txtDoc = itemView.findViewById(R.id.txtDocId);
            txtSeri = itemView.findViewById(R.id.txtSeriId);
            txtKNumber = itemView.findViewById(R.id.txtKNumberId);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtOrderNo = itemView.findViewById(R.id.txtOrderNo);
            txtStock = itemView.findViewById(R.id.txtStock);
            txtStockTitle = itemView.findViewById(R.id.txtStockTitle);
            txtOrderNoTitle = itemView.findViewById(R.id.txtOrderNoTitle);
            txtDateTitle = itemView.findViewById(R.id.txtDateTitle);
            txtKNumberTitle = itemView.findViewById(R.id.txtKNumberIdTitle);
            txtSeriTitle = itemView.findViewById(R.id.txtSeriIdTitle);
            txtDocTitle = itemView.findViewById(R.id.txtDocIdTitle);
            lnrLyt = itemView.findViewById(R.id.lnrLyt);
            lytAmount = itemView.findViewById(R.id.lytAmount);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.get(id).getSubeCode();
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
