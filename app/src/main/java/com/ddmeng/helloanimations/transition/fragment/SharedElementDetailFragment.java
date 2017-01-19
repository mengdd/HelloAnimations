package com.ddmeng.helloanimations.transition.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ddmeng.helloanimations.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SharedElementDetailFragment extends Fragment {
    private static final String ARG_RESOURCE_ID = "param1";

    @BindView(R.id.detail_image)
    ImageView detailImageView;
    private int resourceId;

    public static SharedElementDetailFragment newInstance(int resourceId) {
        SharedElementDetailFragment fragment = new SharedElementDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_RESOURCE_ID, resourceId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            resourceId = getArguments().getInt(ARG_RESOURCE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shared_element_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        detailImageView.setImageResource(resourceId);
    }
}
