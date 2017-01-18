package com.ddmeng.helloanimations.transition.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.Visibility;

import com.ddmeng.helloanimations.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ddmeng.helloanimations.transition.activity.TransitionActivity.EXTRA_SOURCE;
import static com.ddmeng.helloanimations.transition.activity.TransitionActivity.EXTRA_TYPE;
import static com.ddmeng.helloanimations.transition.activity.TransitionActivity.SOURCE_PROGRAMMATICALLY;
import static com.ddmeng.helloanimations.transition.activity.TransitionActivity.TYPE_EXPLODE;

public class TransitionActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition2);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupWindowAnimations();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Transition transition;

        Bundle extras = getIntent().getExtras();
        int source = extras.getInt(EXTRA_SOURCE);
        int type = extras.getInt(EXTRA_TYPE);

        if (SOURCE_PROGRAMMATICALLY == source) {
            Visibility enterTransition;
            if (TYPE_EXPLODE == type) {
                enterTransition = new Explode();
            } else {
                enterTransition = new Slide();
            }
            enterTransition.setDuration(1000);
            transition = enterTransition;
        } else {
            if (TYPE_EXPLODE == type) {
                transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
            } else {
                transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom);
            }

        }
        getWindow().setEnterTransition(transition);
    }

    @OnClick(R.id.exit_button)
    void onExitButtonClick() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

}
