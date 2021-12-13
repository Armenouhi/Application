package com.example.projectn1.profile.images;

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
import com.example.projectn1.dto.Photo;
import com.example.projectn1.dto.SearchPhotos;
import com.example.projectn1.profile.fullPages.FullImageFragment;
import com.example.projectn1.profile.fullPages.OnClickFullExhibitor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileImagesFragment extends Fragment implements OnClickFullExhibitor {
    View view;
    ProfileImagesAdapter adapter = new ProfileImagesAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState
    ) {
        view = inflater.inflate(R.layout.profile_layout, container, false);
        imagesProfilePage();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        com.example.projectn1.dto.Images images  = com.example.projectn1.dto.Images.create();
        Call<SearchPhotos> city = images.searchImage("city");

        city.enqueue(new Callback<SearchPhotos>() {
            @Override
            public void onResponse(Call<SearchPhotos> call, Response<SearchPhotos> response) {
                SearchPhotos body = response.body();
                if (body != null) {
                    List<Photo> photos = body.getPhotos();

                    ArrayList<Images> profilePhoto = new ArrayList<>();

                    for (Photo photo : photos) {

                        profilePhoto.add(new Images(
                                photo.getSrc().getLargeUrl()));
                    }
                    adapter.setImages(profilePhoto);
                }
            }

            @Override
            public void onFailure(Call<SearchPhotos> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });


    }

    private void imagesProfilePage() {
        RecyclerView recyclerView = view.findViewById(R.id.recViewProfile);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                getContext(),
                3,
                RecyclerView.VERTICAL,
                false
        );

        recyclerView.setLayoutManager(gridLayoutManager);

        adapter.setOnClickExhibitor(this);

//        adapter.setImages(Images.getImages());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onShowFull(String url) {
        System.out.println(url);

        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", url);
        FullImageFragment fullImageFragment = new FullImageFragment();
        fullImageFragment.setArguments(bundle);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHomePage, fullImageFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
