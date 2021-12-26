package com.example.projectn1.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectn1.R;
import com.example.projectn1.home.dialogBottomFilters.DialogBottomCommentsActivity;

import java.util.ArrayList;
import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageHolder> {
    ArrayList<Image> homeImages = new ArrayList<>();
    private OnLikeListener onLike;
    private OnOpenPageListener openPageListener;
    private addCommentListener clickCommentListener;
    private OnClickShare shareListener;
    DialogBottomCommentsActivity commentsActivity;

    public void setOnLikeListener(OnLikeListener onLikeListener) {
        onLike = onLikeListener;
    }

    public void setOpenPageClickListener (OnOpenPageListener openPageListener) {
        this.openPageListener = openPageListener;
    }

    public void setClickCommentListener (addCommentListener clickCommentListener) {
        this.clickCommentListener = clickCommentListener;
    }

    public void setOnClickShare (OnClickShare share) {
        shareListener = share;
    }


    @NonNull
    @Override
    public HomePageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_home_view,parent,false);
        return new HomePageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageHolder holder, int position) {

        Image images = homeImages.get(position);
        holder.initData(images);

        AppCompatImageView commentButton = holder.itemView.findViewById(R.id.imageMessage);
        AppCompatImageView buttonShare = holder.itemView.findViewById(R.id.shareIcon);

        String fullName = images.getFullName();

        holder.image.setOnClickListener(view -> {
            openPageListener.changeName(fullName);
        });

        holder.title.setOnClickListener(view -> {
            openPageListener.changeName(fullName);
        });

        holder.comment.setOnClickListener(view -> {
            clickCommentListener.addComment(commentButton);
        });

        holder.shareI.setOnClickListener(v -> {
            shareListener.share(buttonShare);
        });

    }

    @Override
    public int getItemCount() {
        return homeImages.size();
    }


    public void setImages(List<Image> image) {
        homeImages.clear();
        homeImages.addAll(image);
        notifyDataSetChanged();
    }
}

class HomePageHolder extends RecyclerView.ViewHolder {
    private boolean changeColor = false;

    AppCompatImageView image = itemView.findViewById(R.id.logo);
    AppCompatTextView title = itemView.findViewById(R.id.title);
    AppCompatImageView imageView = itemView.findViewById(R.id.nature);
    AppCompatImageView heart = itemView.findViewById(R.id.imageHeart);
    AppCompatImageView comment = itemView.findViewById(R.id.imageMessage);
    AppCompatImageView shareI = itemView.findViewById(R.id.shareIcon);

    public HomePageHolder(@NonNull View itemView) {
        super(itemView);
    }


    public void initData(Image image) {
        Glide.with (itemView.getContext())
                .load (image.getImageUrl())
                .centerCrop ()
                .placeholder ( R.drawable.ic_loading)
                .error (R.drawable.ic_error)
                .into (this.image);

        Glide.with (itemView.getContext())
                .load (image.getImageUrl())
                .centerCrop ()
                .placeholder ( R.drawable.ic_loading)
                .error (R.drawable.ic_error)
                .into (imageView);

        title.setText(image.getFullName());


        heart.setOnClickListener(v -> {
            if (!changeColor) {
                heart.setImageResource(R.drawable.ic_baseline_favorite_24);
                changeColor = true;
            } else {
                heart.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                changeColor = false;
            }
        });

    }
}
