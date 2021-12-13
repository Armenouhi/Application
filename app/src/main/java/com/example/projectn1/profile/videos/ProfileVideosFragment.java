package com.example.projectn1.profile.videos;

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
import com.example.projectn1.profile.fullPages.FullImageFragment;
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
        Call<SearchVideos> camera = videosS.searchVideo("santa");

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

                            profileVideo.add(new Videos(
                                    video.getSrc().getLargeUrl()));
                        }
                        adapter.setVideos(profileVideo);
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
        adapter.setVideos(Videos.getVideos());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onShowFull(String videoUrl) {
        System.out.println(videoUrl);

        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", videoUrl);
        FullImageFragment fullImageFragment = new FullImageFragment();
        fullImageFragment.setArguments(bundle);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHomePage, fullImageFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
