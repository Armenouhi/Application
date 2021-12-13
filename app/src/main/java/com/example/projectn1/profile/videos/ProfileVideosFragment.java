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
import com.example.projectn1.profile.fullPages.FullImageFragment;
import com.example.projectn1.profile.fullPages.OnClickFullExhibitor;

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
