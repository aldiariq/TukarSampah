package com.example.tukarsampah.Dashboard.Pengguna.ui.transaksipengguna;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
    private List<String> tempusernamekurir = new ArrayList<String>();
    private SharedPreferences sharedPreferences;
    private CardView Cardtransaksi;
    private TextView Idtransaksi, Jumlahtransaksi, Tgltransaksi, Namakurir;
    private Button Bataltransaksi, Teleponkurir;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.transaksi_pengguna_fragment, container, false);
        initView(root);
        sharedPreferences = getContext().getSharedPreferences("LOGIN", MODE_PRIVATE);

        getTransaksiPengguna(sharedPreferences.getString("ID_AKUN", ""));

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
        Idtransaksi = (TextView) root.findViewById(R.id.txtIdtransaksi_transaksi_pengguna);
        Jumlahtransaksi = (TextView) root.findViewById(R.id.txtJumlah_transaksi_pengguna);
        Tgltransaksi = (TextView) root.findViewById(R.id.txtTgl_transaksi_pengguna);
        Namakurir = (TextView) root.findViewById(R.id.txtNamakurir_transaksi_pengguna);
        Bataltransaksi = (Button) root.findViewById(R.id.btnBataltransaksitransaksipengguna);
        Teleponkurir = (Button) root.findViewById(R.id.btnTeleponkurirtransaksipengguna);
    }

    private void kosongkanInputan(){
        Banyaksampah.setText("");
        Kurir.setSelection(0);
        Idtransaksi.setText("Id Transaksi : ");
        Jumlahtransaksi.setText("Jumlah Transaksi : ");
        Tgltransaksi.setText("Tanggal Transaksi : ");
        Namakurir.setText("Nama Kurir : ");
    }

    private void setSpinner(){
        Operasipengguna operasipengguna = Service.Koneksi().create(Operasipengguna.class);
        Call<Responsetransaksigetkurirpengguna> responsetransaksigetkurirpengguna = operasipengguna.getDatakurir();
        responsetransaksigetkurirpengguna.enqueue(new Callback<Responsetransaksigetkurirpengguna>() {
            @Override
            public void onResponse(Call<Responsetransaksigetkurirpengguna> call, Response<Responsetransaksigetkurirpengguna> response) {
                List<Kelolakuriradmin> datakurir = response.body().getData();

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
                    kosongkanInputan();
                    getTransaksiPengguna(sharedPreferences.getString("ID_AKUN", ""));
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
                    Cardtransaksi.setVisibility(View.VISIBLE);
                    Banyaksampah.setVisibility(View.GONE);
                    Kurir.setVisibility(View.GONE);
                    Transaksi.setVisibility(View.GONE);

                    Idtransaksi.setText("Id Transaksi : " + datatransaksi.get(0).getId_transaksi());
                    Jumlahtransaksi.setText("Jumlah Transaksi : " + datatransaksi.get(0).getJumlah_transaksi() + "Kg");
                    Tgltransaksi.setText("Tanggal Transaksi : " + datatransaksi.get(0).getTgl_transaksi());
                    Namakurir.setText("Nama Kurir : " + datatransaksi.get(0).getUsername_kurir());

                    Bataltransaksi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Call<Responseoperasi> Bataltransaksi = operasipengguna.Bataltransaksipengguna(datatransaksi.get(0).getId_transaksi());
                            Bataltransaksi.enqueue(new Callback<Responseoperasi>() {
                                @Override
                                public void onResponse(Call<Responseoperasi> call, Response<Responseoperasi> response) {
                                    kosongkanInputan();
                                    tempidkurir.clear();
                                    tempusernamekurir.clear();
                                    getTransaksiPengguna(sharedPreferences.getString("ID_AKUN", ""));
                                }

                                @Override
                                public void onFailure(Call<Responseoperasi> call, Throwable t) {
                                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                    Teleponkurir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent teleponkurir = new Intent(Intent.ACTION_CALL);
                            teleponkurir.setData(Uri.parse("tel:" + datatransaksi.get(0).getNohp_kurir()));
                            startActivity(teleponkurir);
                        }
                    });
                }else {
                    Cardtransaksi.setVisibility(View.GONE);
                    Banyaksampah.setVisibility(View.VISIBLE);
                    Kurir.setVisibility(View.VISIBLE);
                    Transaksi.setVisibility(View.VISIBLE);
                    setSpinner();
                }
            }

            @Override
            public void onFailure(Call<Responsegettransaksipengguna> call, Throwable t) {
                Toast.makeText(getContext(), "GAGAL MENDAPATKAN DATA", Toast.LENGTH_SHORT).show();
            }
        });
    }
}