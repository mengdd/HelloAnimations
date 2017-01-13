package com.ddmeng.helloanimations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ddmeng.helloanimations.layout.DefaultLayoutAnimationDemoActivity;
import com.ddmeng.helloanimations.layout.LayoutAnimationsDemoActivity;
import com.ddmeng.helloanimations.property.BasicPropertyAnimationDemo;
import com.ddmeng.helloanimations.property.CardFlipActivity;
import com.ddmeng.helloanimations.property.KeyframeDemoActivity;
import com.ddmeng.helloanimations.property.ViewPropertyAnimationDemoActivity;
import com.ddmeng.helloanimations.view.ViewAnimationDemoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.sample_list)
    ListView sampleListView;

    private Sample[] samples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        samples = new Sample[]{
                new Sample(R.string.basic_property_animation_demo, BasicPropertyAnimationDemo.class),
                new Sample(R.string.view_property_animation_demo, ViewPropertyAnimationDemoActivity.class),
                new Sample(R.string.keyframe_demo, KeyframeDemoActivity.class),
                new Sample(R.string.default_layout_animation_demo, DefaultLayoutAnimationDemoActivity.class),
                new Sample(R.string.layout_animations_demo, LayoutAnimationsDemoActivity.class),
                new Sample(R.string.view_animation_demo, ViewAnimationDemoActivity.class),
                new Sample(R.string.card_flip_demo, CardFlipActivity.class)

        };
        sampleListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, samples));
    }


    @OnItemClick(R.id.sample_list)
    void onSampleItemClick(AdapterView<?> parent, View view,
                           int position, long id) {
        startActivity(new Intent(this, samples[position].activityClass));

    }

    private class Sample {
        private final CharSequence title;
        private final Class<? extends Activity> activityClass;

        public Sample(int titleResId, Class<? extends Activity> activityClass) {
            this.activityClass = activityClass;
            this.title = getResources().getString(titleResId);

        }

        @Override
        public String toString() {
            return title.toString();
        }
    }
}
