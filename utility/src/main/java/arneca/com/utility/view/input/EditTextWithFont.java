package arneca.com.utility.view.input;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import arneca.com.utility.R;
import arneca.com.utility.util.FontCache;

public class EditTextWithFont extends EditText {

    private static final String TAG = "EditTextWithFont";

    public EditTextWithFont(Context context) {
        super(context);
        setCustomFont(context);
    }

    public EditTextWithFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public EditTextWithFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.TextViewPlus);
        String customFont = a.getString(R.styleable.TextViewPlus_customFont);
        setCustomFont(ctx);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx) {
        Typeface typeface = null;
        try {
            typeface = FontCache.getTypeface(ctx, "fonts/SF-Pro-Text-Regular.ttf");
        } catch (Exception e) {
            Log.e(TAG, "Unable to load typeface: "+e.getMessage());
            return false;
        }

        setTypeface(typeface);
        return true;
    }
}
