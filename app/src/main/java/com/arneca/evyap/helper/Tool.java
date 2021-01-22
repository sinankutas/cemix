package com.arneca.evyap.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import com.afollestad.materialdialogs.MaterialDialog;
import com.arneca.evyap.ISuccessClickListener;


import com.arneca.evyap.R;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.kaopiz.kprogresshud.KProgressHUD;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import arneca.com.utility.dialog.blurDialog.BlurPopupWindow;
import arneca.com.utility.dialog.blurDialog.OnClick;
import arneca.com.utility.dialog.blurDialog.OnDialogClick;

public class Tool {
    private static KProgressHUD mProgress;
    private static AlertDialog loader_dialog;
    private static AlertDialog.Builder dialogbuilder;

    public static void showProgres(Context context) {
        if (mProgress == null) {
            mProgress = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(false)
                    .setLabel("loading")
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f);
        }
        mProgress.show();
    }

    public static void hideProgres() {
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
        }
    }

    public static String getLanguage() {
        String lang = Locale.getDefault().getLanguage().toUpperCase();
        switch (lang) {
            case "TR":
                return "TR";
            case "EN":
                return "EN";
            case "DE":
                return "DE";
            case "RU":
                return "RU";
            default:
                return "TR";
        }

    }

    public static String getDeviceID(Context activity) {
        String android_id = Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;
    }


    public static void shareText(String text, Context context) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/*");
        i.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(i, "Share Message"));
    }


    private static Uri getLocalBitmapUri(Bitmap bitmap, Context context) {
        Uri bmpUri = null;
        try {
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    public static File getLocalBitmapFile(Bitmap bitmap, Context context) {
        File bmpFile = null;
        try {
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpFile = file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpFile;
    }

    public enum ModuleType {
        postwall,
        profile,
        attendees,
        notifications,
        sessions,
        newsurvey,
        question
    }

    public static String formUrl(String unformed, String upperPart) {
        String formed = "";
        if (unformed != null && unformed.length() > 0) {
            if (unformed.startsWith("http")) {
                formed = unformed;
            } else {
                if (unformed.contains(".com/")) {
                    formed = "https://" + unformed;
                } else {
                    formed = upperPart + unformed;
                }
            }
        }
        return formed;
    }

    public static void openDialog(Activity activity) {
        if (dialogbuilder != null)
            loader_dialog.dismiss();

        dialogbuilder = new AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle);
        dialogbuilder.setView(activity.getLayoutInflater().inflate(R.layout.dialog_loading, null));
        loader_dialog = dialogbuilder.create();
        loader_dialog.setCancelable(false);
        loader_dialog.show();
        loader_dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

    }

    public static void hideDialog() {
        if (loader_dialog != null) {
            loader_dialog.dismiss();
        }
    }

    public static String MD5(final String str) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(str.getBytes());
            byte[] messageDigest = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    public static String getDeviceModel() {
        String manufacturer = Build.MANUFACTURER;
        return capitalize(manufacturer);
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }






    private static BlurPopupWindow builder;

    public static void showPopup(Context context,
                                 String title,
                                 final OnDialogClick dialogClick) {
        builder = new BlurPopupWindow.Builder(context)
                .setContentView(arneca.com.utility.R.layout.defult_dialog_popup)
                .bindClickListener(v -> {

                    if (builder.isShown() && builder != null)
                        builder.dismiss();

                    if (v.getId() == arneca.com.utility.R.id.yes) {
                        dialogClick.onClickAccept();

                    }
                    if (v.getId() == arneca.com.utility.R.id.no) {
                        dialogClick.onClickRefuse();
                    }

                }, arneca.com.utility.R.id.yes, arneca.com.utility.R.id.no)
                .setGravity(Gravity.CENTER)
                .setScaleRatio(0.2f)
                .setBlurRadius(5)
                .setTitle(title,arneca.com.utility.R.id.title)
                .setContent("no",arneca.com.utility.R.id.no)
                .setContent("yes",arneca.com.utility.R.id.yes)
                .setTintColor(0x30000000)
                .setAnimationDuration(300)
                .build();
        builder.show();
    }

    public static void showPopup(Context context,
                                 String title,
                                 String choice1,
                                 String choice2,
                                 final OnClick dialogClick) {
        builder = new BlurPopupWindow.Builder(context)
                .setContentView(arneca.com.utility.R.layout.defult_dialog_popup)
                .bindClickListener(v -> {

                    if (builder.isShown() && builder != null)
                        builder.dismiss();

                    if (v.getId() == arneca.com.utility.R.id.yes) {
                        dialogClick.onItemClicked(0);

                    }
                    if (v.getId() == arneca.com.utility.R.id.no) {
                        dialogClick.onItemClicked(1);
                    }

                }, R.id.yes, R.id.no)
                .setGravity(Gravity.CENTER)
                .setScaleRatio(0.2f)
                .setBlurRadius(5)
                .setTitle(title, arneca.com.utility.R.id.title)
                .setContent(choice1, R.id.no)
                .setContent(choice2, R.id.yes)
                .setTintColor(0x30000000)
                .setAnimationDuration(300)
                .build();
        builder.show();
    }

    public static void customProfileShowPopup(Context context,
                                              String choice1,
                                              String choice2,
                                              final OnClick dialogClick) {
        builder = new BlurPopupWindow.Builder(context)
                .setContentView(R.layout.profile_dialog_popup)
                .bindClickListener(v -> {

                    if (builder.isShown() && builder != null)
                        builder.dismiss();

                    if (v.getId() == R.id.yes) {
                        dialogClick.onItemClicked(0);

                    }
                    if (v.getId() == R.id.no) {
                        dialogClick.onItemClicked(1);
                    }

                }, R.id.yes, R.id.no)
                .setGravity(Gravity.CENTER)
                .setScaleRatio(0.2f)
                .setBlurRadius(5)
                .setContent(choice1, R.id.no)
                .setContent(choice2, R.id.yes)
                .setTintColor(0x30000000)
                .setAnimationDuration(300)
                .build();
        builder.show();
    }

    public static void showPopup(Context context,
                                 String title,
                                 String message,
                                 final OnClick dialogClick) {
        builder = new BlurPopupWindow.Builder(context)
                .setContentView(R.layout.defult_dialog_popup)
                .bindClickListener(v -> {

                    if (builder.isShown() && builder != null)
                        builder.dismiss();

                    if (v.getId() == R.id.yes) {
                        dialogClick.onItemClicked(0);

                    }

                }, R.id.yes, R.id.no)
                .setGravity(Gravity.CENTER)
                .setScaleRatio(0.2f)
                .setBlurRadius(5)
                .setTitle(title, R.id.title)
                .setContent(message, R.id.yes)
                .setTintColor(0x30000000)
                .setAnimationDuration(300)
                .build();
        builder.show();
    }



    public static void showPopup(Context context,
                                 String title,
                                 String content) {
        builder = new BlurPopupWindow.Builder(context)
                .setContentView(R.layout.info_popup)
                .bindClickListener(view -> builder.dismiss(), R.id.accept)
                .setGravity(Gravity.CENTER)
                .setScaleRatio(0.2f)
                .setBlurRadius(3)
                .setTitle(title, R.id.title)
                .setTitle("OK", R.id.accept)
                .setContent(content, R.id.content)
                .setTintColor(0x30000000)
                .build();
        builder.show();
    }

    public static void showPopupFinish(Activity activity,
                                       String title,
                                       String content) {
        builder = new BlurPopupWindow.Builder(activity)
                .setContentView(R.layout.info_popup)
                .bindClickListener(view -> {
                    builder.dismiss();
                    activity.finish();
                }, R.id.accept)
                .setGravity(Gravity.CENTER)
                .setScaleRatio(0.2f)
                .setBlurRadius(3)
                .setTitle(title, R.id.title)
                .setTitle("OK", R.id.accept)
                .setContent(content, R.id.content)
                .setTintColor(0x30000000)
                .build();
        builder.show();
    }

    public static void showPopupShareSuccess(Context context,
                                             String title,
                                             String content, ISuccessClickListener mListener) {
        builder = new BlurPopupWindow.Builder(context)
                .setContentView(R.layout.info_popup)
                .bindClickListener(view -> {
                    builder.dismiss();
                    mListener.onSuccessClick();
                }, arneca.com.utility.R.id.accept)
                .setGravity(Gravity.CENTER)
                .setScaleRatio(0.2f)
                .setBlurRadius(0)
                .setTitle(title, arneca.com.utility.R.id.title)
                .setTitle("accept", arneca.com.utility.R.id.accept)
                .setDismissOnClickBack(false)
                .setDismissOnTouchBackground(false)
                .setContent(content, arneca.com.utility.R.id.content)
                .setTintColor(0x30000000)
                .build();
        builder.show();
    }

    public static void openSocialMedia(String url, BaseActivity activity) {

        if (url.contains("instagram")) {
            Uri uri = Uri.parse(url);
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                activity.startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                activity.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(url)));
            }
        } else if (url.contains("facebook")) {

            try {
                activity.getPackageManager().getPackageInfo("com.facebook.katana", 0);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                activity.startActivity(intent);
            } catch (Exception e) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                activity.startActivity(intent);
            }

        } else if (url.contains("twitter")) {

            Intent intent = null;
            try {
                activity.getPackageManager().getPackageInfo("com.twitter.android", 0);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } catch (Exception e) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            }
            activity.startActivity(intent);

        } else if (url.contains("youtube")) {

            Intent intent = null;
            try {
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setPackage("com.google.android.youtube");
                intent.setData(Uri.parse(url));
                activity.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                activity.startActivity(intent);
            }
        }
    }

    public static String getApplicationVersion(Activity activity) {
        String version = "";
        try {
            PackageInfo pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

    public static int getCalculatedImageHeight(Activity activity, String width, String height) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int dev_width = displayMetrics.widthPixels;
        return dev_width * Integer.parseInt(height) / Integer.parseInt(width);
    }

    public static int getCalculatedImageHeight(Activity activity, int width, int height) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int dev_width = displayMetrics.widthPixels;
        return dev_width * height / width;
    }


    public static void showInfo(Context context, String title, String message) {
        new MaterialDialog.Builder(context)
                .title(title)
                .titleColor(context.getResources().getColor(R.color.colorPrimary))
                .content(message)
                .positiveText("ok")
                .positiveColor(context.getResources().getColor(R.color.colorPrimary))
                .cancelable(false)
                .show();
    }

    public static void showInfo(Context context, String title, String message,
                                MaterialDialog.SingleButtonCallback buttonCallback) {
        new MaterialDialog.Builder(context)
                .title(title)
                .contentColor(context.getResources().getColor(R.color.colorPrimaryDark))
                .titleColor(context.getResources().getColor(R.color.colorPrimary))
                .content(message)
                .positiveText("ok")
                .onPositive(buttonCallback)
                .positiveColor(context.getResources().getColor(R.color.colorPrimary))
                .cancelable(false)
                .show();
    }

    public static void showInfoOnlyMessage(Context context, String message, MaterialDialog.SingleButtonCallback buttonCallback) {
        new MaterialDialog.Builder(context)
                .titleColor(context.getResources().getColor(R.color.colorPrimary))
                .content(message)
                .positiveText("ok")
                .onPositive(buttonCallback)
                .positiveColor(context.getResources().getColor(R.color.colorPrimary))
                .cancelable(false)
                .show();
    }

    public static void showInfo(Context context, String title, String message, MaterialDialog.SingleButtonCallback buttonCallback, MaterialDialog.SingleButtonCallback buttonCallback1) {
        new MaterialDialog.Builder(context)
                .title(title)
                .titleColor(context.getResources().getColor(R.color.colorPrimary))
                .content(message)
                .positiveText("Close")
                .negativeText("Cancel")
                .onPositive(buttonCallback)
                .onNegative(buttonCallback1)
                .positiveColor(context.getResources().getColor(R.color.colorPrimary))
                .cancelable(false)
                .show();
    }

    public static void showInfoAcceptRefuse(Context context, String title, String message, MaterialDialog.SingleButtonCallback buttonCallback, MaterialDialog.SingleButtonCallback buttonCallback1) {
        new MaterialDialog.Builder(context)
                .title(title)
                .titleColor(context.getResources().getColor(R.color.colorPrimary))
                .content(message)
                .positiveText("ok")
                .negativeText("no")
                .onPositive(buttonCallback)
                .onNegative(buttonCallback1)
                .positiveColor(context.getResources().getColor(R.color.colorPrimary))
                .cancelable(false)
                .show();
    }

    public static void showInfoAcceptRefuse(Context context, String message, MaterialDialog.SingleButtonCallback buttonCallback, MaterialDialog.SingleButtonCallback buttonCallback1) {
        new MaterialDialog.Builder(context)
                .titleColor(context.getResources().getColor(R.color.colorPrimary))
                .content(message)
                .positiveText("ok")
                .negativeText("no")
                .onPositive(buttonCallback)
                .onNegative(buttonCallback1)
                .positiveColor(context.getResources().getColor(R.color.colorPrimary))
                .cancelable(false)
                .show();
    }

    public static void showPopupInfo(Context context,
                                     String title,
                                     String content) {
        builder = new BlurPopupWindow.Builder(context)
                .setContentView(R.layout.info_popup)
                .bindClickListener(view -> builder.dismiss(), R.id.accept)
                .setGravity(Gravity.CENTER)
                .setScaleRatio(0.2f)
                .setBlurRadius(3)
                .setTitle(title, R.id.title)
                .setTitle("ok", R.id.accept)
                .setContent(content, R.id.content)
                .setTintColor(0x30000000)
                .setDismissOnClickBack(false)
                .setDismissOnTouchBackground(false)
                .build();
        builder.show();
    }

    public static void showPopupInfo(Context context,
                                     String content) {
        builder = new BlurPopupWindow.Builder(context)
                .setContentView(R.layout.info_popup)
                .bindClickListener(view -> builder.dismiss(), R.id.accept)
                .setGravity(Gravity.CENTER)
                .setScaleRatio(0.2f)
                .setBlurRadius(3)
                .setTitle("ok", R.id.accept)
                .setContent(content, R.id.content)
                .setTintColor(0x30000000)
                .setDismissOnClickBack(false)
                .setDismissOnTouchBackground(false)
                .build();
        builder.show();
    }



   /* public static void makeSnackBar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
*/
    public static int dpToPx(Context context, int i) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i, r.getDisplayMetrics());
        return (int) px;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static long getMillisTime(String givenDateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        long timeInMilliseconds = 0;
        try {
            Date mDate = sdf.parse(givenDateString);
            timeInMilliseconds = mDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timeInMilliseconds;
    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String[] convertServerDate(String serverDate) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", new Locale("tr-TR"));
        input.setTimeZone(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat outputDate = new SimpleDateFormat("dd.MM.yyyy");
        outputDate.setTimeZone(Calendar.getInstance().getTimeZone());
        SimpleDateFormat outputTime = new SimpleDateFormat("HH:mm");
        SimpleDateFormat outputDayMonth = new SimpleDateFormat("dd MMM");
        SimpleDateFormat outputDayMonthLong = new SimpleDateFormat("dd MMMM");
        try {
            Date parseInput = input.parse(serverDate);

            String[] datetimeList = new String[4];
            datetimeList[0] = outputDate.format(parseInput);
            datetimeList[1] = outputTime.format(parseInput);
            datetimeList[2] = outputDayMonth.format(parseInput);
            datetimeList[3] = outputDayMonthLong.format(parseInput);
            return datetimeList;
        } catch (ParseException e) {
            return new String[4];
        }
    }

    public static String getNotificationDate(String date) {
        String[] dates = Tool.convertServerDate(date);
        if (dates.length >= 3) {
            return dates[2] + ' ' + dates[1];
        } else {
            return date;
        }
    }

    public static String getPostWallDate(String date) {
        String[] dates = Tool.convertServerDate(date);
        if (dates.length >= 4) {
            return dates[3] + ' ' + dates[1];
        } else {
            return date;
        }
    }



    public static File createImageFile(Context context, String currentPhotoPath) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public static File persistImage(Context context, Bitmap bitmap) {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File filesDir = context.getFilesDir();
        File imageFile = new File(filesDir, imageFileName + ".jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
            return imageFile;
        } catch (Exception e) {
         //   Timber.e("Error writing bitmap");
            return null;
        }
    }



  /*  public static void hideKeyboardImeiAction(EditTextWithFont editText) {
        editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
    }*/

    public static String capitalizeStr(String value) {
        return value.substring(0, 1).toUpperCase();
    }
}
