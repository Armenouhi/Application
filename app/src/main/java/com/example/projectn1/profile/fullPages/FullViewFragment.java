package com.example.projectn1.profile.fullPages;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectn1.R;

public class FullViewFragment extends Fragment {
    VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view =   inflater.inflate(R.layout.full_video_layout, container, false);
        videoView = view.findViewById(R.id.simpleVideoView);
        loadVideo();
        return  view;
    }

    private void loadVideo() {
        VideoView simpleVideoView = (VideoView) videoView.findViewById(R.id.simpleVideoView);
        if (getActivity() != null) {
            simpleVideoView.setVideoURI(Uri.parse("android.resource://"
                    + getActivity().getPackageName()
                    + "/" + R.raw.armath));
            simpleVideoView.start();
        } else {
            System.out.println("Can't open this video ");
        }
    }
}
