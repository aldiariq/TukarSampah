package com.example.tukarsampah.Dashboard.Admin.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.tukarsampah.Api.Service;
import com.example.tukarsampah.Dashboard.Api.Operasiadmin;
import com.example.tukarsampah.Dashboard.Model.Responseprofiladmin;
import com.example.tukarsampah.R;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment {

    private AppBarConfiguration mAppBarConfiguration;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private NavigationView mNavigationView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dashboard_admin_fragment, container, false);
        getProfiladmin(root);
        return root;
    }

    private void getProfiladmin(View view){
        sharedPreferences = getActivity().getSharedPreferences("LOGIN", MODE_PRIVATE);

        TextView Namadashboard;

        Namadashboard = view.findViewById(R.id.txtNamadashboardadmin);

        String id_admin = sharedPreferences.getString("ID_AKUN", "");
        Operasiadmin operasiadmin = Service.Koneksi().create(Operasiadmin.class);
        Call<Responseprofiladmin> profiladmin = operasiadmin.getProfiladmin(id_admin);
        profiladmin.enqueue(new Callback<Responseprofiladmin>() {
            @Override
            public void onResponse(Call<Responseprofiladmin> call, Response<Responseprofiladmin> response) {
                Namadashboard.setText("Nama \t: " + response.body().getUSERNAMEADMIN());
            }

            @Override
            public void onFailure(Call<Responseprofiladmin> call, Throwable t) {
                Toast.makeText(getContext(), "Mohon Periksa Koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}