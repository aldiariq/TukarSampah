package com.example.tukarsampah;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tukarsampah.Api.Operasi;
import com.example.tukarsampah.Api.Service;
import com.example.tukarsampah.Dashboard.Kurir.DashboardKurirActivity;
import com.example.tukarsampah.Dashboard.Admin.DashboardAdminActivity;
import com.example.tukarsampah.Dashboard.Pengguna.DashboardPenggunaActivity;
import com.example.tukarsampah.Model.ResponseMasuk;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasukActivity extends AppCompatActivity {

    private EditText Username, Password;
    private Spinner Tipeakun;
    private Button Login;
    private TextView Register;

    private ConnectivityManager Koneksi;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);
        initView();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername, strPassword, strTipeakun;
                strUsername = (String) Username.getText().toString().trim();
                strPassword = (String) Password.getText().toString().trim();
                strTipeakun = (String) Tipeakun.getSelectedItem().toString().trim();

                if (strUsername.equalsIgnoreCase("") || strPassword.equalsIgnoreCase("")){
                    Toast.makeText(MasukActivity.this, "Mohon Lengkapi Form", Toast.LENGTH_SHORT).show();
                }else {
                    if (cekKoneksi()){
                        prosesLogin(strUsername, strPassword, strTipeakun);
                    }else {
                        Toast.makeText(MasukActivity.this, "Mohon Periksa Koneksi", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(MasukActivity.this, DaftarActivity.class);
                startActivity(register);
                finish();
            }
        });

        if (cekKoneksi()){
            if (cekSession()){
                if (sharedPreferences.getString("TIPEAKUN", "").equalsIgnoreCase("PENGGUNA")){
                    Intent pengguna = new Intent(MasukActivity.this, DashboardPenggunaActivity.class);
                    startActivity(pengguna);
                }else if (sharedPreferences.getString("TIPEAKUN", "").equalsIgnoreCase("KURIR")){
                    Intent kurir = new Intent(MasukActivity.this, DashboardKurirActivity.class);
                    startActivity(kurir);
                }else if (sharedPreferences.getString("TIPEAKUN", "").equalsIgnoreCase("ADMIN")){
                    Intent admin = new Intent(MasukActivity.this, DashboardAdminActivity.class);
                    startActivity(admin);
                }
                finish();
            }
        }
    }

    private void initView(){
        Username = (EditText) findViewById(R.id.etUsernameMasuk);
        Password = (EditText) findViewById(R.id.etPasswordMasuk);
        Tipeakun = (Spinner) findViewById(R.id.spTipeakunMasuk);
        Login = (Button) findViewById(R.id.btnLoginMasuk);
        Register = (TextView) findViewById(R.id.txtRegisterMasuk);
        sharedPreferences = getSharedPreferences("LOGIN", MODE_PRIVATE);
        sharedPreferencesEditor = getSharedPreferences("LOGIN", MODE_PRIVATE).edit();
        dialog = new ProgressDialog(MasukActivity.this);
    }

    private boolean cekSession(){
        if(sharedPreferences.getString("STATUS", "GAGAL").equalsIgnoreCase("BERHASIL")){
            return true;
        }else {
            return false;
        }
    }

    private boolean cekKoneksi(){
        Koneksi = (ConnectivityManager) getSystemService(MasukActivity.CONNECTIVITY_SERVICE);
        {
            if (Koneksi.getActiveNetworkInfo() != null && Koneksi.getActiveNetworkInfo().isAvailable() && Koneksi.getActiveNetworkInfo().isConnected()) {
                return true;
            } else {
                return false;
            }
        }
    }

    private void prosesLogin(String Username, String Password, String Tipeakun){
        dialog.setMessage("Silahkan Tunggu..");
        dialog.show();
        Operasi operasi = Service.Koneksi().create(Operasi.class);
        Call<ResponseMasuk> login = operasi.Masuk(Username, Password, Tipeakun);
        login.enqueue(new Callback<ResponseMasuk>() {
            @Override
            public void onResponse(Call<ResponseMasuk> call, Response<ResponseMasuk> response) {
                if (response.body().getSTATUS().equalsIgnoreCase("BERHASIL")){
                    Toast.makeText(MasukActivity.this, "Log In Berhasil..", Toast.LENGTH_SHORT).show();
                    sharedPreferencesEditor.putString("STATUS", response.body().getSTATUS());
                    sharedPreferencesEditor.putString("KETERANGAN", response.body().getKETERANGAN());
                    sharedPreferencesEditor.putString("ID_AKUN", response.body().getIDPENGGUNA());
                    sharedPreferencesEditor.putString("USERNAME", response.body().getUSERNAME());
                    sharedPreferencesEditor.putString("TIPEAKUN", response.body().getTIPEAKUN());
                    sharedPreferencesEditor.apply();
                    dialog.dismiss();
                    if (response.body().getTIPEAKUN().equalsIgnoreCase("PENGGUNA")){
                        Intent pengguna = new Intent(MasukActivity.this, DashboardPenggunaActivity.class);
                        startActivity(pengguna);
                    }else if (response.body().getTIPEAKUN().equalsIgnoreCase("KURIR")){
                        Intent kurir = new Intent(MasukActivity.this, DashboardKurirActivity.class);
                        startActivity(kurir);
                    }else if (response.body().getTIPEAKUN().equalsIgnoreCase("ADMIN")){
                        Intent admin = new Intent(MasukActivity.this, DashboardAdminActivity.class);
                        startActivity(admin);
                    }
                    finish();
                }else {
                    Toast.makeText(MasukActivity.this, "Mohon Periksa Username atau Password", Toast.LENGTH_SHORT).show();
                    kosongkanInput();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseMasuk> call, Throwable t) {
                Toast.makeText(MasukActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void kosongkanInput(){
        Username.setText("");
        Password.setText("");
        Tipeakun.setSelection(0);
    }
}