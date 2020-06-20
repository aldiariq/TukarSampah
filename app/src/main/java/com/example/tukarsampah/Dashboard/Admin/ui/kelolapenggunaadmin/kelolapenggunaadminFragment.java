package com.example.tukarsampah.Dashboard.Admin.ui.kelolapenggunaadmin;

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
import com.example.tukarsampah.Dashboard.Admin.Adapter.Adapterkelolapenggunaadmin;
import com.example.tukarsampah.Dashboard.Admin.Api.Operasiadmin;
import com.example.tukarsampah.Dashboard.Admin.Model.Kelolapenggunaadmin;
import com.example.tukarsampah.Dashboard.Admin.Model.Responsekelolapenggunaadmin;
import com.example.tukarsampah.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class kelolapenggunaadminFragment extends Fragment {
    private RecyclerView rvKelolapengguna;
    private RecyclerView.Adapter adKelolapengguna;
    private RecyclerView.LayoutManager lmKelolapengguna;
    private List<Kelolapenggunaadmin> listPengguna = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.kelolapengguna_admin_fragment, container, false);

        ambilDatapengguna(root);

        return root;
    }

    private void ambilDatapengguna(View root){
        rvKelolapengguna = (RecyclerView) root.findViewById(R.id.rvkelolapenggunaadmin);
        lmKelolapengguna = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvKelolapengguna.setLayoutManager(lmKelolapengguna);
        rvKelolapengguna.setHasFixedSize(true);
        Operasiadmin operasiadmin = Service.Koneksi().create(Operasiadmin.class);
        Call<Responsekelolapenggunaadmin> tampilData = operasiadmin.getDatapengguna();

        tampilData.enqueue(new Callback<Responsekelolapenggunaadmin>() {
            @Override
            public void onResponse(Call<Responsekelolapenggunaadmin> call, Response<Responsekelolapenggunaadmin> response) {
                listPengguna = response.body().getData();
                adKelolapengguna = new Adapterkelolapenggunaadmin(getContext(), listPengguna);
                rvKelolapengguna.setAdapter(adKelolapengguna);
                adKelolapengguna.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Responsekelolapenggunaadmin> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Mendapatkan Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}