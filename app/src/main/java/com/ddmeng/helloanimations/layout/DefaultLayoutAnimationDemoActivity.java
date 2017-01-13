package com.ddmeng.helloanimations.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ddmeng.helloanimations.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DefaultLayoutAnimationDemoActivity extends AppCompatActivity {

    @BindView(R.id.container)
    LinearLayout layoutContainer;

    private int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_layout_animation_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_new_button)
    void onAddNewButtonClick() {
        Button button = new Button(this);
        button.setText(String.valueOf(num++));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutContainer.removeView(v);
            }
        });
        layoutContainer.addView(button, Math.min(1, layoutContainer.getChildCount()));
    }
}
