package com.ddmeng.helloanimations.transition.fragment;

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
    }

    @OnClick(R.id.image_item)
    void onItemClicked() {
        if (listener != null) {
            listener.onImageItemClicked(getAdapterPosition(), resourceId);
        }

    }
}
