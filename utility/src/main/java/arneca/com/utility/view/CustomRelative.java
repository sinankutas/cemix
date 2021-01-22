package arneca.com.utility.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import arneca.com.utility.R;
import arneca.com.utility.util.FontCache;
import arneca.com.utility.view.input.CustomEditText;

public class CustomRelative extends RelativeLayout {

    private Context mContext;
    private float elevation = 5;
    private float text_size = 13;
    private float title_size = 12;
    private EditText mEditText;
    private TextView mTextView;


    public CustomRelative(Context context) {
        super(context);
        mContext = context;
        init(context);
    }

    public CustomRelative(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context);
    }

    public CustomRelative(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(Context context) {
        this.setBackground(context.getDrawable(R.drawable.view_back));
        this.setElevation(elevation);
        this.addView(addTextView(context));
        this.addView(addEditText(context));
    }

    @SuppressLint("ClickableViewAccessibility")
    private EditText addEditText(Context context) {
        Typeface typeface = FontCache.getTypeface(context, "fonts/SF-Pro-Text-Regular.ttf");
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        mEditText = new EditText(context);
        mEditText.setBackground(null);
        mEditText.setSingleLine();
        mEditText.setMaxLines(10);
        mEditText.setGravity(Gravity.START|Gravity.CENTER);
        mEditText.setTextSize(text_size);
        mEditText.setTypeface(typeface);
        mEditText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        params.setMarginStart(8);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, mTextView.getId());
        mEditText.setLayoutParams(params);

        mEditText.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                changeBackgroundStatus(status.SUCCESS);
            } else {
                changeBackgroundStatus(status.NONE);
            }
        });
        return mEditText;
    }

    private TextView addTextView(Context context) {
        Typeface typeface = FontCache.getTypeface(context,"fonts/SFProText-Semibold.ttf");
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        mTextView = new TextView(context);
        mTextView.setGravity(Gravity.START|Gravity.CENTER);
        mTextView.setTypeface(typeface);
        mTextView.setTextSize(title_size);
        params.setMargins(16,16,8,0);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mTextView.setLayoutParams(params);

        return mTextView;
    }

    public String getText(){
        if(mEditText != null)
            return mEditText.getText().toString();
        else
            return  "";
    }

    public void setHint(String hint){
        if(mEditText != null && hint != null){
            mEditText.setHint(hint);
        }
    }

    public void setText(String text){
        if(mEditText != null && text != null){
            mEditText.setText(text);
        }
    }

    public void setTextTV(String text) {
        if (mTextView != null && text != null) {
            mTextView.setText(text);
        }
    }

    public void setError(boolean isError){
        if(isError)
            changeBackgroundStatus(status.ERROR);
        else
            changeBackgroundStatus(status.NONE);
    }

    public void setEditTextInputType(CustomEditText.inputType type){
        switch (type){

            case TEXT:
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;

            case PASSWORD:
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
        }
    }

    private void changeBackgroundStatus(status mStatus){
        switch (mStatus){
            case NONE:
                this.setBackground(mContext.getDrawable(R.drawable.view_back));
                break;
            case SUCCESS:
                this.setBackground(mContext.getDrawable(R.drawable.view_back_succes));
                break;
            case ERROR:
                this.setBackground(mContext.getDrawable(R.drawable.view_back_error));
                break;
        }
    }

    private void showKeyBoard(View view, Context context){
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            }
        }
    }

    private enum status{
        SUCCESS,
        ERROR,
        NONE
    }

    public enum inputType{
        PASSWORD,
        TEXT
    }
}
