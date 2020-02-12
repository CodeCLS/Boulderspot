package app.playstore.boulderspot.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import app.playstore.boulderspot.R;

public class Navigation_bar extends View {
    private ImageView home;
    private ImageView events;
    private ImageView review;
    private ImageView location;
    private static final int DEFAULT_CIRCLE_COLOR = Color.RED;

    private int circleColor = DEFAULT_CIRCLE_COLOR;
    private Paint paint = new Paint();

    public Navigation_bar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();

        int pl = getPaddingLeft();
        int pr = getPaddingRight();
        int pt = getPaddingTop();
        int pb = getPaddingBottom();

        int usableWidth = w;
        int usableHeight = h ;

        int radius = Math.min(usableWidth, usableHeight);
        int cx = usableWidth;
        int cy = usableHeight;

        paint.setColor(circleColor);
        canvas.drawCircle(cx, cy, radius, paint);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }





    private void setOnclick() {
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reveal_line();

            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reveal_line();

            }
        });
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reveal_line();

            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reveal_line();

            }
        });

    }

    private void reveal_line() {

    }
}
