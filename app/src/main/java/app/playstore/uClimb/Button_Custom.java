package app.playstore.uClimb;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;

public class Button_Custom extends View {
    public Button_Custom(Context context) {
        super(context);
    }
    private void init() {
        float textHeight = (float) 1.231;
        int textColor = getResources().getColor(R.color.blue_pressed_btn);
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        if (textHeight == 0) {
            textHeight = textPaint.getTextSize();
        } else {
            textPaint.setTextSize(textHeight);
        }


        Paint piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        piePaint.setStyle(Paint.Style.FILL);
        piePaint.setTextSize(textHeight);

        Paint shadowPaint = new Paint(0);
        shadowPaint.setColor(0xff101010);
        shadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
    }

    //    @Override
   // protected void onDraw(Canvas canvas) {
   //     canvas.drawRoundRect(100.0,100.0,100.0,100.0,100.0,100.0,"#fff");
   //     super.onDraw(canvas);
   // }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
