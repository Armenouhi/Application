package com.example.projectn1.profile.fullPages;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectn1.R;

public class FullVideoFragment extends Fragment {
    VideoView simpleVideoView;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view =   inflater.inflate(R.layout.full_video_layout, container, false);
        simpleVideoView = view.findViewById(R.id.simpleVideoView);
        loadVideo();
        return  view;
    }

    private void loadVideo() {

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String videoUrl = bundle.getString("videoUrl");
            Uri uri = Uri.parse(videoUrl);
            simpleVideoView.setVideoURI(uri);
            simpleVideoView.setVideoPath(videoUrl);
            MediaController mediaController = new MediaController(simpleVideoView.getContext());
            mediaController.setAnchorView(simpleVideoView);
            mediaController.setMediaPlayer(simpleVideoView);
            simpleVideoView.setMediaController(mediaController);
            simpleVideoView.start();
            mediaController.show();
        }
    }
}
