package com.ddmeng.helloanimations.property;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;

import com.ddmeng.helloanimations.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewPropertyAnimationDemoActivity extends AppCompatActivity {

    @BindView(R.id.text_view_1)
    TextView textView1;
    @BindView(R.id.text_view_2)
    TextView textView2;
    @BindView(R.id.text_view_3)
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property_animation_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.play_button)
    void onPlayButtonClick() {
        // method 1:
        ObjectAnimator animX = ObjectAnimator.ofFloat(textView1, "x", 150f);
        ObjectAnimator animY = ObjectAnimator.ofFloat(textView1, "y", 300f);
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(animX, animY);
        animSetXY.start();

        // method 2:
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 150f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 300f);
        ObjectAnimator.ofPropertyValuesHolder(textView2, pvhX, pvhY).start();

        // method 3:
        ViewPropertyAnimator viewPropertyAnimator = textView3.animate();
        viewPropertyAnimator.x(150f).y(300f);
    }
}
