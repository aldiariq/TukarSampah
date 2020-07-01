package com.example.tukarsampah.Dashboard.Admin.ui.kelolakuriradmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tukarsampah.Api.Service;
import com.example.tukarsampah.Dashboard.Adapter.Adapterkelolakuriradmin;
import com.example.tukarsampah.Dashboard.Api.Operasiadmin;
import com.example.tukarsampah.Dashboard.Model.Kelolakuriradmin;
import com.example.tukarsampah.Dashboard.Model.Responsekelolakuriradmin;
import com.example.tukarsampah.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kelolakuriradminFragment extends Fragment {
    private RecyclerView rvKelolakurir;
    private RecyclerView.Adapter adKelolakurir;
    private RecyclerView.LayoutManager lmKelolakurir;
    private List<Kelolakuriradmin> listKurir = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.kelolakurir_admin_fragment, container, false);
        ambilDatakurir(root);
        return root;
    }

    private void ambilDatakurir(View root){
        rvKelolakurir = (RecyclerView) root.findViewById(R.id.rvkelolakuriradmin);
        lmKelolakurir = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvKelolakurir.setLayoutManager(lmKelolakurir);
        rvKelolakurir.setHasFixedSize(true);
        Operasiadmin operasiadmin = Service.Koneksi().create(Operasiadmin.class);
        Call<Responsekelolakuriradmin> tampilData = operasiadmin.getDatakurir();

        tampilData.enqueue(new Callback<Responsekelolakuriradmin>() {
            @Override
            public void onResponse(Call<Responsekelolakuriradmin> call, Response<Responsekelolakuriradmin> response) {
                try {
                    listKurir = response.body().getData();
                    adKelolakurir = new Adapterkelolakuriradmin(getContext(), listKurir);
                    rvKelolakurir.setAdapter(adKelolakurir);
                    adKelolakurir.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Data Kurir Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Responsekelolakuriradmin> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Mendapatkan Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}