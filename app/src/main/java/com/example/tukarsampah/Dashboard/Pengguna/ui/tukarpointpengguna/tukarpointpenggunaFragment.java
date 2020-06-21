package com.example.tukarsampah.Dashboard.Pengguna.ui.tukarpointpengguna;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tukarsampah.Api.Service;
import com.example.tukarsampah.Dashboard.Api.Operasipengguna;
import com.example.tukarsampah.Dashboard.Model.Responsetukarpointgetrewardpengguna;
import com.example.tukarsampah.Dashboard.Model.Tukarpointgetrewardpengguna;
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
//        getPointpengguna(sharedPreferences.getString("ID_AKUN", ""));
        getRewardpengguna();
        return root;
    }

    private void initView(View root){
        Jumlahpoint = (TextView) root.findViewById(R.id.txtJumlahpointtukarpointpengguna);
        Reward = (Spinner) root.findViewById(R.id.spRewardtukarpointpengguna);
        Ambilpoint = (Button) root.findViewById(R.id.btnAmbilhadiahtukarpointpengguna);
    }

    private void getPointpengguna(String idpengguna){

    }

    private void getRewardpengguna(){
        Operasipengguna operasipengguna = Service.Koneksi().create(Operasipengguna.class);
        Call<Responsetukarpointgetrewardpengguna> responsetukarpointgetrewardpengguna = operasipengguna.getDatareward();
        responsetukarpointgetrewardpengguna.enqueue(new Callback<Responsetukarpointgetrewardpengguna>() {
            @Override
            public void onResponse(Call<Responsetukarpointgetrewardpengguna> call, Response<Responsetukarpointgetrewardpengguna> response) {
                List<Tukarpointgetrewardpengguna> datareward = response.body().getData();
                for (int i = 0; i < datareward.size(); i++){
                    String idreward = datareward.get(i).getId_reward();
                    tempidreward.add(idreward);
                    String hadiahreward = datareward.get(i).getHadiah_reward();
                    temphadiahreward.add(hadiahreward);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                        (getActivity(), android.R.layout.simple_spinner_item, temphadiahreward );

                dataAdapter.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);

                Reward.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<Responsetukarpointgetrewardpengguna> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}