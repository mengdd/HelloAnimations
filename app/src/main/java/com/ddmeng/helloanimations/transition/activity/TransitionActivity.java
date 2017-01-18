package com.ddmeng.helloanimations.transition.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;

import com.ddmeng.helloanimations.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransitionActivity extends AppCompatActivity {
    static final String EXTRA_SOURCE = "source";
    static final String EXTRA_TYPE = "type";
    static final int SOURCE_PROGRAMMATICALLY = 0;
    static final int SOURCE_XML = 1;
    static final int TYPE_EXPLODE = 0;
    static final int TYPE_SLIDE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        ButterKnife.bind(this);
        setupWindowAnimation();
    }

    private void setupWindowAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade enterTransition = new Fade(); //API 19
            enterTransition.setDuration(2000);
            getWindow().setEnterTransition(enterTransition); // API 21
        }
    }

    @OnClick(R.id.explode_button_1)
    void onExplodeButton1Click() {
        Intent intent = new Intent(TransitionActivity.this, TransitionActivity2.class);
        intent.putExtra(EXTRA_SOURCE, SOURCE_PROGRAMMATICALLY);
        intent.putExtra(EXTRA_TYPE, TYPE_EXPLODE);
        transitionTo(intent);
    }

    @OnClick(R.id.explode_button_2)
    void onExplodeButton2Click() {
        Intent intent = new Intent(TransitionActivity.this, TransitionActivity2.class);
        intent.putExtra(EXTRA_SOURCE, SOURCE_XML);
        intent.putExtra(EXTRA_TYPE, TYPE_EXPLODE);
        transitionTo(intent);
    }

    @OnClick(R.id.slide_button_1)
    void onSlideButton1Click() {
        Intent intent = new Intent(TransitionActivity.this, TransitionActivity2.class);
        intent.putExtra(EXTRA_SOURCE, SOURCE_PROGRAMMATICALLY);
        intent.putExtra(EXTRA_TYPE, TYPE_SLIDE);
        transitionTo(intent);
    }

    @OnClick(R.id.slide_button_2)
    void onSlideButton2Click() {
        Intent intent = new Intent(TransitionActivity.this, TransitionActivity2.class);
        intent.putExtra(EXTRA_SOURCE, SOURCE_XML);
        intent.putExtra(EXTRA_TYPE, TYPE_SLIDE);
        transitionTo(intent);
    }

    @SuppressWarnings("unchecked")
    private void transitionTo(Intent intent) {
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        startActivity(intent, activityOptionsCompat.toBundle());
    }
}
