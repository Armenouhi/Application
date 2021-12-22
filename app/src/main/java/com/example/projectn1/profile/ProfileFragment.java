package com.example.projectn1.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.projectn1.R;
import com.example.projectn1.profile.viewpager2.ViewPager2Adapter;
import com.google.android.material.tabs.TabLayout;

public class ProfileFragment extends Fragment {
    ViewPager2Adapter viewPager2adapter;

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    SwipeRefreshLayout refreshLayout, sRLFullName;
    AppCompatTextView fullName;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.viewpager_profile_layout, container, false);

        refreshLayout = view.findViewById(R.id.swipeRefreshL);
        sRLFullName = view.findViewById(R.id.sRLFullName);
        fullName = view.findViewById(R.id.full_name);

        tabLayout = view.findViewById(R.id.profile_tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_image_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_videocam_24));

        viewPager2 = view.findViewById(R.id.profile_viewPager);
        viewPager2adapter = new ViewPager2Adapter(getParentFragmentManager(),getLifecycle());
        viewPager2.setAdapter(viewPager2adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
            }
        });

        sRLFullName.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
            }
        });

        getFullName();
        return view;
    }

    private void getFullName() {
        Bundle bundle = getArguments();
        System.out.println("bundle " + bundle);
        if (bundle != null) {
            fullName.setText(bundle.getString("full_name"));
        }
    }
}
