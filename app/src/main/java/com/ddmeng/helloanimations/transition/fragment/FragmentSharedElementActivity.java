package com.ddmeng.helloanimations.transition.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ddmeng.helloanimations.R;

import butterknife.ButterKnife;

public class FragmentSharedElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_shared_element);
        ButterKnife.bind(this);
        addFragment();
    }

    private void addFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new SharedElementFragment())
                .commit();
    }
}
