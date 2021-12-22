package com.example.projectn1.home;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.projectn1.dto.Images;
import com.example.projectn1.dto.Photo;
import com.example.projectn1.dto.SearchPhotos;
import com.example.projectn1.home.dialogBottomFilters.AddCommentDBFragment;
import com.example.projectn1.profile.ProfileFragment;
import com.example.projectn1.room.AppDatabase;
import com.example.projectn1.room.AuthorDao;
import com.example.projectn1.room.AuthorsWithImage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageFragment extends Fragment
        implements OnLikeListener, OnOpenPageListener, OnClickCommentListener, OnClickShare {


    HomePageAdapter adapter = new HomePageAdapter();
    View view;

    boolean isInternetConnected = false;

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

        Images images = Images.create();
        Call<SearchPhotos> nature = images.searchImage("nature");

        if (isInternetConnected) {
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
                                    R.drawable.photographer,
                                    s1, s2,
                                    photo.getSrc().getLargeUrl(), 0));
                        }
                        adapter.setImages(profilePhoto);
                        saveToDB(profilePhoto);
                    }
                }


                @Override
                public void onFailure(Call<SearchPhotos> call, Throwable t) {
                    System.out.println(t.getLocalizedMessage());
                }
            });
        } else {
            AppDatabase db = AppDatabase.getInstance(getContext());
            AuthorDao authorsDao = db.getAuthorsDao();

            List<AuthorsWithImage> authorsWithImages = authorsDao.getAuthors();

            ArrayList<Image> dbPhoto = new ArrayList<>();

            /*for (AuthorsWithImage photo : authorsWithImages) {

                dbPhoto.add(new AuthorsWithImage(
                    photo.getId(),
                        photo.getFirstname(),
                        photo.getUrl()
                ));
            }

            isInternetConnected = true;*/


            for (AuthorsWithImage photo : authorsWithImages ) {
                dbPhoto.add(new Image(
                        photo.getUrl(),
                        photo.getFirstname()
                ));
            }
            adapter.setImages(dbPhoto);

            isInternetConnected = true;
        }





    }


    private void saveToDB(ArrayList<Image> profilePhoto) {
        AppDatabase db = AppDatabase.getInstance(getContext());
        AuthorDao authorsDao = db.getAuthorsDao();

        List<AuthorsWithImage> entityAuthors = new ArrayList<>();
        for (Image dto: profilePhoto) {
            entityAuthors.add(new AuthorsWithImage(
                0,
                    dto.getImageUrl(),
                    dto.getFullName()
            ));
        }
        
        authorsDao.insertAll(entityAuthors);
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
        adapter.setOnClickShare(this);

//        adapter.setImages(Image.getImages());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLike(AppCompatImageView like) {
    }

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

        AddCommentDBFragment addFiltersBottomDialogFragment =
                AddCommentDBFragment.newInstance();
        addFiltersBottomDialogFragment.show(getParentFragmentManager(),
                "add_photo_dialog_fragment");
    }

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    public void share(AppCompatImageView buttonShare) {
        System.out.println("Comment");

        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Send a simple tex"
        );
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}