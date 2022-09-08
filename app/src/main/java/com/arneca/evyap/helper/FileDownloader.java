package com.arneca.evyap.helper;/*
 * Created by Sinan KUTAS on 9/8/22.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.activity.cmx.PDFViewerActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class FileDownloader {
    private static final int  MEGABYTE = 1024 * 1024;

    public static void downloadFile(String fileUrl){
        try {

            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            //urlConnection.setRequestMethod("GET");
            //urlConnection.setDoOutput(true);
            urlConnection.connect();


           String specName = ""+Calendar.getInstance().getTime().getTime();
            String filePath = Environment.getExternalStorageDirectory()+"/Download/"+ specName+".pdf";
            File fileScreenshot = new File(filePath);
            FileOutputStream fileOutputStream = null;

            InputStream inputStream = urlConnection.getInputStream();
             fileOutputStream = new FileOutputStream(fileScreenshot);
            int totalSize = urlConnection.getContentLength();

            byte[] buffer = new byte[MEGABYTE];
            int bufferLength = 0;
            while((bufferLength = inputStream.read(buffer))>0 ){
                fileOutputStream.write(buffer, 0, bufferLength);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            PreferencesHelper.getCurrentPDFActivity().hideDialog();
            PreferencesHelper.getCurrentPDFActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(PreferencesHelper.getCurrentPDFActivity(), "Download/"+ specName+".pdf indirildi", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
