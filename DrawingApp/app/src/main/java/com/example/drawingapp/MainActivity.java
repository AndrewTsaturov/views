package com.example.drawingapp;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import static com.example.drawingapp.R.drawable.anim_one;

public class MainActivity extends AppCompatActivity {

    Animation animationType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnerAnim = (Spinner) findViewById(R.id.spinner_animation);

        final RoundedImageView showAnim = (RoundedImageView) findViewById(R.id.anim_show);

        String[] spinnerValues = this.getResources().getStringArray(R.array.spinner_items);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,  android.R.layout.simple_spinner_dropdown_item,
                spinnerValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerAnim.setAdapter(adapter);
        spinnerAnim.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        animation(showAnim);
                        break;
                    case 1:
                        animationType = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_enlarge);
                        animation(showAnim, animationType);
                        break;
                    case 2:
                        animationType = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_smaller);
                        animation(showAnim, animationType);
                        break;
                    case 3:
                        animationType = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_smaller_alpha);
                        animation(showAnim, animationType);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
             animation(showAnim);
            }
        });
    }


    private void animation(RoundedImageView show){
        show.setBackgroundResource(R.drawable.cock_anim);
        AnimationDrawable anim = (AnimationDrawable) show.getBackground();
        anim.start();
    }

    private void animation(final RoundedImageView show, Animation animation){
       show.setBackgroundResource(R.drawable.anim_one);
       show.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                show.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
