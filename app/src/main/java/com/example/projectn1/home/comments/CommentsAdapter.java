package com.example.projectn1.home.comments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectn1.R;
import com.example.projectn1.profile.images.Images;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsHolder> {
    ArrayList<Comment> commentsList = new ArrayList<>();
    private OnSaveData saveData;

    public void setComments(OnSaveData saveData) {
        this.saveData = saveData;
    };

    @NonNull
    @Override
    public CommentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_comment, null);
        return new CommentsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsHolder holder, int position) {
        Comment comment = commentsList.get(position);
        holder.initData(comment);
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public void setCommentskist(List<Comment> comments) {
        this.commentsList.clear();
        this.commentsList.addAll(comments);
        notifyDataSetChanged();
    }
}

class CommentsHolder extends RecyclerView.ViewHolder {

    AppCompatTextView comment = itemView.findViewById(R.id.comment);

    public CommentsHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void initData(Comment comments) {
        comment.setText(comments.getComment());
    }
}
