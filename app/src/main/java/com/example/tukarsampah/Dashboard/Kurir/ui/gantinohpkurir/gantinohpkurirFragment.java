package com.example.tukarsampah.Dashboard.Kurir.ui.gantinohpkurir;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tukarsampah.Api.Service;
import com.example.tukarsampah.Dashboard.Api.Operasikurir;
import com.example.tukarsampah.Dashboard.Model.Responseoperasi;
import com.example.tukarsampah.MasukActivity;
import com.example.tukarsampah.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class gantinohpkurirFragment extends Fragment {
    private EditText Nohp;
    private Button Btnsimpan;
    private SharedPreferences sharedPreferences;
    private ConnectivityManager Koneksi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.gantinohp_fragment, container, false);
        Btnsimpan = (Button) root.findViewById(R.id.btnsimpanubahubahnohp);
        Btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cekKoneksi()){
                    sharedPreferences = getActivity().getSharedPreferences("LOGIN", MODE_PRIVATE);
                    Nohp = (EditText) root.findViewById(R.id.etnohpubahnohp);
                    String nohp;
                    nohp = Nohp.getText().toString().trim();

                    if (nohp.equalsIgnoreCase("")){
                        Toast.makeText(getContext(), "Mohon Lengkapi Form", Toast.LENGTH_SHORT).show();
                    }else {
                        ubahNohp(nohp, root);
                    }

                    Nohp.setText("");
                }else {
                    Toast.makeText(getContext(), "Mohon Periksa Koneksi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    public void ubahNohp(String Nohp, View itemView){
        sharedPreferences = getActivity().getSharedPreferences("LOGIN", MODE_PRIVATE);
        Operasikurir operasikurir = Service.Koneksi().create(Operasikurir.class);
        Call<Responseoperasi> ubahNohp = operasikurir.ubahNohp(sharedPreferences.getString("ID_AKUN", ""), Nohp);

        ubahNohp.enqueue(new Callback<Responseoperasi>() {
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
                Toast.makeText(getActivity(), "Mohon Periksa Koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean cekKoneksi(){
        Koneksi = (ConnectivityManager) getActivity().getSystemService(MasukActivity.CONNECTIVITY_SERVICE);
        {
            if (Koneksi.getActiveNetworkInfo() != null && Koneksi.getActiveNetworkInfo().isAvailable() && Koneksi.getActiveNetworkInfo().isConnected()) {
                return true;
            } else {
                return false;
            }
        }
    }
}