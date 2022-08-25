package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/25/22.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.arneca.evyap.api.response.cmx.NewSayimDetailModel;
import com.arneca.evyap.api.response.cmx.NewSayimModel;
import com.arneca.evyap.api.response.cmx.OpenDocumentStockListResponse;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.adapter.cmx.OpenDocListAdapter;
import com.arneca.evyap.ui.adapter.cmx.OpenDocStockListAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SayimUrunEkleAdapter extends RecyclerView.Adapter<SayimUrunEkleAdapter.ViewHolder> {

    private ArrayList<NewSayimDetailModel> mData;
    private LayoutInflater mInflater;
    private OpenDocListAdapter.ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public SayimUrunEkleAdapter(Context context, ArrayList<NewSayimDetailModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public SayimUrunEkleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cmx_opendocstock_item, parent, false);
        return new SayimUrunEkleAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull SayimUrunEkleAdapter.ViewHolder holder, int position) {
        holder.txtCode.setText(String.valueOf(mData.get(position).getStokKod()));
        holder.txtName.setText(String.valueOf(mData.get(position).getStokAd()));
        holder.txtColor.setText(String.valueOf(mData.get(position).getRenkId()));
        holder.txtAmount.setText(String.valueOf(mData.get(position).getMiktar()));

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
                        (dialog, which) -> gotoDeleteDialog(position),"Mitar Güncelle","Satır Sil");
            }
        });
    }

    private void gotoDeleteDialog(int position) {
        Tool.showInfo2action(context,"Bilgi",
                mData.get(position).getStokKod()+ "Kodlu stok silinsin mi?",
                (dialog, which) -> gotoDelete(position),
                (dialog, which) -> cancelDialog(),"Satır silinsin mi?","İptal");
    }

    private void gotoDelete(int position) {
       // lokal delete
    }

    private void cancelDialog() {
    }

    private void gotoUpdate(int position) {
        openInputDialog(position);
    }

    private void openInputDialog(int position) {
        final EditText txtAmount = new EditText(context);
        txtAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
        txtAmount.setHint(mData.get(position).getMiktar());
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

    }

    // total number of cells
    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        else
            return mData.size();
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
        return mData.get(id).getStokKod();
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
