package com.example.tukarsampah.Dashboard.Admin.ui.kelolarewardadmin;

import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tukarsampah.Api.Service;
import com.example.tukarsampah.Dashboard.Adapter.Adapterkelolarewardadmin;
import com.example.tukarsampah.Dashboard.Api.Operasiadmin;
import com.example.tukarsampah.Dashboard.Model.Kelolarewardadmin;
import com.example.tukarsampah.Dashboard.Model.Responsekelolarewardadmin;
import com.example.tukarsampah.Dashboard.Model.Responseoperasi;
import com.example.tukarsampah.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class kelolarewardadminFragment extends Fragment {
    private RecyclerView rvKelolareward;
    private RecyclerView.Adapter adKelolareward;
    private RecyclerView.LayoutManager lmKelolareward;
    private List<Kelolarewardadmin> listReward = new ArrayList<>();
    private FloatingActionButton fabReward;
    private ConnectivityManager Koneksi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.kelolareward_admin_fragment, container, false);
        ambilDatareward(root);
        fabReward = (FloatingActionButton) root.findViewById(R.id.fabkelolarewardadmin);
        fabReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View formsView = inflater.inflate(R.layout.tambahreward_admin_dialog, null, false);
                final EditText hadiah_reward = (EditText) formsView.findViewById(R.id.hadiah_reward);
                final EditText point_reward = (EditText) formsView.findViewById(R.id.point_reward);

                AlertDialog.Builder dialogPesan = new AlertDialog.Builder(root.getContext());
                dialogPesan.setView(formsView);
                dialogPesan.setCancelable(true);
                dialogPesan.setTitle("Tambah Reward");
                dialogPesan.setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String hadiah = hadiah_reward.getText().toString().trim().toUpperCase();
                        String point  = point_reward.getText().toString().trim();
                        if (hadiah.equalsIgnoreCase("") || point.equalsIgnoreCase("")){
                            Toast.makeText(getActivity(), "Mohon Lengkapi Form", Toast.LENGTH_SHORT).show();
                        }else {
                            tambahDatareward(hadiah, point, formsView);
                        }

                        hadiah_reward.setText("");
                        point_reward.setText("");
                    }
                });
                dialogPesan.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogPesan.show();
            }
        });
        return root;
    }

    private void ambilDatareward(View root){
        rvKelolareward = (RecyclerView) root.findViewById(R.id.rvkelolarewardadmin);
        lmKelolareward = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvKelolareward.setLayoutManager(lmKelolareward);
        rvKelolareward.setHasFixedSize(true);
        Operasiadmin operasiadmin = Service.Koneksi().create(Operasiadmin.class);
        Call<Responsekelolarewardadmin> tampilData = operasiadmin.getDatareward();

        tampilData.enqueue(new Callback<Responsekelolarewardadmin>() {
            @Override
            public void onResponse(Call<Responsekelolarewardadmin> call, Response<Responsekelolarewardadmin> response) {
                listReward = response.body().getData();
                adKelolareward = new Adapterkelolarewardadmin(getContext(), listReward);
                rvKelolareward.setAdapter(adKelolareward);
                adKelolareward.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Responsekelolarewardadmin> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Mendapatkan Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tambahDatareward(String hadiah, String point, View itemView){
        Operasiadmin operasiadmin = Service.Koneksi().create(Operasiadmin.class);
        Call<Responseoperasi> tampilData = operasiadmin.setReward(hadiah, point);

        tampilData.enqueue(new Callback<Responseoperasi>() {
            @Override
            public void onResponse(Call<Responseoperasi> call, Response<Responseoperasi> response) {
                if (response.body().getSTATUS().equalsIgnoreCase("BERHASIL")){
                    Toast.makeText(itemView.getContext(), response.body().getKETERANGAN(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(itemView.getContext(), response.body().getKETERANGAN(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Responseoperasi> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menambahkan Reward", Toast.LENGTH_SHORT).show();
            }
        });
    }
}