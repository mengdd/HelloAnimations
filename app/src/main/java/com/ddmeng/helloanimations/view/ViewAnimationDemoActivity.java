package com.ddmeng.helloanimations.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.ddmeng.helloanimations.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewAnimationDemoActivity extends AppCompatActivity {

    @BindView(R.id.target)
    View targetView;

    private RotateAnimation turnUpAnimation;
    private RotateAnimation turnDownAnimation;
    private TranslateAnimation translateAnimationOne;
    private TranslateAnimation translateAnimationTwo;
    private AnimationSet alphaAnimationSet;
    private ScaleAnimation scaleAnimation;
    private Animation animationSet;

    private int stateId = 0;
    private static final int STATE_COUNT = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation_demo);
        ButterKnife.bind(this);
        initAnimations();
    }

    @OnClick(R.id.start_button)
    void onStartButtonClick() {
        switch (stateId) {
            case 0:
                targetView.startAnimation(turnDownAnimation);
                break;
            case 1:
                targetView.startAnimation(turnUpAnimation);
                break;
            case 2:
                targetView.startAnimation(translateAnimationOne);
                break;
            case 3:
                targetView.startAnimation(translateAnimationTwo);
                break;
            case 4:
                targetView.startAnimation(alphaAnimationSet);
                break;
            case 5:
                targetView.startAnimation(scaleAnimation);
                break;
            case 6:
                targetView.startAnimation(animationSet);
                break;

            default:
                break;
        }

        stateId = (++stateId) % STATE_COUNT;

    }

    @OnClick(R.id.target)
    void onTargetClick() {
        Toast.makeText(this, "Target Clicked!", Toast.LENGTH_SHORT).show();
    }

    private void initAnimations() {
        turnUpAnimation = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        turnUpAnimation.setInterpolator(new LinearInterpolator());
        turnUpAnimation.setDuration(500);
        turnUpAnimation.setFillAfter(true);

        turnDownAnimation = new RotateAnimation(-180, 0,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        turnDownAnimation.setInterpolator(new LinearInterpolator());
        turnDownAnimation.setDuration(500);
        turnDownAnimation.setFillAfter(true);

        translateAnimationOne = new TranslateAnimation(0, 200, 0, 200);
        translateAnimationOne
                .setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimationOne.setDuration(1000);
        translateAnimationOne.setFillAfter(true);

        translateAnimationTwo = new TranslateAnimation(200, 200, 0, 200);
        translateAnimationTwo
                .setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimationTwo.setDuration(1000);
        translateAnimationTwo.setFillAfter(true);

        AlphaAnimation alphaAnimationOne = new AlphaAnimation(1, 0);
        alphaAnimationOne.setDuration(1000);
        alphaAnimationOne.setFillAfter(true);

        AlphaAnimation alphaAnimationTwo = new AlphaAnimation(0, 1);
        alphaAnimationTwo.setDuration(1000);
        alphaAnimationTwo.setStartOffset(1000);
        alphaAnimationTwo.setFillAfter(true);

        alphaAnimationSet = new AnimationSet(false);
        alphaAnimationSet.addAnimation(alphaAnimationOne);
        alphaAnimationSet.addAnimation(translateAnimationOne);
//        alphaAnimationSet.addAnimation(alphaAnimationTwo);
        // fail case

        scaleAnimation = new ScaleAnimation(1, 1.5f, 1, 1.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);

        animationSet = AnimationUtils.loadAnimation(this, R.anim.view_animation_set);

    }
}
