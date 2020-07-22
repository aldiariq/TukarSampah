package com.example.tukarsampah.Dashboard.Admin.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tukarsampah.R;
import com.google.android.material.navigation.NavigationView;


public class HomeFragment extends Fragment {

    private NavigationView mNavigationView;
    private View hView;
    private SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dashboard_admin_fragment, container, false);
        return root;
    }
}