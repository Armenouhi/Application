package com.example.projectn1.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectn1.R;
import com.example.projectn1.dio.Images;
import com.example.projectn1.dio.Photo;
import com.example.projectn1.dio.SearchPhotos;
import com.example.projectn1.home.comments.CommentsFragment;
import com.example.projectn1.home.dialogBottomFilters.AddCommentDBFragment;
import com.example.projectn1.profile.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageFragment extends Fragment
        implements OnLikeListener, OnOpenPageListener, OnClickCommentListener{



    HomePageAdapter adapter = new HomePageAdapter();
    View view;
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        view = inflater.inflate(
                R.layout.home_layout,
                container,
                false
        );
        listImages();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Images images  = Images.create();
        Call<SearchPhotos> nature = images.searchImage("nature");

        nature.enqueue(new Callback<SearchPhotos>() {
            @Override
            public void onResponse(Call<SearchPhotos> call, Response<SearchPhotos> response) {
                SearchPhotos body = response.body();
                if (body != null) {
                    List<Photo> photos = body.getPhotos();

                    ArrayList<Image> profilePhoto = new ArrayList<>();

                    for (Photo photo : photos) {

                        String[] s = photo.getPhotographer().split(" ");
                        String s1 = "";
                        String s2 = "";

                        if (s.length - 1 > 0) {
                            s1 = s[0];
                        }
                        if (s.length - 1 > 1) {
                            s2 = s[1];
                        }

                        profilePhoto.add(new Image(
                                R.drawable.user,
                                s1, s2,
                                photo.getSrc().getLargeUrl(), 0));
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

    private void listImages() {
        RecyclerView recyclerView = view.findViewById(R.id.recHomeView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                getContext(),
                1,
                RecyclerView.VERTICAL,
                false
        );

        recyclerView.setLayoutManager(gridLayoutManager);

        adapter.setOnLikeListener(this);
        adapter.setOpenPageClickListener(this);
        adapter.setClickCommentListener(this);

//        adapter.setImages(Image.getImages());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLike(AppCompatImageView like) {}

    @Override
    public void openPage(AppCompatImageView page) {
        ProfileFragment profileFragment = new ProfileFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHomePage, profileFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void addComment(AppCompatImageView comment) {
        CommentsFragment commentsFragment = new CommentsFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHomePage, commentsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        System.out.println("Mi ban");
    }
}
