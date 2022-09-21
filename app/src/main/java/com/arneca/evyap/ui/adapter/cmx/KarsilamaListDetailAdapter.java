package com.arneca.evyap.ui.adapter.cmx;/*
 * Created by Sinan KUTAS on 9/20/22.
 */

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arneca.evyap.R;
import com.arneca.evyap.api.response.cmx.KarsilamaDetailResponse;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.ui.activity.cmx.KarsilamaDetayActivity;
import com.arneca.evyap.ui.activity.cmx.RBMatrisActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KarsilamaListDetailAdapter extends RecyclerView.Adapter<KarsilamaListDetailAdapter.ViewHolder>  {

    private KarsilamaDetailResponse mData;
    private LayoutInflater mInflater;
    private OpenDocListAdapter.ItemClickListener mClickListener;
    private Context context;
    private String viewTitle;
    private String seri = "";
    private String sira = "";
    ArrayList<Integer> selectedItems = new ArrayList<>();
    private boolean isLongClickActive = false;
    // data is passed into the constructor
    public KarsilamaListDetailAdapter(Context context, KarsilamaDetailResponse data, String viewTitle, String seri, String sira) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
        this.viewTitle = viewTitle;
        this.seri = seri;
        this.sira = sira;
        selectedItems = new ArrayList<>();
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public KarsilamaListDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.karsilama_detail_list_item, parent, false);
        return new KarsilamaListDetailAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull KarsilamaListDetailAdapter.ViewHolder holder, int position) {
       holder.txtStockAdi.setText(String.valueOf(mData.getResult().get(position).getStok_adi()));
       holder.txtStockKod.setText(String.valueOf(mData.getResult().get(position).getStok_kodu()));
       holder.txtIstMik.setText(String.valueOf(mData.getResult().get(position).getIstenen_miktar()));
       holder.txtVerMik.setText(String.valueOf(mData.getResult().get(position).getStock()));
        holder.txtColor.setText(String.valueOf(mData.getResult().get(position).getRenk()));
        /* */

        if (selectedItems.contains(position)){
            if (String.valueOf(mData.getResult().get(position).getIstenen_miktar()).equals(String.valueOf(mData.getResult().get(position).getStock()))){
                holder.lnrLyt.setBackgroundColor(Color.parseColor("#21DF87"));
            }else{
                holder.lnrLyt.setBackgroundColor(Color.parseColor("#DFC621"));
            }
        }else{
            holder.lnrLyt.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        holder.lnrLyt.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onLongClick(View v) {
                // ok ise D0D700 yeşil
                // eksik ise EE3316 kırmızı
                // beyaz FFFFFF
                isLongClickActive = true;
                if (selectedItems.contains(position)){
                    for (int i = 0; i<selectedItems.size();i++){
                        if (selectedItems.get(i)==position){
                            selectedItems.remove(i);
                    //        holder.lnrLyt.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            notifyItemChanged(position);
                        }
                    }
                }else{
                    selectedItems.add(position);
                    //   holder.lnrLyt.setBackgroundColor(Color.parseColor("#EE3316"));
                    notifyItemChanged(position);
                }

                if (selectedItems.size() == mData.getResult().size()){
                    ((KarsilamaDetayActivity)context).showCompletedButton(true,mData);
                }else{
                    ((KarsilamaDetayActivity)context).showCompletedButton(false,mData);
                }
                return false;
            }
        });

        holder.lnrLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLongClickActive == false){
                    openInputDialog(holder.lnrLyt, position);
                }
                isLongClickActive = false;
            }
        });
    }

    private void controlBGcolor(LinearLayout lyt, int position,int amount){
        // ok ise D0D700 yeşil
        // eksik ise EE3316 kırmızı
        // beyaz FFFFFF
        if (amount == mData.getResult().get(position).getIstenen_miktar()){
            selectedItems.add(position);
            //    lyt.setBackgroundColor(Color.parseColor("#D0D700"));
            notifyItemChanged(position);
        } else{
            selectedItems.add(position);
            //   lyt.setBackgroundColor(Color.parseColor("#EE3316"));
            notifyItemChanged(position);
        }

        if (selectedItems.size() == mData.getResult().size()){
            ((KarsilamaDetayActivity)context).showCompletedButton(true,mData);
        }else{
            ((KarsilamaDetayActivity)context).showCompletedButton(false,mData);
        }
    }

    private void openInputDialog(LinearLayout lyt,int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = alertDialogBuilder.create();
        final AlertDialog alertDialog2 = new AlertDialog.Builder(context).create();

        final EditText txtAmount = new EditText(context);
        txtAmount.setImeOptions(EditorInfo.IME_ACTION_DONE);
        txtAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
        txtAmount.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    //  Toast.makeText(AddProductActivity.this, binding.edtSearch.getText(), Toast.LENGTH_SHORT).show();
                    String amount = txtAmount.getText().toString();
                    if (amount.equals("")){
                        if (Integer.parseInt(txtAmount.getHint().toString())>0)
                            amount = txtAmount.getHint().toString();
                        else
                            amount = "0";
                    }

                        if (validateInput(position, amount, txtAmount)){
                            mData.getResult().get(position).setStock(Integer.parseInt(amount));
                            controlBGcolor(lyt,position,Integer.parseInt(amount));
                            notifyItemChanged(position);
                            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(txtAmount.getWindowToken(), 0);
                        }
                    return true;
                }
                return false;
            }
        });

        txtAmount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // burası
                    String amount = txtAmount.getText().toString();
                    if (amount.equals("")){
                        if (Integer.parseInt(txtAmount.getHint().toString())>0)
                            amount = txtAmount.getHint().toString();
                        else
                            amount = "0";
                    }
                        if (validateInput(position, amount, txtAmount)){
                            mData.getResult().get(position).setStock(Integer.parseInt(amount));
                            controlBGcolor(lyt,position,Integer.parseInt(amount));
                            notifyItemChanged(position);
                            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(txtAmount.getWindowToken(), 0);
                            alertDialog2.dismiss();
                        }
                    return true;
                }
                return false;
            }
        });

        txtAmount.setHint(String.valueOf(mData.getResult().get(position).getStock()));
        alertDialog2
                .setTitle(mData.getResult().get(position).getStok_kodu()+" Yeni Miktar");
        alertDialog2.setMessage("");
        alertDialog2 .setView(txtAmount);
        alertDialog2.setButton(Dialog.BUTTON_POSITIVE,"Güncelle",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String amount = txtAmount.getText().toString();
                if (amount.equals("")){
                    if (Integer.parseInt(txtAmount.getHint().toString())>0)
                        amount = txtAmount.getHint().toString();
                    else
                        amount = "0";
                }
                    if (validateInput(position, amount, txtAmount)){
                        mData.getResult().get(position).setStock(Integer.parseInt(amount));
                        controlBGcolor(lyt,position,Integer.parseInt(amount));
                        notifyItemChanged(position);
                        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(txtAmount.getWindowToken(), 0);
                        alertDialog2.dismiss();
                    }

                alertDialog2.dismiss();
            }
        });
        alertDialog2.setButton(Dialog.BUTTON_NEGATIVE,"İptal",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog2.dismiss();
            }
        });
        alertDialog2.show();
    }

    private boolean validateInput(int position, String amountFromEditable, EditText edtAmount ){
        boolean isValid = true;
            if (Integer.parseInt(amountFromEditable) > mData.getResult().get(position).getIstenen_miktar()) {
                //  Tool.showInfo(context, "Uyarı", "En fazla " + maxLimits.get(position) + " miktar girilebilir");
                Toast.makeText(context,"En fazla " +mData.getResult().get(position).getIstenen_miktar() + " miktar girilebilir" ,Toast.LENGTH_SHORT).show();
                edtAmount.setText("");
                isValid = false;

        }
        return isValid;
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
        TextView txtStockKod,txtStockAdi,txtIstMik,txtVerMik,txtColor;
        LinearLayout lnrLyt;


        ViewHolder(View itemView) {
            super(itemView);
            txtStockKod = itemView.findViewById(R.id.txtStockKod);
            txtStockAdi = itemView.findViewById(R.id.txtStockAdi);
            txtIstMik = itemView.findViewById(R.id.txtIstMik);
            txtVerMik = itemView.findViewById(R.id.txtVerMik);
            txtColor = itemView.findViewById(R.id.txtColor);
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
        return mData.getResult().get(id).getHar_uuid();
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
