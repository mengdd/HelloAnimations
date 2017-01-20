package com.ddmeng.helloanimations.transition.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddmeng.helloanimations.R;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private int[] imageResourceIds;
    private ImageItemClickListener listener;

    public ImageAdapter(int[] imageResourceIds, ImageItemClickListener listener) {
        this.imageResourceIds = imageResourceIds;
        this.listener = listener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_view_holder, parent, false);
        return new ImageViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.populate(imageResourceIds[position]);
    }

    @Override
    public int getItemCount() {
        return imageResourceIds != null ? imageResourceIds.length : 0;
    }

    public interface ImageItemClickListener {
        void onImageItemClicked(int position, int resourceId, View sharedView);
    }
}
