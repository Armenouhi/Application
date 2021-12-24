package com.example.projectn1.profile;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
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

    SwipeRefreshLayout refreshLayout;
    AppCompatTextView fullName, emptyDataView, posts, followers;
    CardView cardView;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.viewpager_profile_layout, container, false);

        refreshLayout = view.findViewById(R.id.swipeRefreshL);
        fullName = view.findViewById(R.id.full_name);
        emptyDataView = view.findViewById(R.id.empty_view);
        posts = view.findViewById(R.id.posts);
        followers = view.findViewById(R.id.followers);
        cardView = view.findViewById(R.id.roundProfileImg);

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

        getFullName();
        checkInternet();
        return view;
    }

    private void getFullName() {
        Bundle bundle = getArguments();
        System.out.println("bundle " + bundle);
        if (bundle != null) {
            fullName.setText(bundle.getString("full_name"));
        }
    }

    private void checkInternet() {
        if (getActivity() != null) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager
                            .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                            .getState() == NetworkInfo.State.CONNECTED) {

                emptyDataView.setVisibility(View.GONE);

                viewPager2.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                posts.setVisibility(View.VISIBLE);
                followers.setVisibility(View.VISIBLE);
                fullName.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.VISIBLE);

            } else {
                emptyDataView.setVisibility(View.VISIBLE);
                
                viewPager2.setVisibility(View.GONE);
                tabLayout.setVisibility(View.GONE);
                posts.setVisibility(View.GONE);
                followers.setVisibility(View.GONE);
                fullName.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);

            }
        }

    }
}
