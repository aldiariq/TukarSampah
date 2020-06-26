package com.example.tukarsampah;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tukarsampah.Api.Operasi;
import com.example.tukarsampah.Api.Service;
import com.example.tukarsampah.Model.ResponseDaftar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarActivity extends AppCompatActivity {

    private EditText Username, Password, Password2, NoHp, Alamat;
    private Spinner Tipeakun;
    private Button Register;
    private TextView Login;
    private ConnectivityManager Koneksi;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        initView();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cekKoneksi()){
                    String strUsername, strPassword, strPassword2, strTipeakun, strNohp, strAlamat;
                    strUsername = (String) Username.getText().toString().trim();
                    strPassword = (String) Password.getText().toString().trim();
                    strPassword2 = (String) Password2.getText().toString().trim();
                    strNohp = (String) NoHp.getText().toString().trim();
                    strAlamat = (String) Alamat.getText().toString().trim();
                    strTipeakun = (String) Tipeakun.getSelectedItem().toString().trim();

                    if (strUsername.equalsIgnoreCase("") || strPassword.equalsIgnoreCase("") || strPassword2.equalsIgnoreCase("") || strNohp.equalsIgnoreCase("") || strAlamat.equalsIgnoreCase("") || strTipeakun.equalsIgnoreCase("")){
                        Toast.makeText(DaftarActivity.this, "Mohon Lengkapi Form", Toast.LENGTH_SHORT).show();
                    }else {
                        if (!strPassword.equals(strPassword2)){
                            Toast.makeText(DaftarActivity.this, "Password Tidak Sama", Toast.LENGTH_SHORT).show();
                        }else {
                            prosesRegister(strUsername, strPassword, strTipeakun, strNohp, strAlamat);
                        }
                    }
                }else {
                    Toast.makeText(DaftarActivity.this, "Mohon Periksa Koneksi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(DaftarActivity.this, MasukActivity.class);
                startActivity(login);
            }
        });
    }

    private void initView(){
        Username = (EditText) findViewById(R.id.etUsernameDaftar);
        Password = (EditText) findViewById(R.id.etPasswordDaftar);
        Password2 = (EditText) findViewById(R.id.etPassword2Daftar);
        NoHp = (EditText) findViewById(R.id.etNohpdaftar);
        Alamat = (EditText) findViewById(R.id.etAlamatdaftar);
        Tipeakun = (Spinner) findViewById(R.id.spTipeakunDaftar);
        Register = (Button) findViewById(R.id.btnRegisDaftar);
        Login = (TextView) findViewById(R.id.txtLoginDaftar);
        dialog = new ProgressDialog(DaftarActivity.this);
    }

    private boolean cekKoneksi(){
        Koneksi = (ConnectivityManager) getSystemService(DaftarActivity.CONNECTIVITY_SERVICE);
        {
            if (Koneksi.getActiveNetworkInfo() != null && Koneksi.getActiveNetworkInfo().isAvailable() && Koneksi.getActiveNetworkInfo().isConnected()) {
                return true;
            } else {
                return false;
            }
        }
    }

    private void prosesRegister(String Username, String Password, String Tipeakun, String Nohp, String Alamat){
        dialog.setMessage("Silahkan Tunggu..");
        dialog.show();
        Operasi operasi = Service.Koneksi().create(Operasi.class);
        Call<ResponseDaftar> daftar = operasi.Daftar(Username, Password, Tipeakun, Nohp, Alamat);
        daftar.enqueue(new Callback<ResponseDaftar>() {
            @Override
            public void onResponse(Call<ResponseDaftar> call, Response<ResponseDaftar> response) {
                if (response.isSuccessful()){
                    if (response.body().getSTATUS().equalsIgnoreCase("BERHASIL")){
                        dialog.dismiss();
                        Toast.makeText(DaftarActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DaftarActivity.this, MasukActivity.class));
                        finish();
                    }else {
                        Toast.makeText(DaftarActivity.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                        kosongkanInputan();
                    }

                } else{
                    Toast.makeText(DaftarActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDaftar> call, Throwable t) {
                Toast.makeText(DaftarActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void kosongkanInputan(){
        Username.setText("");
        Password.setText("");
        Password2.setText("");
        NoHp.setText("");
        Tipeakun.setSelection(0);
    }
}