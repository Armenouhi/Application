package com.example.projectn1.profile.images;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectn1.R;
import com.example.projectn1.profile.fullPages.OnClickFullExhibitor;

import java.util.ArrayList;
import java.util.List;

public class ProfileImagesAdapter extends RecyclerView.Adapter<ProfileHolder> {
    ArrayList<Images> images = new ArrayList<>();
    private OnClickFullExhibitor exhibitor;

    public void setOnClickExhibitor (OnClickFullExhibitor exhibitor) {
        this.exhibitor = exhibitor;
    }

    @NonNull
    @Override
    public ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_profile, null);
        return new ProfileHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHolder holder, int position) {
        Images image = images.get(position);

        holder.imageViewProfile.setOnClickListener(view -> {
            System.out.println(image.getUrl());
            exhibitor.onShowFull(image.getUrl());
        });

        holder.initData(image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    public void setImages(List<Images> images) {
        this.images.clear();
        this.images.addAll(images);
        notifyDataSetChanged();
    }
}

class ProfileHolder extends RecyclerView.ViewHolder {
    AppCompatImageView imageViewProfile = itemView.findViewById(R.id.imageProfilePage);

    public ProfileHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void initData(Images images) {
        Glide.with(itemView.getContext())
                .load(images.getUrl())
                .into(imageViewProfile);
    }

}