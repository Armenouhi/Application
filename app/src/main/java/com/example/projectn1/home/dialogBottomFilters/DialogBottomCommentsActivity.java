package com.example.projectn1.home.dialogBottomFilters;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectn1.R;

public class DialogBottomCommentsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDialogFragment();
    }


    public void initDialogFragment() {
        AddCommentDBFragment addFiltersBottomDialogFragment =
                AddCommentDBFragment.newInstance();
        addFiltersBottomDialogFragment.show(getSupportFragmentManager(),
                "add_photo_dialog_fragment");
    }


}

