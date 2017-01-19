package com.ddmeng.helloanimations.transition.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddmeng.helloanimations.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SharedElementFragment extends Fragment implements ImageAdapter.ImageItemClickListener {

    private static final int[] IMAGE_IDS = new int[]{R.drawable.image1, R.drawable.image2,
            R.drawable.image2, R.drawable.image1};

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shared_element, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ImageAdapter adapter = new ImageAdapter(IMAGE_IDS, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onImageItemClicked(int position, int resourceId) {
        SharedElementDetailFragment detailFragment = SharedElementDetailFragment.newInstance(resourceId);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit();
    }


}
