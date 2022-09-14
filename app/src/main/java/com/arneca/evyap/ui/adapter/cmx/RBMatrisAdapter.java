package com.arneca.evyap.ui.adapter.cmx;/*
 * Created by Sinan KUTAS on 8/16/22.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arneca.evyap.R;
import com.arneca.evyap.api.response.cmx.ProductSearchResponse;
import com.arneca.evyap.api.response.cmx.RBMatrisResponse;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.cmx.OpenDocRecordsActivity;
import com.arneca.evyap.ui.activity.cmx.RBMatrisActivity;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RBMatrisAdapter  extends RecyclerView.Adapter<RBMatrisAdapter.ViewHolder> {

    private RBMatrisResponse mData;
    private LayoutInflater mInflater;
    private OpenDocListAdapter.ItemClickListener mClickListener;
    private Context context;
    private int currentIndex;
    private ArrayList<Integer> maxLimits= new ArrayList();
    private HashMap<String,Integer> mapSubeMiktar = new HashMap();
    JSONArray jsonArray = PreferencesHelper.getJsonArrayForMatris();
    private boolean isStockActive;
    private AlertDialog dialog2;
    // data is passed into the constructor
    public RBMatrisAdapter(Context context, RBMatrisResponse data, int currentIndex, boolean isStockActive) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
        this.currentIndex = currentIndex;
        this.isStockActive = isStockActive;
        maxLimits = new ArrayList<>();
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public RBMatrisAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rbmatris_item, parent, false);

        mapSubeMiktar = new HashMap<>();
        if (PreferencesHelper.getActiveDocType().equals("satis")) {
            for (RBMatrisResponse.ResultBean.RenkDetayBean renkDetayBean : mData.getResult().get(currentIndex).getRenkDetay()){
                mapSubeMiktar.put("d1",renkDetayBean.getD1());
                mapSubeMiktar.put("d14",renkDetayBean.getD14());
                mapSubeMiktar.put("d89",renkDetayBean.getD89());
            }

            mapSubeMiktar.get(PreferencesHelper.getLoginResponse().getResult().getProfil().getSubeKodu().toLowerCase());
        }

        return new RBMatrisAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull RBMatrisAdapter.ViewHolder holder, int position) {
        holder.txtColor.setText(String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getRnk_kirilim()));
        holder.txtDP1.setText(String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getD1()));
        holder.txtDP2.setText(String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getD14()));
        holder.txtDP3.setText(String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getD89()));
        holder.edtAmount.setHint(String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getStock()));
        holder.txtAmount.setText(String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getStock()));
        String color = "";
        if (String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getRenk()).length()==0){
            color = "#FFFFFF";
        }else{
            color = "#"+String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getRenk()).replace(" ","");
        }

        int decodedColor = Color.parseColor(color);
        holder.img.setBackgroundColor(decodedColor);
        if (isStockActive){
            holder.edtAmount.setVisibility(View.INVISIBLE);
            holder.txtamountTitle.setVisibility(View.INVISIBLE);
        }


        if (PreferencesHelper.getActiveDocType().equals("satis")) {
            if (PreferencesHelper.getLoginResponse().getResult().getProfil().getSubeKodu().toLowerCase().equals("d1")) {
                maxLimits.add(mData.getResult().get(currentIndex).getRenkDetay().get(position).getD1());
            } else if (PreferencesHelper.getLoginResponse().getResult().getProfil().getSubeKodu().toLowerCase().equals("d14")) {
                maxLimits.add(mData.getResult().get(currentIndex).getRenkDetay().get(position).getD14());
            } else if (PreferencesHelper.getLoginResponse().getResult().getProfil().getSubeKodu().toLowerCase().equals("d89")) {
                maxLimits.add(mData.getResult().get(currentIndex).getRenkDetay().get(position).getD89());
            }
        }

    //    ((RBMatrisActivity)context).showSoftKeyboard(holder.itemView);
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
                    mData.getResult().get(currentIndex).getRenkDetay().get(position).setStock(Integer.parseInt(amountFromEditable));

                    boolean isfounded = false;
                    int existingIndex = 0;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject row = jsonArray.getJSONObject(i);
                        if( String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getId()).equals(row.get("StokIdx"))){
                            isfounded = true;
                            existingIndex = i;
                            break;
                        }
                    }

                    if (isfounded){
                        jsonArray.remove(existingIndex);
                    }
                    obj.put("StokKodu", String.valueOf(mData.getResult().get(currentIndex).getKod()));
                    obj.put("StokIdx", String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getId()));
                    obj.put("RenkId", String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getRnk_kirilim_id()));
                    obj.put("Miktar", ""+amountFromEditable);
                    obj.put("Fiyat", ""+String.valueOf(mData.getResult().get(currentIndex).getFiyat()));
                    obj.put("Dvz", "1");

                    if (PreferencesHelper.getActiveDocType().equals("satis")) {
                        if (Integer.parseInt(amountFromEditable) > maxLimits.get(position)) {
                            Tool.showInfo(context, "Uyarı", "En fazla " + maxLimits.get(position) + " miktar girilebilir");
                            holder.edtAmount.setText("");
                            return;
                        }
                    }

                   if (!amountFromEditable.equals("0"))
                       jsonArray.put(obj);

                    ((RBMatrisActivity)context).setJsonArray(jsonArray);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

       // if (position!=0){
            holder.txtColorTitle.setVisibility(View.INVISIBLE);
            holder.txtamountTitle.setVisibility(View.INVISIBLE);
            holder.txtDP3Title.setVisibility(View.INVISIBLE);
            holder.txtDP2Title.setVisibility(View.INVISIBLE);
            holder.txtDP1Title.setVisibility(View.INVISIBLE);
            holder.txtKNumberIdTitle.setVisibility(View.INVISIBLE);
        // }
        holder.lnrLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInputDialog(position);
            }
        });
    }

    private void openInputDialog(int position) {
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
                    if (PreferencesHelper.getActiveDocType().equals("satis")) {

                        if (validateInput(position, amount, txtAmount)){
                            mData.getResult().get(currentIndex).getRenkDetay().get(position).setStock(Integer.parseInt(amount));
                            notifyItemChanged(position);
                            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(txtAmount.getWindowToken(), 0);
                        }
                    }

                    if (PreferencesHelper.getActiveDocType().equals("siparis")) {
                        if (validateInputForSiparis(position, amount, txtAmount)){
                            mData.getResult().get(currentIndex).getRenkDetay().get(position).setStock(Integer.parseInt(amount));
                            notifyItemChanged(position);
                            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(txtAmount.getWindowToken(), 0);
                        }
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
                    if (PreferencesHelper.getActiveDocType().equals("satis")) {
                        if (validateInput(position, amount, txtAmount)){
                            mData.getResult().get(currentIndex).getRenkDetay().get(position).setStock(Integer.parseInt(amount));
                            notifyItemChanged(position);
                            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(txtAmount.getWindowToken(), 0);
                            alertDialog2.dismiss();
                        }
                    }

                    if (PreferencesHelper.getActiveDocType().equals("siparis")) {
                        if (validateInputForSiparis(position, amount, txtAmount)){
                            mData.getResult().get(currentIndex).getRenkDetay().get(position).setStock(Integer.parseInt(amount));
                            notifyItemChanged(position);
                            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(txtAmount.getWindowToken(), 0);
                            alertDialog2.dismiss();
                        }
                    }
                    return true;
                }
                return false;
            }
        });

        txtAmount.setHint(String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getStock()));
        alertDialog2
                .setTitle(mData.getResult().get(currentIndex).getRenkDetay().get(position).getRnk_kirilim()+" Yeni Miktar");
        alertDialog2.setMessage("");
        alertDialog2 .setView(txtAmount);
        alertDialog2.setButton(Dialog.BUTTON_POSITIVE,"Güncelle",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String amount = txtAmount.getText().toString();
                if (PreferencesHelper.getActiveDocType().equals("satis")) {
                    if (validateInput(position, amount, txtAmount)){
                        mData.getResult().get(currentIndex).getRenkDetay().get(position).setStock(Integer.parseInt(amount));
                        notifyItemChanged(position);
                        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(txtAmount.getWindowToken(), 0);
                        alertDialog2.dismiss();
                    }
                }

                if (PreferencesHelper.getActiveDocType().equals("siparis")) {
                    if (validateInputForSiparis(position, amount, txtAmount)){
                        mData.getResult().get(currentIndex).getRenkDetay().get(position).setStock(Integer.parseInt(amount));
                        notifyItemChanged(position);
                        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(txtAmount.getWindowToken(), 0);
                        alertDialog2.dismiss();
                    }
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


    private boolean validateInputForSiparis(int position, String amountFromEditable, EditText edtAmount ){
        boolean isValid = true;


        if (PreferencesHelper.getActiveDocType().equals("siparis")) {
            if (Integer.parseInt(amountFromEditable) > mData.getResult().get(currentIndex).getRenkDetay().get(position).getToplam()) {
                //  Tool.showInfo(context, "Uyarı", "En fazla " + maxLimits.get(position) + " miktar girilebilir");
                Toast.makeText(context,"En fazla " +mData.getResult().get(currentIndex).getRenkDetay().get(position).getToplam() + " miktar girilebilir" ,Toast.LENGTH_SHORT).show();
                edtAmount.setText("");
                isValid = false;
            } else{
                JSONObject obj = new JSONObject();
                try {
                    boolean isfounded = false;
                    int existingIndex = 0;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject row = null;

                        row = jsonArray.getJSONObject(i);

                        if( String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getId()).equals(row.get("StokIdx"))){
                            isfounded = true;
                            existingIndex = i;
                            break;
                        }
                    }

                    if (isfounded){
                        jsonArray.remove(existingIndex);
                    }
                    obj.put("StokKodu", String.valueOf(mData.getResult().get(currentIndex).getKod()));
                    obj.put("StokIdx", String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getId()));
                    obj.put("RenkId", String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getRnk_kirilim_id()));
                    obj.put("Miktar", ""+amountFromEditable);
                    obj.put("Fiyat", ""+String.valueOf(mData.getResult().get(currentIndex).getFiyat()));
                    obj.put("Dvz", "1");

                    if (!amountFromEditable.equals("0"))
                        jsonArray.put(obj);

                    ((RBMatrisActivity)context).setJsonArray(jsonArray);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return isValid;
    }

    // total number of cells
    private boolean validateInput(int position, String amountFromEditable, EditText edtAmount ){
        boolean isValid = true;
        if (PreferencesHelper.getActiveDocType().equals("satis")) {
            if (PreferencesHelper.getLoginResponse().getResult().getProfil().getSubeKodu().toLowerCase().equals("d1")) {
                maxLimits.add(mData.getResult().get(currentIndex).getRenkDetay().get(position).getD1());
            } else if (PreferencesHelper.getLoginResponse().getResult().getProfil().getSubeKodu().toLowerCase().equals("d14")) {
                maxLimits.add(mData.getResult().get(currentIndex).getRenkDetay().get(position).getD14());
            } else if (PreferencesHelper.getLoginResponse().getResult().getProfil().getSubeKodu().toLowerCase().equals("d89")) {
                maxLimits.add(mData.getResult().get(currentIndex).getRenkDetay().get(position).getD89());
            }
        }

        if (PreferencesHelper.getActiveDocType().equals("satis")) {
            if (Integer.parseInt(amountFromEditable) > maxLimits.get(position)) {
              //  Tool.showInfo(context, "Uyarı", "En fazla " + maxLimits.get(position) + " miktar girilebilir");
                Toast.makeText(context,"En fazla " + maxLimits.get(position) + " miktar girilebilir" ,Toast.LENGTH_SHORT).show();
                edtAmount.setText("");
                isValid = false;
            }
            else{
                JSONObject obj = new JSONObject();
                try {
                    boolean isfounded = false;
                    int existingIndex = 0;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject row = null;

                            row = jsonArray.getJSONObject(i);

                        if( String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getId()).equals(row.get("StokIdx"))){
                            isfounded = true;
                            existingIndex = i;
                            break;
                        }
                    }

                    if (isfounded){
                        jsonArray.remove(existingIndex);
                    }
                    obj.put("StokKodu", String.valueOf(mData.getResult().get(currentIndex).getKod()));
                    obj.put("StokIdx", String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getId()));
                    obj.put("RenkId", String.valueOf(mData.getResult().get(currentIndex).getRenkDetay().get(position).getRnk_kirilim_id()));
                    obj.put("Miktar", ""+amountFromEditable);
                    obj.put("Fiyat", ""+String.valueOf(mData.getResult().get(currentIndex).getFiyat()));
                    obj.put("Dvz", "1");

                    if (!amountFromEditable.equals("0"))
                        jsonArray.put(obj);

                    ((RBMatrisActivity)context).setJsonArray(jsonArray);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return isValid;
    }
    @Override
    public int getItemCount() {
        if (mData.getResult() == null)
            return 0;
        else
            return mData.getResult().get(currentIndex).getRenkDetay().size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtColor,txtAmount,txtDP1,txtDP2,txtDP3,txtColorTitle,txtamountTitle,txtDP3Title,txtDP2Title,txtDP1Title,txtKNumberIdTitle;
        TextView img ;
        EditText edtAmount;
        LinearLayout lnrLyt;


        ViewHolder(View itemView) {
            super(itemView);
            txtColor = itemView.findViewById(R.id.txtColor);
            txtDP1 = itemView.findViewById(R.id.txtDP1);
            txtDP2 = itemView.findViewById(R.id.txtDP2);
            txtAmount = itemView.findViewById(R.id.txtAmount);
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