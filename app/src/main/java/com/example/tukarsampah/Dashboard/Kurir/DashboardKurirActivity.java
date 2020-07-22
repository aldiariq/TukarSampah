package com.example.tukarsampah.Dashboard.Kurir;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
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
import com.example.tukarsampah.Dashboard.Api.Operasikurir;
import com.example.tukarsampah.Dashboard.Model.Responseprofilkurir;
import com.example.tukarsampah.MasukActivity;
import com.example.tukarsampah.R;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardKurirActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView txtUsername, txtTipe;
    private NavigationView mNavigationView;
    private View hView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_kurir);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_transaksi_kurir, R.id.nav_gantipassword_kurir, R.id.nav_logout_kurir)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.getMenu().findItem(R.id.nav_logout_kurir).setOnMenuItemClickListener(menuItem -> {
            fungsiLogout();
            return true;
        });
        getProfilkurir();
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
        Toast.makeText(DashboardKurirActivity.this, "Berhasil Log Out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(DashboardKurirActivity.this, MasukActivity.class));
        finish();
    }

    private void getProfilkurir(){
        sharedPreferences = getSharedPreferences("LOGIN", MODE_PRIVATE);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = mNavigationView.getHeaderView(0);
        TextView Username, Nohp, Tipeakun;

        Username = header.findViewById(R.id.txtusername_nav_kurir);
        Nohp = header.findViewById(R.id.txtnohp_nav_kurir);
        Tipeakun = header.findViewById(R.id.txttipe_nav_kurir);

        String id_kurir = sharedPreferences.getString("ID_AKUN", "");
        Operasikurir operasikurir = Service.Koneksi().create(Operasikurir.class);
        Call<Responseprofilkurir> profilkurir = operasikurir.getProfilkurir(id_kurir);
        profilkurir.enqueue(new Callback<Responseprofilkurir>() {
            @Override
            public void onResponse(Call<Responseprofilkurir> call, Response<Responseprofilkurir> response) {
                Username.setText("Username \t: " + response.body().getUSERNAMEKURIR());
                Nohp.setText("No HP \t: " + response.body().getNOHPKURIR());
                Tipeakun.setText("Tipe Akun \t: " + response.body().getTIPEAKUN());
            }

            @Override
            public void onFailure(Call<Responseprofilkurir> call, Throwable t) {
                Toast.makeText(DashboardKurirActivity.this, "Mohon Periksa Koneksi", Toast.LENGTH_SHORT).show();
            }
        });

    }
}