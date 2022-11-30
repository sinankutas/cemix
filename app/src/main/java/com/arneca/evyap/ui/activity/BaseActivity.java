package com.arneca.evyap.ui.activity;/*
 * Created by Sinan KUTAS on 22.01.2021.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.arneca.evyap.R;
import com.arneca.evyap.api.service.Client;
import com.arneca.evyap.helper.Tool;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import arneca.com.utility.dialog.blurDialog.BlurPopupWindow;
import arneca.com.utility.dialog.blurDialog.OnClick;
import okhttp3.RequestBody;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class BaseActivity extends AppCompatActivity {

    public static final int LOCATION_REQUEST_CODE = 1;
    // private ViewToolbarBinding viewToolbarBinding;
    private BlurPopupWindow builder;
    private KProgressHUD mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        // TODO delete cache
        deleteCache(this);
    }

    public void openAnotherActivity(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        startActivity(intent);
    }

    public void openAnotherFragment(Fragment baseFragment, int container) {
        getSupportFragmentManager().beginTransaction()
                .add(container, baseFragment)
                .addToBackStack(baseFragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }
    /*  public void openAnotherFragment(BaseFragment baseFragment, int container) {
          getSupportFragmentManager().beginTransaction()
                  .add(container, baseFragment)
                  .addToBackStack(baseFragment.getClass().getSimpleName())
                  .commit();
      }

      public void showFragment(BaseFragment baseFragment, int container, List<BaseFragment> fragmentList) {
          for (int i = 0; i < fragmentList.size(); i++) {
              if (!baseFragment.getClass().getSimpleName().equals(fragmentList.get(i).getClass().getSimpleName())) {
                  getSupportFragmentManager().beginTransaction()
                          .hide(fragmentList.get(i))
                          .commit();
              }
          }
          getSupportFragmentManager().beginTransaction()
                  .show(baseFragment)
                  .addToBackStack(baseFragment.getClass().getSimpleName())
                  .commit();
      }
  */
    public static void showInfo(String title, String message, Context context,
                                MaterialDialog.SingleButtonCallback positiveButtonCallback,
                                MaterialDialog.SingleButtonCallback negativeButtonCallback, boolean cancel) {
        new MaterialDialog.Builder(context)
                .content(message)
                .title(title)
                .positiveText("ok")
                .negativeText("no")
                .onPositive(positiveButtonCallback)
                .onNegative(negativeButtonCallback)
                .cancelable(cancel)
                .show();
    }

    public static void showInfo(int title, int message, boolean cancelable, Context context) {
        new MaterialDialog.Builder(context)
                .content(message)
                .title(title)
                .positiveText("ok")
                .cancelable(cancelable)
                .show();
    }

    /* public static void showInfo(String title, String message, Context context,
                                 MaterialDialog.SingleButtonCallback positiveButtonCallback,
                                 MaterialDialog.SingleButtonCallback negativeButtonCallback, boolean cancel) {
         new MaterialDialog.Builder(context)
                 .content(message)
                 .title(title)
                 .positiveText("ok")
                 .negativeText("no")
                 .onPositive(positiveButtonCallback)
                 .onNegative(negativeButtonCallback)
                 .cancelable(cancel)
                 .show();
     }*/
    public static void showInfo(String title, String message, Context context) {
        new MaterialDialog.Builder(context)
                .content(message)
                .title(title)
                .positiveText("ok")
                .cancelable(false)
                .show();
    }

    public static void showInfo(String message, Context context) {
        new MaterialDialog.Builder(context)
                .content(message)
                .positiveText("ok")
                .cancelable(false)
                .show();
    }

    public static void showInfo(int titleRes, int messageRes, Context context) {
        new MaterialDialog.Builder(context)
                .content(messageRes)
                .title(titleRes)
                .positiveText("ok")
                .cancelable(false)
                .show();
    }

    public static void showInfo(int titleRes, int messageRes, int possitiveButton, Context context) {
        new MaterialDialog.Builder(context)
                .content(messageRes)
                .title(titleRes)
                .positiveText(possitiveButton)
                .cancelable(false)
                .show();
    }

    public static void showDropDownDialog(String title, int resId, boolean cancelable, Context context, MaterialDialog.ListCallbackSingleChoice listener) {
        new MaterialDialog.Builder(context)
                .title(title)
                .positiveText("ok")
                .cancelable(cancelable)
                .items(resId)
                .itemsCallbackSingleChoice(0, listener)
                .show();
    }

    public static void showDropDownDialog(int titleResId, int resId, int selectedIndex, boolean cancelable, Context context, MaterialDialog.SingleButtonCallback listener) {
        new MaterialDialog.Builder(context)
                .title(titleResId)
                .positiveText("ok")
                .cancelable(cancelable)
                .items(resId)
                .itemsCallbackSingleChoice(selectedIndex, (dialog, itemView, which, text) -> true)
                .onPositive(listener)
                .show();
    }

    protected void showPopup(Context context, String title, String choice1, String choice2, final OnClick dialogClick) {
        Tool.showPopup(context, title, choice1, choice2, dialogClick);
    }

    protected void showProfilePopup(Context context, String choice1, String choice2, final OnClick dialogClick) {
        Tool.customProfileShowPopup(context, choice1, choice2, dialogClick);
    }

    public void showPopup(AppCompatActivity activity,
                          String title,
                          String content) {
        builder = new BlurPopupWindow.Builder(activity)
                .setContentView(R.layout.info_popup)
                .bindClickListener(view -> {
                    builder.dismiss();
                    activity.finish();
                }, R.id.accept)
                .setDismissOnTouchBackground(false)
                .setGravity(Gravity.CENTER)
                .setScaleRatio(0.2f)
                .setBlurRadius(3)
                .setTitle(title, R.id.title)
                .setTitle("ok", R.id.accept)
                .setContent(content, R.id.content)
                .setTintColor(0x30000000)
                .build();
        builder.show();
    }

    public void showIntentErrorPopup(AppCompatActivity activity,
                                     String title,
                                     String content) {
        builder = new BlurPopupWindow.Builder(activity)
                .setContentView(R.layout.info_popup)
                .bindClickListener(view -> builder.dismiss(), R.id.accept)
                .setDismissOnTouchBackground(false)
                .setGravity(Gravity.CENTER)
                .setScaleRatio(0.2f)
                .setBlurRadius(3)
                .setTitle(title, R.id.title)
                .setTitle("ok", R.id.accept)
                .setContent(content, R.id.content)
                .setTintColor(0x30000000)
                .build();
        builder.show();
    }

    public AlertDialog.Builder showAlertDialog(String title, String message) {
        return new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom))
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false);
    }

    public AlertDialog.Builder showAlertDialogOnlyMessage(String message) {
        return new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom))
                .setMessage(message)
                .setCancelable(false);
    }

    public void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    protected void removeStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                , WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public void showProgres(Context context) {
        Tool.showProgres(context);
    }

    public void hideProgres() {
        Tool.hideProgres();
    }

    public void openDialog() {
        Tool.openDialog(this);
    }

    public void hideDialog() {
        Tool.hideDialog();
    }

    protected HashMap<String, Object> map() {
        return Client.standardHashMap();
    }

    protected HashMap<String, RequestBody> multiPartMap() {
        return Client.standardMultiPartHashMap();
    }

    protected HashMap<String, Object> headersMap(boolean isAppKeyActive) {

        return Client.headersHashMap(this, isAppKeyActive);
    }

    protected void hideKeyBoard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            assert inputManager != null;
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.SHOW_FORCED);
        }
    }

    protected void makeFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

   /* public void showFragment(BaseFragment baseFragment, int container, List<BaseFragment> fragmentList) {
        for (int i = 0; i < fragmentList.size(); i++) {
            if (!baseFragment.getClass().getSimpleName().equals(fragmentList.get(i).getClass().getSimpleName())) {
                getSupportFragmentManager().beginTransaction()
                        .hide(fragmentList.get(i))
                        .commit();
            }
        }
        getSupportFragmentManager().beginTransaction()
                .show(baseFragment)
                .addToBackStack(baseFragment.getClass().getSimpleName())
                .commit();
    }


    public void openAnotherFragment(BaseFragment baseFragment, int container) {
        getSupportFragmentManager().beginTransaction()
                .add(container, baseFragment)
                .addToBackStack(baseFragment.getClass().getSimpleName())
                .commit();
    }
*/
    public int dpToPx(int dp) {
        return Tool.dpToPx(dp);
    }

    public String getToken() {
        return "";//PreferencesHelper.getUserToken();
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {
        }
    }

    private void openModuleWithTypeName(Class<? extends Activity> activity, String type) {
        Intent intent = new Intent(this, activity);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    public void requestLocationPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
    }

    public void animationTab(View view) {
        ScaleAnimation scale = new ScaleAnimation(.9f, 1f, .9f, 1f, ScaleAnimation.RELATIVE_TO_SELF, .4f, ScaleAnimation.RELATIVE_TO_SELF, .4f);
        scale.setDuration(500);
        scale.setInterpolator(new OvershootInterpolator());
        view.startAnimation(scale);
    }




 /*   public static void hideKeyboardNewVersion(EditTextWithFont editTextWithFont) {
        Tool.hideKeyboardImeiAction(editTextWithFont);
    }*/
}