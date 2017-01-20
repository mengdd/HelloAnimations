package com.ddmeng.helloanimations.transition.fragment;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ddmeng.helloanimations.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image)
    ImageView imageView;
    private ImageAdapter.ImageItemClickListener listener;
    private int resourceId;

    public ImageViewHolder(View itemView, ImageAdapter.ImageItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        ButterKnife.bind(this, itemView);
    }

    public void populate(int resourceId) {
        this.resourceId = resourceId;
        imageView.setImageResource(resourceId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String transitionName = String.format(itemView.getContext().getString(R.string.transition_name_fragment_image),
                    getAdapterPosition(), resourceId);
            imageView.setTransitionName(transitionName);
        }
    }

    @OnClick(R.id.image_item)
    void onItemClicked() {
        if (listener != null) {
            listener.onImageItemClicked(getAdapterPosition(), resourceId, imageView);
        }

    }
}
