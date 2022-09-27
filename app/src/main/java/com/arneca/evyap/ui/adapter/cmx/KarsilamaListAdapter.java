package com.arneca.evyap.ui.adapter.cmx;/*
 * Created by Sinan KUTAS on 9/20/22.
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
import com.arneca.evyap.api.response.cmx.KarsilamaResponse;
import com.arneca.evyap.api.response.cmx.LoginResponse;
import com.arneca.evyap.api.response.cmx.OpenDocumentListResponse;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.cmx.KarsilamaDetayActivity;
import com.arneca.evyap.ui.activity.cmx.OpenDocListActivity;
import com.arneca.evyap.ui.activity.cmx.OpenDocStockListActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class KarsilamaListAdapter extends RecyclerView.Adapter<KarsilamaListAdapter.ViewHolder>  {

    private KarsilamaResponse mData;
    private LayoutInflater mInflater;
    private OpenDocListAdapter.ItemClickListener mClickListener;
    private Context context;
    private String viewTitle;
    // data is passed into the constructor
    public KarsilamaListAdapter(Context context, KarsilamaResponse data, String viewTitle) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
        this.viewTitle = viewTitle;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public KarsilamaListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.karsilama_list_item, parent, false);
        return new KarsilamaListAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull KarsilamaListAdapter.ViewHolder holder, int position) {
        holder.txtDepo.setText(String.valueOf(mData.getResult().get(position).getHedef_depo()));
        holder.txtSeri.setText(String.valueOf(mData.getResult().get(position).getSeri()));
        holder.txtDate.setText(String.valueOf(mData.getResult().get(position).getTarih()));
        holder.txtSira.setText(String.valueOf(mData.getResult().get(position).getSira()));



        holder.lnrLyt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
            /*    Tool.showInfo2action(context,"Bilgi",
                        mData.getResult().get(position).getBelge_id()+ " Belge Id' li satır silinsin mi?",
                        (dialog, which) -> gotoDelete(position),
                        (dialog, which) -> cancelDialog(),"Emin misiniz?","İptal");*/
                return false;
            }
        });

        holder.lnrLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, KarsilamaDetayActivity.class);
                intent.putExtra("seri",String.valueOf(mData.getResult().get(position).getSeri()));
                intent.putExtra("sira",String.valueOf(mData.getResult().get(position).getSira()));
                intent.putExtra("adet",String.valueOf(mData.getResult().get(position).getAdet()));
                intent.putExtra("sayi",String.valueOf(mData.getResult().get(position).getSayi()));
                intent.putExtra("subeName",String.valueOf(mData.getResult().get(position).getHedef_depo()));
                viewTitle = "Karşılama Detay";
                intent.putExtra("viewTitle",viewTitle);
                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.startActivity(intent);
            }
        });
    }



    private void cancelDialog() {
    }

    private void gotoDelete(int position) {
     /*   RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("guid", mData.getResult().get(position).getGuid())
                .build();

        Request.deleteDocFromUplist(requestBody, context, response -> {
            DocUpdateResponse docUpdateResponse = ( DocUpdateResponse) response.body();
            response.headers();
            ((OpenDocListActivity)context).loadData(true);
            if (docUpdateResponse!=null){
                Tool.showInfo(context,"Bilgi",
                        docUpdateResponse.getResult_message().getMessage(),
                        (dialog, which) -> cancelDialog(),
                        "Tamam");


            }else{
                Tool.hideDialog();
                Tool.showInfo(context, "Bilgi", docUpdateResponse.getResult_message().getMessage());
            }
        });*/
    }

    // total number of cells
    @Override
    public int getItemCount() {
        if (mData.getResult()!=null)
            return mData.getResult().size();
        else
            return 0;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtSira,txtSeri,txtDate,txtDepo;
        LinearLayout lnrLyt;


        ViewHolder(View itemView) {
            super(itemView);
            txtSeri = itemView.findViewById(R.id.txtSeri);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtDepo = itemView.findViewById(R.id.txtDepo);
            txtSira = itemView.findViewById(R.id.txtSira);
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
        return mData.getResult().get(id).getHedef_depo();
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