package com.example.drawingapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Андрей on 02.08.2017.
 */

public class RoundedImageView extends android.support.v7.widget.AppCompatImageView {

    int width, heigth;

    Paint paintBorder, paintFill;



    public RoundedImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        paintFill = new Paint();
        paintFill.setStyle(Paint.Style.FILL);
       paintFill.setColor(context.getResources().
              getColor(android.support.v7.appcompat.R.color.background_floating_material_light));
//        paintFill.setColor(Color.BLACK);
        paintFill.setAntiAlias(true);

        paintBorder = new Paint();
        paintBorder.setStyle(Paint.Style.STROKE);
        paintBorder.setColor(Color.BLACK);
        paintBorder.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        heigth = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(width / 2, heigth / 2, width / 2, paintBorder);
        circleInside(canvas);

    }

    private void circleInside(Canvas canvas){
        Path path = new Path();
        path.reset();
        if(!albumOrientation()){
            RectF oval = new RectF(0, heigth - (heigth / 2 + width / 2), width, (heigth / 2 + width / 2));
            path.moveTo(0, 0);
            path.lineTo(0, heigth);
            path.lineTo(width, heigth);
            path.lineTo(width, heigth / 2);
            path.arcTo(oval, 0, 360);
            path.lineTo(width, 0);
            path.close();
        }
        else {
            RectF oval = new RectF(width - (width / 2 + heigth / 2), 0,(width / 2 + heigth / 2), heigth);
            path.moveTo(0, 0);
            path.lineTo(0, heigth);
            path.lineTo(width, heigth);
            path.lineTo(width, heigth / 2);
            path.arcTo(oval, 0, 360);
            path.lineTo(width, heigth / 2);
            path.lineTo(width, 0);
            path.close();
        }
        canvas.drawPath(path, paintFill);

    }
    private boolean albumOrientation(){
        boolean result = false;
        if (width > heigth) result = true;
        return result;
    }
}
