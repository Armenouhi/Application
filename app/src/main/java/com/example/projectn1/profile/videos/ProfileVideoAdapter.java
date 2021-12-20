package com.example.projectn1.profile.videos;

import android.annotation.SuppressLint;
import android.provider.MediaStore;
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

public class ProfileVideoAdapter extends RecyclerView.Adapter<VideoHolder> {
    ArrayList<Videos> videos = new ArrayList<>();
    private OnClickFullExhibitor exhibitor;

    public void setOnClickExhibitor (OnClickFullExhibitor exhibitor) {
        this.exhibitor = exhibitor;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_profile, null);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        Videos  videos= this.videos.get(position);

        holder.imageVideoVProfile.setOnClickListener(view -> {
            exhibitor.onShowFull(videos.getVideoUrl());
        });

        holder.initData(videos);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public void setVideos(List<Videos> videos) {
        this.videos.clear();
        this.videos.addAll(videos);
        notifyDataSetChanged();
    }
}

class VideoHolder extends RecyclerView.ViewHolder {
    AppCompatImageView imageVideoVProfile = itemView.findViewById(R.id.imageProfilePage);

    public VideoHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void initData(Videos videos) {
        Glide.with(itemView.getContext())
                .load(videos.getVideoUrl())
                .into(imageVideoVProfile);
    }
}