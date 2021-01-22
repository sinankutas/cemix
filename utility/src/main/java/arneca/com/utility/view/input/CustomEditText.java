package arneca.com.utility.view.input;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import arneca.com.utility.R;

public class CustomEditText extends RelativeLayout{

    private Context mContext;
    private EditTextWithFont mEditText;
    private float text_size = 18;
    private float elevation = 0;
    private float corner = 10;

    public CustomEditText(Context context) {
        super(context);
        mContext = context;
        init(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(Context context) {
        this.setBackground(context.getDrawable(R.drawable.view_back));
        this.setElevation(elevation);
        this.addView(addEditText(context));
        this.setPadding(10,0,0,0);
    }

    @SuppressLint("ClickableViewAccessibility")
    private EditText addEditText(Context context) {
        mEditText = new EditTextWithFont(context);
        mEditText.setBackground(null);
        mEditText.setGravity(Gravity.START|Gravity.CENTER);
        mEditText.setTextSize(text_size);
        mEditText.setSingleLine();
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT,
                1.0f
        );
        param.setMarginStart(25);
        mEditText.setLayoutParams(param);
        mEditText.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                changeBackgroundStatus(status.SUCCESS);
            } else {
                changeBackgroundStatus(status.NONE);
            }
        });
        return mEditText;
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

    public void setError(boolean isError){
        if(isError)
            changeBackgroundStatus(status.ERROR);
        else
            changeBackgroundStatus(status.NONE);
    }

    public void setEditTextInputType(inputType type){
        switch (type){

            case TEXT:
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;

            case PASSWORD:
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;

            case NUMBER:
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_CLASS_NUMBER);
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

    private void showKeyBoard(View view,Context context){
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
        TEXT,
        NUMBER
    }
}
