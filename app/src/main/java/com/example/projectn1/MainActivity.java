package com.example.projectn1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.projectn1.home.HomePageFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homePage();
    }

    private void homePage() {
        HomePageFragment fH = new HomePageFragment();

        FragmentTransaction fT = getSupportFragmentManager().beginTransaction();
        fT.replace(R.id.fragmentHomePage, fH);
        fT.addToBackStack(null);
        fT.commit();
    }
}