package com.arneca.evyap.ui.adapter.cmx;/*
 * Created by Sinan KUTAS on 8/23/22.
 */

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arneca.evyap.R;
import com.arneca.evyap.api.response.cmx.RBMatrisResponse;
import com.arneca.evyap.api.response.cmx.TanimlarResponse;
import com.arneca.evyap.api.response.cmx.TanimlarResultModel;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.ui.activity.cmx.LocalRBMatrisActivity;
import com.arneca.evyap.ui.activity.cmx.RBMatrisActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LocalRBMatrisAdapter extends RecyclerView.Adapter<LocalRBMatrisAdapter.ViewHolder> {

    private List<TanimlarResultModel> mData;
    private LayoutInflater mInflater;
    private OpenDocListAdapter.ItemClickListener mClickListener;
    private Context context;
    private int currentIndex;
    private String fiyat,stokAd;
    JSONArray jsonArray = PreferencesHelper.getJsonArrayForLocalMatris();
    private boolean isStockActive;
    // data is passed into the constructor
    public LocalRBMatrisAdapter(Context context, List<TanimlarResultModel> data, int currentIndex, boolean isStockActive,String fiyat,String stokAd) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
        this.currentIndex = currentIndex;
        this.isStockActive = isStockActive;
        this.fiyat = fiyat;
        this.stokAd = stokAd;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public LocalRBMatrisAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rbmatris_item, parent, false);
        return new LocalRBMatrisAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull LocalRBMatrisAdapter.ViewHolder holder, int position) {
        holder.txtColor.setText(String.valueOf(mData.get(position).getRenk()));
        holder.txtDP1.setText(String.valueOf(mData.get(position).getD1()));
        holder.txtDP2.setText(String.valueOf(mData.get(position).getD14()));
        holder.txtDP3.setText(String.valueOf(mData.get(position).getD89()));
        holder.edtAmount.setHint(String.valueOf("0"));
        String color = "#FFFFFF";
     /*   String color = "";
        if (String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getRenk()).length()==0){
            color = "#FFFFFF";
        }else{
            color = "#"+String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getRenk()).replace(" ","");
        }*/

        int decodedColor = Color.parseColor(color);
        holder.img.setBackgroundColor(decodedColor);
        if (isStockActive){
            holder.edtAmount.setVisibility(View.INVISIBLE);
            holder.txtamountTitle.setVisibility(View.INVISIBLE);
        }

        holder.edtAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                }
            }
        });




        ((LocalRBMatrisActivity)context).showSoftKeyboard(holder.edtAmount);
        JSONObject obj = new JSONObject();
        holder.edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    String amountFromEditable = editable.toString();
                    if (amountFromEditable.equals("")){
                        amountFromEditable = "0";
                    }
                    mData.get(position).setMiktar(Integer.parseInt(amountFromEditable));


                    boolean isfounded = false;
                    int existingIndex = 0;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject row = jsonArray.getJSONObject(i);
                        if( String.valueOf(mData.get(position).getId()).equals(row.get("StokIdx"))){
                            isfounded = true;
                            existingIndex = i;
                            break;
                        }
                    }

                    if (isfounded){
                        jsonArray.remove(existingIndex);
                    }
                    obj.put("StokKodu", String.valueOf(mData.get(position).getKod()));
                    obj.put("StokIdx", String.valueOf(mData.get(position).getId()));
                    obj.put("RenkId", String.valueOf(mData.get(position).getRenk_id()));
                    obj.put("Miktar", ""+amountFromEditable);
                    obj.put("Fiyat", ""+fiyat);
                    obj.put("Dvz", "1");
                    obj.put("StokAdı", stokAd);
                    obj.put("Renk", mData.get(position).getRenk());

                    if (!amountFromEditable.equals("0"))
                      jsonArray.put(obj);

                    ((LocalRBMatrisActivity)context).setJsonArray(jsonArray);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

     //   if (position!=0){
            holder.txtColorTitle.setVisibility(View.INVISIBLE);
            holder.txtamountTitle.setVisibility(View.INVISIBLE);
            holder.txtDP3Title.setVisibility(View.INVISIBLE);
            holder.txtDP2Title.setVisibility(View.INVISIBLE);
            holder.txtDP1Title.setVisibility(View.INVISIBLE);
            holder.txtKNumberIdTitle.setVisibility(View.INVISIBLE);
    //    }
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
        if (mData != null)
            return mData.size();
        else
            return 1;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtColor,txtDP1,txtDP2,txtDP3,txtColorTitle,txtamountTitle,txtDP3Title,txtDP2Title,txtDP1Title,txtKNumberIdTitle;
        TextView img ;
        EditText edtAmount;
        LinearLayout lnrLyt;


        ViewHolder(View itemView) {
            super(itemView);
            txtColor = itemView.findViewById(R.id.txtColor);
            txtDP1 = itemView.findViewById(R.id.txtDP1);
            txtDP2 = itemView.findViewById(R.id.txtDP2);
            txtDP3 = itemView.findViewById(R.id.txtDP3);
            edtAmount = itemView.findViewById(R.id.edtAmount);
            txtColorTitle = itemView.findViewById(R.id.txtColorTitle);
            txtamountTitle = itemView.findViewById(R.id.txtamountTitle);
            txtDP3Title = itemView.findViewById(R.id.txtDP3Title);
            txtDP2Title = itemView.findViewById(R.id.txtDP2Title);
            txtDP1Title = itemView.findViewById(R.id.txtDP1Title);
            txtKNumberIdTitle = itemView.findViewById(R.id.txtKNumberIdTitle);
            img = itemView.findViewById(R.id.imgColor);
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
        return mData.get(id).getAd();
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