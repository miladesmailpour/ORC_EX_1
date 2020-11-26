package com.example.ocr_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public float prvPoint=0.0f, newPoint=1.0f, rate=1.0f;
    public TextView dagree;
    public int i = 0;
    public String str = "";
    public String[] arr = {"1", "2", "3", "4", "5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dagree = findViewById(R.id.tv_dagree);
        Button rotateBtn = findViewById(R.id.btn_R);
        ImageView myImageView = (ImageView)findViewById(R.id.iv_c);
        Handler mHandler = new Handler();

        AnimationSet animSet = new AnimationSet(true);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.setFillAfter(true);
        animSet.setFillEnabled(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<61;i++) {


                    try {
                        Thread.sleep(1000);

                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                final RotateAnimation animRotate = new RotateAnimation(prvPoint, newPoint,
                                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                                newPoint = counter(newPoint);

                                animSet.addAnimation(animRotate);
                                myImageView.startAnimation(animSet);
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();



        rotateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                final RotateAnimation animRotate = new RotateAnimation(prvPoint, newPoint,
//                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//                newPoint = counter(newPoint);
//
////                animRotate.setDuration(10);
////                animRotate.setFillAfter(true);
//                animSet.addAnimation(animRotate);
//                myImageView.startAnimation(animSet);
            }
        });
    }
    private float counter(float currPoint) {
        prvPoint = currPoint;
        str = Float.toString(prvPoint);
        dagree.setText(Float.toString(prvPoint));
        return currPoint + rate;
    }


}