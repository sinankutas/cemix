package arneca.com.utility.view.progres;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import arneca.com.utility.R;

public class CustomProgres extends ProgressBar {

    public CustomProgres(Context context) {
        super(context);
        setCustomFont(context,null);
    }

    public CustomProgres(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CustomProgres(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
    }

    public CustomProgres(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context context, AttributeSet attrs) {
        Drawable draw=context.getDrawable(R.drawable.custom_progres_bar);
        setProgressDrawable(draw);
        //setProgressTintList(ColorStateList.valueOf(Color.RED));
    }
}
