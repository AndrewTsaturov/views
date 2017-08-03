package com.example.drawingapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by Андрей on 30.07.2017.
 */

public class MyView extends View {


    Paint paintAccent, paintBoldColor;

    int width, height, innerPadding;





    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        a.recycle();
        innerPadding = a.getDimensionPixelSize(R.styleable.MyView_innerPadding, 0);

        paintAccent = new Paint();
        paintAccent.setColor(context.getResources().getColor(R.color.accent));
        paintAccent.setStrokeWidth(10f);
        paintAccent.setAntiAlias(true);
        paintAccent.setSubpixelText(true);
        paintAccent.setTextSize(128f);
        paintAccent.setStyle(Paint.Style.FILL);

        paintBoldColor = new Paint();
        paintBoldColor.setColor(context.getResources().getColor(R.color.primary_text));
        paintBoldColor.setStrokeWidth(10f);
        paintBoldColor.setAntiAlias(true);
        paintBoldColor.setSubpixelText(true);
        paintBoldColor.setTextSize(128f);
        paintBoldColor.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRect(200f, 200f, 600f, 600f, paintAccent);
//        canvas.drawText("HELLO", 400f, 650f, paintAccent);
          canvas.drawPath(secondPart(width, height, innerPadding), paintAccent);
          canvas.drawPath(firstPart(width, height, innerPadding), paintBoldColor);
    }

    private Path firstPart(float width, float height, float innerPadding){
        Path path = new Path();
        path.reset();
        path.moveTo(innerPadding, (height / 3) * 2);
        path.lineTo(innerPadding + width / 6, height - innerPadding);
        path.lineTo(width - (width / 6) - innerPadding, height - innerPadding);
        path.lineTo(width - innerPadding, (height / 3) * 2);
        path.close();
        return path;
    }

    private Path secondPart(float width, float height, float innerPadding){
        Path path = new Path();
        path.reset();
        path.moveTo(width / 3 + innerPadding, (height / 3) * 2);
        path.lineTo((width / 3) * 2 - innerPadding, (height / 3) * 2);
        path.lineTo((width / 3) * 2, innerPadding);
        path.close();
        return path;
    }
}
