package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 9/8/22.
 */

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;

import com.arneca.evyap.BuildConfig;
import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.FooterInfoResponse;
import com.arneca.evyap.api.response.cmx.PDFResponse;
import com.arneca.evyap.databinding.PdfViewerBinding;
import com.arneca.evyap.helper.FileDownloader;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.BaseActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import javax.net.ssl.HttpsURLConnection;

import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PDFViewerActivity extends BaseActivity {

    private String pdfUrl = "";
    private String guid = "";
    private String viewTitle = "";
    private PdfViewerBinding binding;
    DownloadManager manager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.pdf_viewer);

        binding.toolbar2.rightContainer.setVisibility(View.INVISIBLE);
        Intent myIntent = getIntent(); // gets the previously created intent
        pdfUrl = myIntent.getStringExtra("pdfUrl");
        if (pdfUrl.equals("")){
            pdfUrl = "https://www.orimi.com/pdf-test.pdf";
        }
        viewTitle = myIntent.getStringExtra("viewTitle");
        guid = myIntent.getStringExtra("guid");
        binding.toolbar.txtViewTitle.setText(viewTitle);
        PreferencesHelper.setCurrentPDFActivity(this);
        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.toolbar2.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,pdfUrl);
                sendIntent.putExtra(Intent.EXTRA_TEXT, pdfUrl);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        binding.toolbar2.print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mahmutun servisi
                printPDF();
            }
        });

        binding.toolbar2.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tool.openDialog(PreferencesHelper.getCurrentPDFActivity());
                ActivityCompat.requestPermissions(PDFViewerActivity.this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());

                new DownloadFile().execute(pdfUrl, "cemix.pdf");
            /*    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                }
                Uri uri = Uri.parse("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                long reference = manager.enqueue(request);*/

            }
        });
        loadData();

    }

    private void printPDF() {
        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("guid", guid)
                .build();

        Request.getPrint(requestBody, this, response -> {
            PDFResponse pdfResponse = ( PDFResponse) response.body();
            response.headers();
            hideDialog();
            Tool.showInfo(this, "Bilgi", pdfResponse.getResult_message().getMessage());
        });
    }

    private void loadData() {

        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("guid", guid)
                .build();

        Request.getPDF(requestBody, this, response -> {
            PDFResponse pdfResponse = ( PDFResponse) response.body();
            response.headers();
            hideDialog();

            if (pdfResponse.getResult()!=null){
                Tool.openDialog(this);
                pdfUrl = pdfResponse.getResult().getUrl();
                new RetrivePDFfromUrl().execute(pdfUrl);
            }else{
                Tool.hideDialog();
                // Tool.showInfo(this, "Bilgi", openDocumentStockListResponse.getResult_message().getMessage());
            }
        });
    }


    // create an async task class for loading pdf file from URL.
    class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            Tool.hideDialog();
            binding.toolbar2.rightContainer.setVisibility(View.VISIBLE);
            binding.pdfView.fromStream(inputStream).load();
        }
    }

    private class DownloadFile extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());

            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf

            FileDownloader.downloadFile(fileUrl);
            return null;
        }
    }
}
