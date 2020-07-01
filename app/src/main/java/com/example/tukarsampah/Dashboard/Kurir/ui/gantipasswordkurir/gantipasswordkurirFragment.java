package com.example.tukarsampah.Dashboard.Kurir.ui.gantipasswordkurir;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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


public class gantipasswordkurirFragment extends Fragment {

    private TextView Passlama, Passbaru, Passbaru2;
    private Button Btnsimpan;
    private SharedPreferences sharedPreferences;
    private ConnectivityManager Koneksi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.gantipassword_fragment, container, false);
        Btnsimpan = (Button) root.findViewById(R.id.btnsimpanubahpassword);
        Btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cekKoneksi()){
                    sharedPreferences = getActivity().getSharedPreferences("LOGIN", MODE_PRIVATE);
                    Passlama = (TextView) root.findViewById(R.id.etpasswordlamagantipassword);
                    Passbaru = (TextView) root.findViewById(R.id.etpasswordbarugantipassword);
                    Passbaru2 = (TextView) root.findViewById(R.id.etpasswordbaru2gantipassword);
                    String username, passlama, passbaru, passbaru2;
                    username = sharedPreferences.getString("USERNAME", "");
                    passlama = Passlama.getText().toString().trim();
                    passbaru = Passbaru.getText().toString().trim();
                    passbaru2 = Passbaru2.getText().toString().trim();

                    if (passlama.equalsIgnoreCase("") || passbaru.equalsIgnoreCase("") || passbaru2.equalsIgnoreCase("")){
                        Toast.makeText(getContext(), "Mohon Lengkapi Form", Toast.LENGTH_SHORT).show();
                    }else {
                        ubahPassword(username, passlama, passbaru, root);
                    }

                    Passlama.setText("");
                    Passbaru.setText("");
                    Passbaru2.setText("");
                }else {
                    Toast.makeText(getContext(), "Mohon Periksa Koneksi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    public void ubahPassword(String Username, String Passlama, String Passbaru, View itemView){
        Operasikurir operasikurir = Service.Koneksi().create(Operasikurir.class);
        Call<Responseoperasi> ubahpassword = operasikurir.ubahPassword(Username, Passlama, Passbaru, "KURIR");

        ubahpassword.enqueue(new Callback<Responseoperasi>() {
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