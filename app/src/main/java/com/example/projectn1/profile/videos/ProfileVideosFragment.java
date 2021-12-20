package com.example.projectn1.profile.videos;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectn1.R;
import com.example.projectn1.dto.SearchVideos;
import com.example.projectn1.dto.Video;
import com.example.projectn1.profile.fullPages.FullVideoFragment;
import com.example.projectn1.profile.fullPages.OnClickFullExhibitor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileVideosFragment extends Fragment implements OnClickFullExhibitor{
    View view;
    ProfileVideoAdapter adapter = new ProfileVideoAdapter();

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        view = inflater.inflate(R.layout.profile_layout, container, false);
        videosProfilePage();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        com.example.projectn1.dto.Videos videosS = com.example.projectn1.dto.Videos.create();
        Call<SearchVideos> camera = videosS.searchVideo("videos");

        camera.enqueue(new Callback<SearchVideos>() {
            @Override
            public void onResponse(Call<SearchVideos> call, Response<SearchVideos> response) {
                SearchVideos body = response.body();
                System.out.println(body);

                if (body != null) {
                    List<Video> videos = body.getVideos();
                    System.out.println(videos);

                    ArrayList<Videos> profileVideo = new ArrayList<>();
                    if (videos != null) {

                        for (Video video : videos) {
                            System.out.println(video);
                            profileVideo.add(new Videos(video.getImage()));
                        }
                        System.out.println(profileVideo);
                        adapter.setVideos(profileVideo);
                    }
                    else {
                        System.out.println("No videos available");
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchVideos> call, Throwable t) {

            }
        });

    }

    private void videosProfilePage() {
        RecyclerView recyclerView = view.findViewById(R.id.recViewProfile);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                getContext(),
                3,
                RecyclerView.VERTICAL,
                false
        );

        recyclerView.setLayoutManager(gridLayoutManager);

        adapter.setOnClickExhibitor(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onShowFull(String videoUrl) {
        System.out.println(videoUrl);

        Bundle bundle = new Bundle();
        bundle.putString("videoUrl", videoUrl);
        FullVideoFragment fullVideoFragment = new FullVideoFragment();
        fullVideoFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.simpleVideoView, fullVideoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
