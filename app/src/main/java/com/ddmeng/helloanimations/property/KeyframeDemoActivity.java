package com.ddmeng.helloanimations.property;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ddmeng.helloanimations.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KeyframeDemoActivity extends AppCompatActivity {
    @BindView(R.id.text_view_1)
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyframe_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.play_button)
    void onPlayButtonClick() {
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.5f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
        ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(textView1, pvhRotation);
        rotationAnim.setDuration(5000);
        rotationAnim.start();
    }
}
