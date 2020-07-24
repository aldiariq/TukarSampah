package com.example.tukarsampah.Dashboard.Pengguna.ui.home;

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
import com.example.tukarsampah.Dashboard.Api.Operasipengguna;
import com.example.tukarsampah.Dashboard.Model.Responseprofilpengguna;
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
        View root = inflater.inflate(R.layout.dashboard_pengguna_fragment, container, false);
        getProfilpengguna(root);
        return root;
    }

    private void getProfilpengguna(View view){
        sharedPreferences = getActivity().getSharedPreferences("LOGIN", MODE_PRIVATE);

        TextView Namadashboard, Alamatdashboard;

        Namadashboard = view.findViewById(R.id.txtNamadashboardpengguna);
        Alamatdashboard = view.findViewById(R.id.txtAlamatdashboardpengguna);

        String id_pengguna = sharedPreferences.getString("ID_AKUN", "");
        Operasipengguna operasipengguna = Service.Koneksi().create(Operasipengguna.class);
        Call<Responseprofilpengguna> profilpengguna = operasipengguna.getProfilpengguna(id_pengguna);
        profilpengguna.enqueue(new Callback<Responseprofilpengguna>() {
            @Override
            public void onResponse(Call<Responseprofilpengguna> call, Response<Responseprofilpengguna> response) {
                Namadashboard.setText("Nama \t: " + response.body().getUSERNAMEPENGGUNA());
                Alamatdashboard.setText("Alamat \t: " + response.body().getALAMATPENGGUNA());
            }

            @Override
            public void onFailure(Call<Responseprofilpengguna> call, Throwable t) {
                Toast.makeText(getContext(), "Mohon Periksa Koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}