package com.example.tukarsampah.Dashboard.Admin;

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

import com.example.tukarsampah.MasukActivity;
import com.example.tukarsampah.R;
import com.google.android.material.navigation.NavigationView;

public class DashboardAdminActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView txtUsername, txtTipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_kelolapengguna_admin, R.id.nav_kelolakurir_admin, R.id.nav_kelolareward_admin, R.id.nav_gantipasssword_admin, R.id.nav_logout_admin)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.getMenu().findItem(R.id.nav_logout_admin).setOnMenuItemClickListener(menuItem -> {
            fungsiLogout();
            return true;
        });
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
        Toast.makeText(DashboardAdminActivity.this, "Berhasil Log Out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(DashboardAdminActivity.this, MasukActivity.class));
        finish();
    }
}