package com.example.projectn1.profile.viewpager2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.projectn1.profile.images.ProfileImagesFragment;
import com.example.projectn1.profile.videos.ProfileVideosFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(
            @NonNull FragmentManager fragmentManager,
            @NonNull Lifecycle lifecycle
    ) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                return new ProfileImagesFragment();
            case 1:
                return new ProfileVideosFragment();
        }
        return new ProfileImagesFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
