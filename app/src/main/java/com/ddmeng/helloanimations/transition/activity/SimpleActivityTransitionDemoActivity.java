package com.ddmeng.helloanimations.transition.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ddmeng.helloanimations.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SimpleActivityTransitionDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_transition_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.next)
    void onNextButtonClick() {
        startActivity(new Intent(this, SimpleTransitionSecondActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.fade_out_left);
    }
}
