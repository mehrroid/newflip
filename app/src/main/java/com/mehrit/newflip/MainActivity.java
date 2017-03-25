package com.mehrit.newflip;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayoutFront;
    FrameLayout frameLayoutBack;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;
    private TextViewListener textViewListener;
    private FrameLayout mainFrameLayout;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFrameLayout = (FrameLayout) findViewById(R.id.frameLayoutActivity);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(params);
        textViewListener =new TextViewListener();

        /*

        //included for front of card
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        frameLayoutFront = new FrameLayout(this);
        frameLayoutFront.setLayoutParams(params2);
        TextView imgFront = new TextView(this);
        imgFront.setText("Front");
        imgFront.setTextSize(40);
        imgFront.setTextColor(Color.YELLOW);
        imgFront.setBackgroundResource(R.color.colorPrimaryDark);
        imgFront.setOnClickListener(textViewListener);

//        imgFront.setBackgroundResource(R.drawable.rectangle);
        frameLayoutFront.addView(imgFront);
        frameLayout.addView(frameLayoutFront);
        //
        //included2 for back of card
    //    FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        frameLayoutBack = new FrameLayout(this);
        frameLayoutBack.setLayoutParams(params2);
        TextView imgBack = new TextView(this);
        imgBack.setText("Back");
        imgBack.setTextSize(40);
        imgBack.setTextColor(Color.GREEN);
        imgBack.setBackgroundResource(R.color.colorAccent);
        imgBack.setOnClickListener(textViewListener);
//        imgBack.setBackgroundResource(R.drawable.rectangle);
        frameLayoutBack.addView(imgBack);
        frameLayout.addView(frameLayoutBack);
//        View front = getLayoutInflater().inflate(R.layout.activity_front, null);
//        frameLayout.addView(front);
        mainFrameLayout.addView(frameLayout);
        */

        createTextViews(1,2);
        loadAnimations();
        changeCameraDistance();
    }

    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        frameLayoutFront.setCameraDistance(scale);
        frameLayoutBack.setCameraDistance(scale);
    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.in_animation);
    }

    private View createTextViews(int x, int y){


//        Typeface fontawsome = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        frameLayoutFront = new FrameLayout(this);
        frameLayoutFront.setLayoutParams(params2);
        TextView imgFront = new TextView(this);
        imgFront.setText("Front");
        imgFront.setTextSize(40);
        imgFront.setTextColor(Color.YELLOW);
        imgFront.setBackgroundResource(R.color.colorPrimaryDark);
        imgFront.setOnClickListener(textViewListener);
        imgFront.setId(100*x+y);
//        imgFront.setBackgroundResource(R.drawable.rectangle);
        frameLayoutFront.addView(imgFront);
        frameLayout.addView(frameLayoutFront);

        frameLayoutBack = new FrameLayout(this);
        frameLayoutBack.setLayoutParams(params2);
        TextView imgBack = new TextView(this);
        imgBack.setText("Back");
        imgBack.setTextSize(40);
        imgBack.setTextColor(Color.GREEN);
        imgBack.setBackgroundResource(R.color.colorAccent);
        imgBack.setOnClickListener(textViewListener);
        imgFront.setId(1000*x+y);
//        imgBack.setBackgroundResource(R.drawable.rectangle);
        frameLayoutBack.addView(imgBack);
        frameLayout.addView(frameLayoutBack);
        mainFrameLayout.addView(frameLayout);

        return frameLayout;
    }


    class TextViewListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.i("cards-onClick","in on TextViewListener");
            if (!mIsBackVisible) {
                mSetRightOut.setTarget(frameLayoutFront);
                mSetLeftIn.setTarget(frameLayoutBack);
                mSetRightOut.start();
                mSetLeftIn.start();
                mIsBackVisible = true;

            } else {
                mSetRightOut.setTarget(frameLayoutBack);
                mSetLeftIn.setTarget(frameLayoutFront);
                mSetRightOut.start();
                mSetLeftIn.start();
                mIsBackVisible = false;
            }
        }
    }
}
