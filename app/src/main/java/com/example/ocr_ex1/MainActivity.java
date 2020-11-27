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
    public int i;
    public String str = "";
    public boolean flag = false;
    public AnimationSet animSet;
    public  ImageView ivLine;
    public Handler mHandler;
    //    public String[] arr = {"1", "2", "3", "4", "5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dagree = findViewById(R.id.tv_dagree);
        Button rotateBtn = findViewById(R.id.btn_R);
//        ImageView ivCircle = (ImageView)findViewById(R.id.iv_c);
        ivLine = (ImageView)findViewById(R.id.iv_l);
        mHandler = new Handler();

        animSet = new AnimationSet(true);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.setFillAfter(true);
        animSet.setFillEnabled(true);

        test();



        rotateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            switch (Boolean.toString(flag)) {
                case "true" :
                    flag = false;
                    test();
                    break;
                case "false" :
                    flag = true;
                    break;
            }
            }
        });
    }
    private float counter(float currPoint) {

        prvPoint = currPoint;
        str = Float.toString(prvPoint);
        dagree.setText(Float.toString(prvPoint));
        return currPoint + rate;
    }
    private void test () {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(i=0;;i++) {
                    Log.d("TAG", "run: " + i);
                    if (flag) { break;}
                    try {
                        Thread.sleep(1000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                final RotateAnimation animRotate = new RotateAnimation(0.0f, 6.0f,
                                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                                Log.d("TAG", "run: " + " : " + prvPoint + "->" + newPoint);
                                newPoint = counter(newPoint);

                                animSet.addAnimation(animRotate);
//                                ivCircle.startAnimation(animSet);
                                ivLine.startAnimation(animSet);
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();
    }


}