package com.mehrit.newflip;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayoutFront;
    FrameLayout frameLayoutBack;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout mainFrameLayout = (FrameLayout) findViewById(R.id.frameLayoutActivity);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(params);
        //included for front of card
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        frameLayoutFront = new FrameLayout(this);
        frameLayoutFront.setLayoutParams(params2);
        TextView imgFront = new TextView(this);
        imgFront.setText("Front");
        imgFront.setTextSize(40);
        imgFront.setTextColor(Color.YELLOW);
        imgFront.setBackgroundResource(R.color.colorPrimaryDark);

//        imgFront.setBackgroundResource(R.drawable.rectangle);
        frameLayoutFront.addView(imgFront);
        frameLayout.addView(frameLayoutFront);
        //
        //included2 for back of card
        FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        frameLayoutBack = new FrameLayout(this);
        frameLayoutBack.setLayoutParams(params3);
        TextView imgBack = new TextView(this);
        imgBack.setText("Back");
        imgBack.setTextSize(40);
        imgBack.setTextColor(Color.GREEN);
        imgBack.setBackgroundResource(R.color.colorAccent);
//        imgBack.setBackgroundResource(R.drawable.rectangle);
        frameLayoutBack.addView(imgBack);
        frameLayout.addView(frameLayoutBack);
//        View front = getLayoutInflater().inflate(R.layout.activity_front, null);
//        frameLayout.addView(front);
        mainFrameLayout.addView(frameLayout);
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

    public void flipCard(View view) {
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
