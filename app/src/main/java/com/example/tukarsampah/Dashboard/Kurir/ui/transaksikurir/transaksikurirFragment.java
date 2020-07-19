package com.example.tukarsampah.Dashboard.Kurir.ui.transaksikurir;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tukarsampah.Api.Service;
import com.example.tukarsampah.Dashboard.Api.Operasikurir;
import com.example.tukarsampah.Dashboard.Model.Responsegettransaksikurir;
import com.example.tukarsampah.Dashboard.Model.Responseoperasi;
import com.example.tukarsampah.Dashboard.Model.Transaksigettransaksikurir;
import com.example.tukarsampah.MasukActivity;
import com.example.tukarsampah.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class transaksikurirFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private TextView Idtransaksi, Tipesampah, Tgltransaksi, Namapengguna, Alamatpengguna;
    private EditText Jumlahtransaksi;
    private Button Terimatransaksi, Teleponpengguna;
    private ConnectivityManager Koneksi;
    private ProgressDialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.transaksi_kurir_fragment, container, false);
        initView(root);
        sharedPreferences = getContext().getSharedPreferences("LOGIN", MODE_PRIVATE);
        getTransaksiKurir(sharedPreferences.getString("ID_AKUN", ""));
        return root;
    }

    private void initView(View view){
        Idtransaksi = (TextView) view.findViewById(R.id.txtIdtransaksi_transaksi_kurir);
        Tipesampah = (TextView) view.findViewById(R.id.txtTIpesampah_transaksi_kurir);
        Jumlahtransaksi = (EditText) view.findViewById(R.id.txtJumlah_transaksi_kurir);
        Tgltransaksi = (TextView) view.findViewById(R.id.txtTgl_transaksi_kurir);
        Namapengguna = (TextView) view.findViewById(R.id.txtNamapengguna_transaksi_kurir);
        Alamatpengguna = (TextView) view.findViewById(R.id.txtAlamatpengguna_transaksi_kurir);
        Terimatransaksi = (Button) view.findViewById(R.id.btnTerimatransaksitransaksikurir);
        Teleponpengguna = (Button) view.findViewById(R.id.btnTeleponpenggunatransaksikurir);
        dialog = new ProgressDialog(view.getContext());
    }

    private void kosongkanInputan(){
        Idtransaksi.setText("Id Transaksi : ");
        Tipesampah.setText("Tipe Sampah : ");
        Jumlahtransaksi.setText("");
        Tgltransaksi.setText("Tanggal Transaksi : ");
        Namapengguna.setText("Nama Pengguna : ");
        Alamatpengguna.setText("Alamat Pengguna : ");
    }

    private void getTransaksiKurir(String idkurir){
        dialog.setMessage("Silahkan Tunggu..");
        dialog.setCancelable(false);
        dialog.show();
        Operasikurir operasikurir = Service.Koneksi().create(Operasikurir.class);
        Call<Responsegettransaksikurir> responsegettransaksikurir = operasikurir.getTransaksikurir(idkurir);
        responsegettransaksikurir.enqueue(new Callback<Responsegettransaksikurir>() {
            @Override
            public void onResponse(Call<Responsegettransaksikurir> call, Response<Responsegettransaksikurir> response) {
                List<Transaksigettransaksikurir> datatransaksi = response.body().getTransaksikurir();
                if (datatransaksi.size() == 0){
                    Terimatransaksi.setEnabled(false);
                    Teleponpengguna.setEnabled(false);
                    dialog.dismiss();
                }else {
                    Terimatransaksi.setEnabled(true);
                    Teleponpengguna.setEnabled(true);

                    Idtransaksi.setText("Id Transaksi : " + datatransaksi.get(0).getId_transaksi());
                    Tipesampah.setText("Tipe Sampah : " + datatransaksi.get(0).getTipe_sampah());
                    Jumlahtransaksi.setText(datatransaksi.get(0).getJumlah_transaksi());
                    Tgltransaksi.setText("Tanggal Transaksi : " + datatransaksi.get(0).getTgl_transaksi());
                    Namapengguna.setText("Nama Pengguna : " + datatransaksi.get(0).getUsername_pengguna());
                    Alamatpengguna.setText("Alamat Pengguna : " + datatransaksi.get(0).getAlamat_pengguna());
                    dialog.dismiss();

                    Terimatransaksi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.show();
                            if (cekKoneksi() && !Jumlahtransaksi.getText().toString().trim().equalsIgnoreCase("")){
                                String IDTRANSAKSI, IDPENGGUNA, JUMLAH_TRANSAKSI;
                                IDTRANSAKSI = datatransaksi.get(0).getId_transaksi();
                                IDPENGGUNA = datatransaksi.get(0).getId_pengguna();
                                JUMLAH_TRANSAKSI = Jumlahtransaksi.getText().toString().trim();
                                Operasikurir operasikurir2 = Service.Koneksi().create(Operasikurir.class);
                                Call<Responseoperasi> responseterimatransaksi = operasikurir2.Terimatransaksi(IDTRANSAKSI, IDPENGGUNA, JUMLAH_TRANSAKSI);
                                responseterimatransaksi.enqueue(new Callback<Responseoperasi>() {
                                    @Override
                                    public void onResponse(Call<Responseoperasi> call, Response<Responseoperasi> response) {
                                        Toast.makeText(getContext(), response.body().getKETERANGAN(), Toast.LENGTH_SHORT).show();
                                        kosongkanInputan();
                                        getTransaksiKurir(sharedPreferences.getString("ID_AKUN", ""));
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onFailure(Call<Responseoperasi> call, Throwable t) {
                                        Toast.makeText(getContext(), "Mohon Periksa Koneksi & Form Inputan", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });
                            }else {
                                Toast.makeText(getContext(), "Mohon Periksa Koneksi & Form Inputan", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    });

                    Teleponpengguna.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent teleponpengguna = new Intent(Intent.ACTION_CALL);
                            teleponpengguna.setData(Uri.parse("tel:" + datatransaksi.get(0).getNohp_pengguna()));
                            startActivity(teleponpengguna);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<Responsegettransaksikurir> call, Throwable t) {
                Toast.makeText(getContext(), "Mohon Periksa Koneksi", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
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