package com.example.tukarsampah.Dashboard.Pengguna.ui.transaksipengguna;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.tukarsampah.Api.Service;
import com.example.tukarsampah.Dashboard.Api.Operasipengguna;
import com.example.tukarsampah.Dashboard.Model.Kelolakuriradmin;
import com.example.tukarsampah.Dashboard.Model.Responsegettransaksipengguna;
import com.example.tukarsampah.Dashboard.Model.Responseoperasi;
import com.example.tukarsampah.Dashboard.Model.Responsetransaksigetkurirpengguna;
import com.example.tukarsampah.Dashboard.Model.Transaksigettransaksipengguna;
import com.example.tukarsampah.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class transaksipenggunaFragment extends Fragment {
    private EditText Banyaksampah;
    private Spinner Kurir;
    private Button Transaksi;
    private List<String> tempidkurir = new ArrayList<String>();
    private SharedPreferences sharedPreferences;
    private CardView Cardtransaksi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.transaksi_pengguna_fragment, container, false);
        initView(root);
        sharedPreferences = getContext().getSharedPreferences("LOGIN", MODE_PRIVATE);
        getTransaksiPengguna(sharedPreferences.getString("ID_AKUN", ""));
        setSpinner();

        Transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idpengguna, idkurir, banyaksampah;
                idpengguna = sharedPreferences.getString("ID_AKUN", "");
                banyaksampah = Banyaksampah.getText().toString().trim();
                idkurir = tempidkurir.get(Kurir.getSelectedItemPosition());
                fungsiTransaksi(idpengguna, idkurir, banyaksampah, root);
            }
        });
        return root;
    }

    private void initView(View root){
        Banyaksampah = (EditText) root.findViewById(R.id.etBanyaksampahtransaksipengguna);
        Kurir = (Spinner) root.findViewById(R.id.spKurirtransaksipengguna);
        Transaksi = (Button) root.findViewById(R.id.btnTransaksitransaksipengguna);
        Cardtransaksi = (CardView) root.findViewById(R.id.card_datatransaksi_pengguna);
    }

    private void setSpinner(){
        Operasipengguna operasipengguna = Service.Koneksi().create(Operasipengguna.class);
        Call<Responsetransaksigetkurirpengguna> responsetransaksigetkurirpengguna = operasipengguna.getDatakurir();
        responsetransaksigetkurirpengguna.enqueue(new Callback<Responsetransaksigetkurirpengguna>() {
            @Override
            public void onResponse(Call<Responsetransaksigetkurirpengguna> call, Response<Responsetransaksigetkurirpengguna> response) {
                List<Kelolakuriradmin> datakurir = response.body().getData();
                List<String> tempusernamekurir = new ArrayList<String>();

                for (int i = 0; i < datakurir.size(); i++){
                    String idkurir = datakurir.get(i).getId_kurir();
                    tempidkurir.add(idkurir);
                    String usernamekurir = datakurir.get(i).getUsername_kurir();
                    tempusernamekurir.add(usernamekurir);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                        (getActivity(), android.R.layout.simple_spinner_item, tempusernamekurir );

                dataAdapter.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);

                Kurir.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<Responsetransaksigetkurirpengguna> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fungsiTransaksi(String idpengguna, String idkurir, String banyaksampah, View root){
        Operasipengguna operasipengguna = Service.Koneksi().create(Operasipengguna.class);
        Call<Responseoperasi> responsehapusakunpengguna = operasipengguna.Transaksi(idpengguna, idkurir, banyaksampah);
        responsehapusakunpengguna.enqueue(new Callback<Responseoperasi>() {
            @Override
            public void onResponse(Call<Responseoperasi> call, Response<Responseoperasi> response) {
                if (response.body().getSTATUS().equalsIgnoreCase("BERHASIL")){
                    Toast.makeText(root.getContext(), response.body().getKETERANGAN(), Toast.LENGTH_SHORT).show();
                    Banyaksampah.setText("");
                    Kurir.setSelection(0);
                }else {
                    Toast.makeText(root.getContext(), response.body().getKETERANGAN(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Responseoperasi> call, Throwable t) {
                Toast.makeText(root.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTransaksiPengguna(String idpengguna){
        Operasipengguna operasipengguna = Service.Koneksi().create(Operasipengguna.class);
        Call<Responsegettransaksipengguna> responsegettransaksipengguna = operasipengguna.getTransaksipengguna(idpengguna);
        responsegettransaksipengguna.enqueue(new Callback<Responsegettransaksipengguna>() {
            @Override
            public void onResponse(Call<Responsegettransaksipengguna> call, Response<Responsegettransaksipengguna> response) {
                List<Transaksigettransaksipengguna> datatransaksi = response.body().getTransaksipengguna();
                if (datatransaksi.size() != 0){
                    Banyaksampah.setVisibility(View.GONE);
                    Kurir.setVisibility(View.GONE);
                    Transaksi.setVisibility(View.GONE);
                }else {
                    Cardtransaksi.setVisibility(View.GONE);
                }

                for (int i = 0; i < datatransaksi.size(); i++){
                    Toast.makeText(getContext(), datatransaksi.get(i).getId_pengguna(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Responsegettransaksipengguna> call, Throwable t) {

            }
        });
    }
}