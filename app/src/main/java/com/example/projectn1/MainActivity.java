package com.example.projectn1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.TypeConverters;

import android.os.Bundle;

import com.example.projectn1.home.HomePageFragment;
import com.example.projectn1.room.AppDatabase;
import com.example.projectn1.room.AuthorDao;
import com.example.projectn1.room.Authors;
import com.example.projectn1.room.AuthorsConverter;

import java.util.List;


@TypeConverters({AuthorsConverter.class})
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homePage();

        AppDatabase db = AppDatabase.getInstance(this);
        AuthorDao authorsDao = db.getAuthorsDao();
        List<Authors> authorsList = authorsDao.getAuthors();
    }

    private void homePage() {
        HomePageFragment fH = new HomePageFragment();

        FragmentTransaction fT = getSupportFragmentManager().beginTransaction();
        fT.replace(R.id.fragmentHomePage, fH);
        fT.addToBackStack(null);
        fT.commit();
    }
}