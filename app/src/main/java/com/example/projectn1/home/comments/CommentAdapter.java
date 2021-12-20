package com.example.projectn1.home.comments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectn1.R;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentHolder> {
    ArrayList<Comment> commentArrayList = new ArrayList<>();

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dialog_bottom_comment,parent,false);
        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        Comment comment = commentArrayList.get(position);
        holder.initData(comment);

        AppCompatButton save = holder.itemView.findViewById(R.id.save);
        save.setOnClickListener(v -> {
            System.out.println("mi ban");
        });
    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }

    public void setComments(List<Comment> comment) {
        commentArrayList.clear();
        commentArrayList.addAll(comment);
        notifyDataSetChanged();
    }
}

class CommentHolder extends RecyclerView.ViewHolder {

    AppCompatTextView comments = itemView.findViewById(R.id.comment);

    public CommentHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void initData(Comment comment) {
        comments.setText(comment.getComment());
    }
}