package com.arneca.evyap.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.arneca.evyap.R;
import com.arneca.evyap.api.service.Client;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import de.hdodenhof.circleimageview.CircleImageView;

public class GlideBinding {
    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView view, String imageUrl) {
        Context context = view.getContext();
        if (context != null) {
            Tool.formUrl(imageUrl, Client.BASE_URL);
            Glide.with(context)
                    .load(imageUrl)
                    .into(view);
        }
    }

    @BindingAdapter("setToolbarColor")
    public static void setToolbarColor(View view, String color) {
        if (view != null) {
            view.setBackgroundColor(Color.parseColor(color));
        }
    }

    @BindingAdapter("imageResourceWithGlide")
    public static void setImageWithGlide(ImageView view, String imageUrl) {
        Context context = view.getContext();

        Glide.with(context)
                .load(imageUrl)
                .into(view);
    }

    public static void setImageWithGlide(ImageView view, Uri imageUrl) {
        Context context = view.getContext();

        Glide.with(context)
                .load(imageUrl)
                .into(view);
    }

    public static void setImageWithGlide(CircleImageView view, String imageUrl) {
        Context context = view.getContext();

        Glide.with(context)
                .load(imageUrl)
                .into(view);
    }

    public static void setImageWithGlideWithPB(ImageView view, String imageUrl, RequestListener<Drawable> listener) {
        Context context = view.getContext();

        Glide.with(context)
                .load(imageUrl)
                .listener(listener)
                .into(view);

    }

    public static void cancelPendingRequests(ImageView view) {
        Context context = view.getContext();
        Glide.with(context).clear(view);

    }

    public static void setImageWithGlide(ImageView view, Bitmap bitmap) {
        Context context = view.getContext();
        Glide.with(context).load(bitmap).into(view);
    }

    public static void setImageWithGlideProgress(ImageView view, String imageUrl) {
        Context context = view.getContext();
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(8f);
        circularProgressDrawable.setCenterRadius(40f);
        circularProgressDrawable.setColorSchemeColors(context.getResources().getColor(R.color.colorPrimary));
        circularProgressDrawable.start();

        Glide.with(context)
                .load(imageUrl)
                .placeholder(circularProgressDrawable)
                .into(view);
    }

    public static void setImageWithPicasso(ImageView view, String imageUrl) {
        Context context = view.getContext();
        Picasso.get().load(imageUrl).into(view);
    }

    public static void setImageWithPicassoUri(ImageView view, Uri uri) {
        File f = new File(String.valueOf(uri));
        Context context = view.getContext();

        Picasso.get().load(f).into(view);
    }

    public static void setImageFile(ImageView view, File file) {
        Context context = view.getContext();
        if (context != null)
            Glide.with(context).load(file).into(view);
    }

    public static void setImageUri(ImageView view, Uri uri) {
        Context context = view.getContext();
        if (context != null)
            Glide.with(view).load(uri).into(view);
    }



    public static Bitmap getBitmapFromURL(String src) {
        try {
            Tool.formUrl(src, Client.BASE_URL);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
}




















