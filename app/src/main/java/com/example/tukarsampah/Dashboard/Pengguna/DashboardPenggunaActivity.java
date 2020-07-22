package com.example.tukarsampah.Dashboard.Pengguna;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tukarsampah.Api.Service;
import com.example.tukarsampah.Dashboard.Api.Operasipengguna;
import com.example.tukarsampah.Dashboard.Model.Responseprofilpengguna;
import com.example.tukarsampah.MasukActivity;
import com.example.tukarsampah.R;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardPenggunaActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView txtUsername, txtTipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_pengguna);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_transaksi_pengguna, R.id.nav_tukarpoint_pengguna, R.id.nav_gantipassword_pengguna, R.id.nav_logout_pengguna)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.getMenu().findItem(R.id.nav_logout_pengguna).setOnMenuItemClickListener(menuItem -> {
            fungsiLogout();
            return true;
        });
        getProfilpengguna();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard_admin, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void fungsiLogout(){
        editor = getSharedPreferences("LOGIN", MODE_PRIVATE).edit();
        editor.putString("STATUS", "");
        editor.putString("KETERANGAN", "");
        editor.putString("ID_AKUN", "");
        editor.putString("USERNAME", "");
        editor.putString("TIPEAKUN", "");
        editor.apply();
        Toast.makeText(DashboardPenggunaActivity.this, "Berhasil Log Out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(DashboardPenggunaActivity.this, MasukActivity.class));
        finish();
    }

    private void getProfilpengguna(){
        TextView Username, Nohp, Tipeakun;

        Username = findViewById(R.id.txtusername_nav_pengguna);
        Nohp = findViewById(R.id.txtnohp_nav_pengguna);
        Tipeakun = findViewById(R.id.txttipe_nav_pengguna);

        String id_pengguna = sharedPreferences.getString("ID_AKUN", "");
        Operasipengguna operasipengguna = Service.Koneksi().create(Operasipengguna.class);
        Call<Responseprofilpengguna> profilpengguna = operasipengguna.getProfilpengguna(id_pengguna);
        profilpengguna.enqueue(new Callback<Responseprofilpengguna>() {
            @Override
            public void onResponse(Call<Responseprofilpengguna> call, Response<Responseprofilpengguna> response) {
                Username.setText("Username \t: " + response.body().getUsername_pengguna());
                Nohp.setText("No HP \t: " + response.body().getNohp_pengguna());
                Tipeakun.setText("Tipe Akun \t: " + response.body().getTipe_akun());
            }

            @Override
            public void onFailure(Call<Responseprofilpengguna> call, Throwable t) {
                Toast.makeText(DashboardPenggunaActivity.this, "Mohon Periksa Koneksi", Toast.LENGTH_SHORT).show();
            }
        });


    }
}