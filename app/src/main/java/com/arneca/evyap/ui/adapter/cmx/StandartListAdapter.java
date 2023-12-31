package com.arneca.evyap.ui.adapter.cmx;/*
 * Created by Sinan KUTAS on 8/15/22.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.ProductSearchResponse;
import com.arneca.evyap.api.response.cmx.TanimlarResponse;
import com.arneca.evyap.helper.Const;
import com.arneca.evyap.helper.DBHelper;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.cmx.LokalSayimlarActivity;
import com.arneca.evyap.ui.activity.cmx.NewSayimActivity;
import com.arneca.evyap.ui.activity.cmx.OpenDocListActivity;
import com.arneca.evyap.ui.activity.cmx.OpenDocRecordsActivity;
import com.arneca.evyap.ui.activity.cmx.OpenDocStockListActivity;
import com.arneca.evyap.ui.activity.cmx.RBMatrisActivity;
import com.arneca.evyap.ui.activity.cmx.SayimUrunEkleActivity;
import com.arneca.evyap.ui.activity.cmx.TanimlarActivity;
import com.arneca.evyap.ui.fragment.CompanyBottomFragment;
import com.arneca.evyap.ui.fragment.TanimBottomSheetFragment;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class StandartListAdapter extends RecyclerView.Adapter<StandartListAdapter.ViewHolder>{
    private ArrayList<String> listdata;
    private Context context;
    private String activeDocType;
    private DBHelper dbHelper ;
    // RecyclerView recyclerView;
    private TanimBottomSheetFragment tanimBottomSheetFragment;
    public StandartListAdapter(Context context, ArrayList<String> listdata) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.cmx_standart_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        dbHelper =  new DBHelper(context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String myListData = listdata.get(position);
        final boolean[] isSayimActive = {false};
        holder.textView.setText(listdata.get(position));
        holder.lytLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String viewTitle1 = "";
                String viewTitle2 = "";
                String viewTitle3 = "";
                if (PreferencesHelper.getActiveDocType().equals("satis")){
                    viewTitle1 = "Satışa Devam";
                    viewTitle2 = "Yeni Satış";
                }else if(PreferencesHelper.getActiveDocType().equals("siparis")){
                    viewTitle1 = "Siparişe Devam";
                    viewTitle2 = "Yeni Sipariş";
                }else if(PreferencesHelper.getActiveDocType().equals("teklif")){
                    viewTitle1 = "Teklife Devam";
                    viewTitle2 = "Yeni Teklife";
                }
                else if(PreferencesHelper.getActiveDocType().equals("sayim")){
                    isSayimActive[0] = true;
                    viewTitle1 = "Sayımları Çek";
                    viewTitle2 = "Yeni Sayım";
                    viewTitle3 = "Eski Sayım";
                }else if(PreferencesHelper.getActiveDocType().equals("fuar")){
                    viewTitle1 = "Fuar Siparişine Devam";
                    viewTitle2 = "Yeni Fuar Siparişi";
                }

                if (position==0){
                    if (isSayimActive[0]== true){
                      /*  Tool.hideDialog();
                        Tool.showInfo(context,"Uyarı",
                                "Bu işlem 1 kaç dakika sürebilir. Bu süre bağlantı hzınıza ve cihaz kapasitenize göre değişebilir. Lütfen bağlantınızı kapatmayın ve işlemi yarıda kesmeyin.",
                                (dialog, which) -> loadTanim(),"Tamam");*/
                        loadTanim();
                    }else{
                        Intent intent = new Intent(context, OpenDocListActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        intent.putExtra("viewTitle",viewTitle1);
                        context.startActivity(intent);
                    }
                }else if (position==1){
                    if (isSayimActive[0]== true){
                        // get New Sayim
                        if (dbHelper.numberOfRows()>0) {
                            loadDescription(viewTitle2);
                        }  else{
                      /*      Tool.hideDialog();
                            Tool.showInfo(context,"Tanım Bulunamadı. Tanımlar Yüklensin mi?",
                                    "Bu işlem 1 kaç dakika sürebilir. Bu süre bağlantı hzınıza ve cihaz kapasitenize göre değişebilir. Lütfen bağlantınızı kapatmayın ve işlemi yarıda kesmeyin.",
                                    (dialog, which) -> loadTanim(),"Tamam");*/
                            loadTanim();
                        }
                    }else{
                        Intent intent = new Intent(context, OpenDocRecordsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        intent.putExtra("viewTitle",viewTitle2);
                        context.startActivity(intent);
                    }
                }else if (position == 2){
                    Intent intent = new Intent(context, LokalSayimlarActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("viewTitle",viewTitle3);
                    context.startActivity(intent);
                }
            }
        });
    }

    private void loadDescription(String viewTitle) {
        final EditText txtDescription = new EditText(context);
        txtDescription.setHint("Açıklama");
        new AlertDialog.Builder(context)
                .setTitle("Sayım Açıklaması")
                .setMessage("")
                .setView(txtDescription)
                .setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog1, int whichButton) {
                        String desc = txtDescription.getText().toString();
                        if (desc.length()>0){
                            dbHelper.insertNewSayim(desc,PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx(),PreferencesHelper.getLoginResponse().getResult().getProfil().getSubeKodu(),"","");
                                Intent intent = new Intent(context, SayimUrunEkleActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                intent.putExtra("viewTitle", viewTitle);
                                intent.putExtra("currentId", ""); // yeni liste olduğu için boş yolla
                                context.startActivity(intent);

                        }else{
                            Tool.showInfo(context, "Uyarı", "Açıklama girmelisiniz");
                        }
                    }
                })
                .setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }

    private void loadTanim() {

        // silme işlemi için yapıldı silsede silmesede loadtanim
        if (dbHelper.deleteAll()==1){
            continueLoadTanim();
        }else{
            continueLoadTanim();
        }
    }

    private void continueLoadTanim(){
        Tool.openDialog((TanimlarActivity)context);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .build();

        Request.getTanim(requestBody, context, response -> {
            TanimlarResponse tanimlarResponse = ( TanimlarResponse) response.body();
            response.headers();
        //    Tool.hideDialog();
            //  ( (TanimlarActivity)context).hideDialog();
            if (tanimlarResponse.getResult()!=null){
           /*    Tool.showInfo(context,"Bilgi",
                        tanimlarResponse.getResult_message().getMessage(),
                        (dialog, which) ->  Tool.hideDialog(),"Tamam");*/
                writeToDb(tanimlarResponse);
            }else{
                Tool.hideDialog();
                Tool.showInfo(context, "Bilgi", tanimlarResponse.getResult_message().getMessage());
            }
        });
    }

    private void writeToDb(TanimlarResponse tanimlarResponse) {
     //   Toast.makeText(context,"Veriler Kaydediliyor",Toast.LENGTH_SHORT).show();
        PreferencesHelper.setTanimlarResponse(tanimlarResponse);
        new DBWrite().execute("");
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public LinearLayout lytLinear;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.txtMenuTitle);
            this.lytLinear = (LinearLayout) itemView.findViewById(R.id.lytLinear);
        }
    }

    private class DBWrite extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            int i = 0;
            for (TanimlarResponse.ResultBean trBean : PreferencesHelper.getTanimlarResponse().getResult()){
                try {
                    i = i+1;
//                    Toast.makeText(context,""+i,Toast.LENGTH_SHORT).show();
                    dbHelper.insertRecord(""+trBean.getD1(),trBean.getKod(),trBean.getAd(),trBean.getAnagrup_kod(),trBean.getBeden(),trBean.getBeden_kodu(),trBean.getRenk(),""+trBean.getRenk_id()
                            ,trBean.getPkadet(),trBean.getDvz(),trBean.getSatis_fiyat(),trBean.getD1(),trBean.getD14(),trBean.getD89(),trBean.getSrc(),trBean.getRenk_kodu_x());
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


            Log.d("***** asasda","***** dasda");
            ((TanimlarActivity)context).hideDialog();

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

}

