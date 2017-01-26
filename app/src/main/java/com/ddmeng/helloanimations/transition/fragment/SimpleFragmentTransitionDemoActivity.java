package com.ddmeng.helloanimations.transition.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ddmeng.helloanimations.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SimpleFragmentTransitionDemoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_fragment_transition_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_btn)
    void addFragment() {
        Fragment mTextFragmentOne = new FragmentV4();
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.push_left_in,
                        R.anim.push_left_out,
                        R.anim.push_right_in,
                        R.anim.push_right_out)
                .add(R.id.container, mTextFragmentOne)
                .addToBackStack(null)
                .commit();
    }

    @OnClick(R.id.remove_btn)
    void removeFragment() {
        getSupportFragmentManager().popBackStack();
    }

    public static class FragmentV4 extends Fragment {

        private static int sCount = 0;
        private int mId = 0;
        private final int[] mColors = new int[]{Color.RED, Color.GREEN,
                Color.BLUE, Color.BLACK};

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            ++sCount;
            mId = sCount;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            TextView textView = new TextView(getContext());
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setText(String.format(getString(R.string.fragment_no), mId));
            textView.setBackgroundColor(mColors[mId % mColors.length]);

            return textView;
        }
    }
}
