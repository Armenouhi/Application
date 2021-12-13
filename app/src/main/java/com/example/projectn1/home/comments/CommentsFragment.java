package com.example.projectn1.home.comments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectn1.R;

public class CommentsFragment extends Fragment {
    View view;
    CommentAdapter adapter = new CommentAdapter();
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        view =  inflater.inflate(R.layout.alert_dialog_comment, container, false);
        initComments();
        return view;
    }

    private void initComments() {
        RecyclerView recyclerView = view.findViewById(R.id.recCommentView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                getContext(),
                1,
                RecyclerView.VERTICAL,
                false
        );

        recyclerView.setLayoutManager(gridLayoutManager);

        adapter.setSaveListener(this::initComments);

        recyclerView.setAdapter(adapter);
    }


}
