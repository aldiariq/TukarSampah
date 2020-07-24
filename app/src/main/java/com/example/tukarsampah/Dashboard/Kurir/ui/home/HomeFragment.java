package com.example.tukarsampah.Dashboard.Kurir.ui.home;

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
import com.example.tukarsampah.Dashboard.Api.Operasikurir;
import com.example.tukarsampah.Dashboard.Model.Responseprofilkurir;
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
        View root = inflater.inflate(R.layout.dashboard_kurir_fragment, container, false);
        getProfilkurir(root);
        return root;
    }

    private void getProfilkurir(View view){
        sharedPreferences = getActivity().getSharedPreferences("LOGIN", MODE_PRIVATE);

        TextView Namadashboard, Alamatdashboard;

        Namadashboard = view.findViewById(R.id.txtNamadashboardkurir);
        Alamatdashboard = view.findViewById(R.id.txtAlamatdashboardkurir);

        String id_kurir = sharedPreferences.getString("ID_AKUN", "");
        Operasikurir operasikurir = Service.Koneksi().create(Operasikurir.class);
        Call<Responseprofilkurir> profilkurir = operasikurir.getProfilkurir(id_kurir);
        profilkurir.enqueue(new Callback<Responseprofilkurir>() {
            @Override
            public void onResponse(Call<Responseprofilkurir> call, Response<Responseprofilkurir> response) {
                Namadashboard.setText("Nama \t: " + response.body().getUSERNAMEKURIR());
                Alamatdashboard.setText("Alamat \t: " + response.body().getALAMATKURIR());
            }

            @Override
            public void onFailure(Call<Responseprofilkurir> call, Throwable t) {
                Toast.makeText(getContext(), "Mohon Periksa Koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}