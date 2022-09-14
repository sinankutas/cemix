package com.arneca.evyap.ui.adapter.cmx;/*
 * Created by Sinan KUTAS on 8/15/22.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.DocUpdateResponse;
import com.arneca.evyap.api.response.cmx.OpenDocumentListResponse;
import com.arneca.evyap.api.response.cmx.OpenDocumentStockListResponse;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.cmx.OpenDocRecordsActivity;
import com.arneca.evyap.ui.activity.cmx.OpenDocStockListActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class OpenDocStockListAdapter extends RecyclerView.Adapter<OpenDocStockListAdapter.ViewHolder> {

    private OpenDocumentStockListResponse mData;
    private LayoutInflater mInflater;
    private OpenDocListAdapter.ItemClickListener mClickListener;
    private Context context;
    private String guid = "";

    // data is passed into the constructor
    public OpenDocStockListAdapter(Context context, OpenDocumentStockListResponse data, String guid) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
        this.guid = guid;

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

     //   if (position != 0){
            holder.txtCodeTitle.setVisibility(View.GONE);
            holder.txtNameTitle.setVisibility(View.GONE);
            holder.txtColorTitle.setVisibility(View.GONE);
            holder.txtAmountTitle.setVisibility(View.GONE);
       // }
        holder.lnrLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tool.showInfo2action(context,"Bilgi",
                         "Yapmak istediğiniz işlemi seçin",
                        (dialog, which) -> gotoUpdate(position),
                        (dialog, which) -> gotoDeleteDialog(position),"Miktar Güncelle","Satır Sil");
            }
        });
    }

    private void gotoDeleteDialog(int position) {
        Tool.showInfo2action(context,"Bilgi",
                mData.getResult().get(position).getKod()+ "Kodlu stok silinsin mi?",
                (dialog, which) -> gotoDelete(position),
                (dialog, which) -> cancelDialog(),"Satır silinsin mi?","İptal");
    }

    private void gotoDelete(int position) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("guid", guid)
                .addFormDataPart("detay_guid",mData.getResult().get(position).getDetay_guid())
                .build();

        Request.deleteDoc(requestBody, context, response -> {
            DocUpdateResponse docUpdateResponse = ( DocUpdateResponse) response.body();
            response.headers();
            ((OpenDocStockListActivity)context).loadData();
            if (docUpdateResponse!=null){
                Tool.showInfo(context,"Bilgi",
                        docUpdateResponse.getResult_message().getMessage(),
                        (dialog, which) -> cancelDialog(),
                       "Tamam");


            }else{
                Tool.hideDialog();
                 Tool.showInfo(context, "Bilgi", docUpdateResponse.getResult_message().getMessage());
            }
        });
    }

    private void cancelDialog() {
    }

    private void gotoUpdate(int position) {
        openInputDialog(position);
    }

    private void openInputDialog(int position) {
        final EditText txtAmount = new EditText(context);
        txtAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
        txtAmount.setHint(mData.getResult().get(position).getMiktar());
        new AlertDialog.Builder(context)
                .setTitle("Yeni Miktar")
                .setMessage("")
                .setView(txtAmount)
                .setPositiveButton("Güncelle", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String amount = txtAmount.getText().toString();
                        if (amount.length()>0){
                            getUpdateService(position,amount);
                        }else{
                            Tool.showInfo(context, "Uyarı", "Miktar girmelisiniz");
                        }
                    }
                })
                .setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }

    private void getUpdateService(int position, String amount) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("guid", guid)
                .addFormDataPart("detay_guid",mData.getResult().get(position).getDetay_guid())
                .addFormDataPart("miktar",amount)
                .build();

        Request.docUpdate(requestBody, context, response -> {
            DocUpdateResponse docUpdateResponse = ( DocUpdateResponse) response.body();
            response.headers();
            if (docUpdateResponse!=null){
                Tool.showInfo(context, "Bilgi", docUpdateResponse.getResult_message().getMessage());
                ((OpenDocStockListActivity)context).loadData();

            }else{
                Tool.hideDialog();
                Tool.showInfo(context, "Bilgi", docUpdateResponse.getResult_message().getMessage());
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

    public void finishActivity() {

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