package com.example.tukarsampah.Dashboard.Pengguna.ui.tukarpointpengguna;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tukarsampah.Api.Service;
import com.example.tukarsampah.Dashboard.Api.Operasipengguna;
import com.example.tukarsampah.Dashboard.Model.Responsetukarpointgetpointpengguna;
import com.example.tukarsampah.Dashboard.Model.Responsetukarpointgetrewardpengguna;
import com.example.tukarsampah.Dashboard.Model.Tukarpointgetpointpengguna;
import com.example.tukarsampah.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class tukarpointpenggunaFragment extends Fragment {

    private TextView Jumlahpoint;
    private Spinner Reward;
    private Button Ambilpoint;

    private SharedPreferences sharedPreferences;

    private List<String> tempidreward = new ArrayList<String>();
    private List<String> temphadiahreward = new ArrayList<String>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tukarpoint_pengguna_fragment, container, false);
        sharedPreferences = getContext().getSharedPreferences("LOGIN", MODE_PRIVATE);
        initView(root);
        getPointpengguna(sharedPreferences.getString("ID_AKUN", ""));
//        getRewardpengguna();
        return root;
    }

    private void initView(View root){
        Jumlahpoint = (TextView) root.findViewById(R.id.txtJumlahpointtukarpointpengguna);
        Reward = (Spinner) root.findViewById(R.id.spRewardtukarpointpengguna);
        Ambilpoint = (Button) root.findViewById(R.id.btnAmbilhadiahtukarpointpengguna);
    }

    private void getPointpengguna(String idpengguna){
        Operasipengguna operasipengguna = Service.Koneksi().create(Operasipengguna.class);
        Call<Responsetukarpointgetpointpengguna> responsetukarpointgetpointpengguna = operasipengguna.getPointpengguna(idpengguna);
        responsetukarpointgetpointpengguna.enqueue(new Callback<Responsetukarpointgetpointpengguna>() {
            @Override
            public void onResponse(Call<Responsetukarpointgetpointpengguna> call, Response<Responsetukarpointgetpointpengguna> response) {
                List<Tukarpointgetpointpengguna> datapoint = response.body().getDatapoint();
                Jumlahpoint.setText("Jumlah Point : " + datapoint.get(0).getJUMLAH_POINT());
            }

            @Override
            public void onFailure(Call<Responsetukarpointgetpointpengguna> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getRewardpengguna(){
        Operasipengguna operasipengguna = Service.Koneksi().create(Operasipengguna.class);
        Call<Responsetukarpointgetrewardpengguna> responsetukarpointgetrewardpengguna = operasipengguna.getDatareward();

    }
}