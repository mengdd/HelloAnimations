package com.ddmeng.helloanimations.transition.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ddmeng.helloanimations.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SharedElementFragment extends Fragment implements ImageAdapter.ImageItemClickListener {

    private static final int[] IMAGE_IDS = new int[]{R.drawable.image1, R.drawable.image2,
            R.drawable.image2, R.drawable.image1};

    @BindView(R.id.title)
    TextView titleTextView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private Transition sharedElementTransition;

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
        initAnimation();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ImageAdapter adapter = new ImageAdapter(IMAGE_IDS, this);
        recyclerView.setAdapter(adapter);
    }

    private void initAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            sharedElementTransition = TransitionInflater.from(getActivity()).inflateTransition(R.transition.shared_element_transition_set);
        }
    }

    @Override
    public void onImageItemClicked(int position, int resourceId, View sharedView) {
        SharedElementDetailFragment detailFragment = SharedElementDetailFragment.newInstance(position, resourceId);
        String transitionName = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            detailFragment.setSharedElementEnterTransition(sharedElementTransition);
            detailFragment.setSharedElementReturnTransition(sharedElementTransition);
            transitionName = sharedView.getTransitionName();
        }

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(titleTextView, getString(R.string.transition_name_fragment_text))
                .addSharedElement(sharedView, transitionName)
                .setAllowOptimization(false) //without this, pop back will throw NullPointerException, maybe a bug in support.v4
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit();
    }


}
