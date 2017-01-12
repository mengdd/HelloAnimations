package com.ddmeng.helloanimations.property;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ddmeng.helloanimations.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasicPropertyAnimationDemo extends AppCompatActivity {

    @BindView(R.id.animation_view)
    MyAnimationView myAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_property_animation_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.play_button)
    void onPlayButtonClick() {
        myAnimationView.play();
    }
}
