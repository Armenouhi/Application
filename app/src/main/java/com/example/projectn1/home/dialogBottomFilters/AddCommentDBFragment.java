package com.example.projectn1.home.dialogBottomFilters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectn1.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddCommentDBFragment extends BottomSheetDialogFragment {
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
        return inflater.inflate(R.layout.alert_dialog_comment, container, false);
    }
}
