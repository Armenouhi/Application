package com.example.projectn1.home.dialogBottomFilters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectn1.R;
import com.example.projectn1.home.comments.Comment;
import com.example.projectn1.home.comments.CommentsAdapter;
import com.example.projectn1.home.comments.OnSaveData;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddCommentDBFragment extends BottomSheetDialogFragment implements OnSaveData {
    CommentsAdapter adapterComment = new CommentsAdapter();
    View view;


    public static AddCommentDBFragment newInstance() {
        return new AddCommentDBFragment();
    };

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        view = inflater.inflate(
                R.layout.dialog_bottom_comment,
                container, false
        );

        listComments();

        return view;
    }


    private void listComments() {
        RecyclerView recyclerView = view.findViewById(R.id.recCommentView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                getContext(),
                1,
                RecyclerView.VERTICAL,
                false
        );

        recyclerView.setLayoutManager(gridLayoutManager);

        adapterComment.setComments(this);
        adapterComment.setCommentskist(Comment.getComments());
        recyclerView.setAdapter(adapterComment);
    }

    @Override
    public void onSave() {

    }
}
