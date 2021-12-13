package com.example.projectn1.profile.fullPages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.projectn1.R;

public class FullImageFragment extends Fragment {
    AppCompatImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =   inflater.inflate(R.layout.full_image_layout, container, false);
        imageView = view.findViewById(R.id.fullImage);
        loadImage();
        return  view;
    }

    private void loadImage() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String imageUrl = bundle.getString("imageUrl");

            System.out.println("imageUrl: " + imageUrl);

            Glide.with (this)
                    .load (imageUrl)
                    .centerCrop ()
                    .placeholder ( R.drawable.ic_loading)
                    .error (R.drawable.ic_error)
                    .into (imageView);
        }
    }
}
