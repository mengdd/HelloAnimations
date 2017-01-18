package com.ddmeng.helloanimations.transition.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.util.Pair;
import android.view.View;
import android.view.Window;

import com.ddmeng.helloanimations.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SharedElementActivity extends AppCompatActivity {

    @BindView(R.id.shared_text)
    View sharedText;
    @BindView(R.id.shared_image)
    View sharedElementImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setSharedElementExitTransition(new ChangeBounds());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.start_next_button)
    void onNextButtonClick() {
        Intent intent = new Intent(SharedElementActivity.this, SharedElementActivity2.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            // single element
//            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this,
//                    sharedElementImageView, getString(R.string.transition_name_activity_image));

            // multiple element
            Pair<View, String> pair1 = Pair.create(sharedElementImageView, getString(R.string.transition_name_activity_image));
            Pair<View, String> pair2 = Pair.create(sharedText, getString(R.string.transition_name_activity_text));
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, pair1, pair2);
            startActivity(intent, activityOptions.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
