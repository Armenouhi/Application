package com.example.projectn1.home.comments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectn1.R;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentHolder> {
    ArrayList<Comment> comments = new ArrayList<>();
    private OnSaveData saveData;

    public void setSaveListener(OnSaveData saveData) {
        saveData = saveData;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_comment,parent,false);
        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.initData(comment);

        AppCompatImageView saveBtn = holder.itemView.findViewById(R.id.save);
        saveBtn.setOnClickListener(v -> {
            System.out.println("Save data");
        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void setImages(List<Comment> comment) {
        comments.clear();
        comments.addAll(comment);
        notifyDataSetChanged();
    }
}

class CommentHolder extends RecyclerView.ViewHolder {
    AppCompatTextView comment = itemView.findViewById(R.id.comment);

    public CommentHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void initData(Comment comment) {
        this.comment.setText(comment.getText());
    }
}