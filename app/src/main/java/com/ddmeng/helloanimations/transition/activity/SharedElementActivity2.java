package com.ddmeng.helloanimations.transition.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.view.Window;

import com.ddmeng.helloanimations.R;

public class SharedElementActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setSharedElementEnterTransition(new ChangeBounds());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element2);
    }
}
