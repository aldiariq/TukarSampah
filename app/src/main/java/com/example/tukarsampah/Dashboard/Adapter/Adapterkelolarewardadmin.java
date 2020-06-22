package com.example.tukarsampah.Dashboard.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tukarsampah.Api.Service;
import com.example.tukarsampah.Dashboard.Api.Operasiadmin;
import com.example.tukarsampah.Dashboard.Model.Kelolarewardadmin;
import com.example.tukarsampah.Dashboard.Model.Responseoperasi;
import com.example.tukarsampah.MasukActivity;
import com.example.tukarsampah.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapterkelolarewardadmin extends RecyclerView.Adapter<Adapterkelolarewardadmin.HolderData>{
    private Context ctx;
    private List<Kelolarewardadmin> listReward;
    private String IdReward;
    private ConnectivityManager Koneksi;

    public Adapterkelolarewardadmin(Context ctx, List<Kelolarewardadmin> listReward) {
        this.ctx = ctx;
        this.listReward = listReward;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.kelolareward_admin_card, parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        Kelolarewardadmin kelolarewardadmin = listReward.get(position);
        holder.tvId.setText(kelolarewardadmin.getId_reward());
        holder.tvHadiah.setText(kelolarewardadmin.getHadiah_reward());
        holder.tvPoint.setText(kelolarewardadmin.getPoint_reward());
    }

    @Override
    public int getItemCount() {
        return listReward.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvId, tvHadiah, tvPoint;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.tvidkelolarewardadmin);
            tvHadiah = (TextView) itemView.findViewById(R.id.tvhadiahkelolarewardadmin);
            tvPoint = (TextView) itemView.findViewById(R.id.tvpointkelolarewardadmin);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IdReward = tvId.getText().toString().trim();
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(itemView.getContext());
                    dialogPesan.setCancelable(true);
                    dialogPesan.setTitle("Pilih Operasi");
                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (cekKoneksi()){
                                hapusReward();
                            }else {
                                Toast.makeText(itemView.getContext(), "Mohon Periksa Koneksi", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    dialogPesan.show();
                }
            });
        }

        private boolean cekKoneksi(){
            Koneksi = (ConnectivityManager) itemView.getContext().getSystemService(MasukActivity.CONNECTIVITY_SERVICE);
            {
                if (Koneksi.getActiveNetworkInfo() != null && Koneksi.getActiveNetworkInfo().isAvailable() && Koneksi.getActiveNetworkInfo().isConnected()) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        private void hapusReward(){
            Operasiadmin operasiadmin = Service.Koneksi().create(Operasiadmin.class);
            Call<Responseoperasi> responsehapusreward = operasiadmin.deleteReward(IdReward);
            responsehapusreward.enqueue(new Callback<Responseoperasi>() {
                @Override
                public void onResponse(Call<Responseoperasi> call, Response<Responseoperasi> response) {
                    if (response.body().getSTATUS().equalsIgnoreCase("BERHASIL")){
                        listReward.remove(getPosition());
                        notifyItemRemoved(getPosition());

                        Toast.makeText(itemView.getContext(), response.body().getKETERANGAN(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(itemView.getContext(), response.body().getKETERANGAN(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Responseoperasi> call, Throwable t) {
                    Toast.makeText(itemView.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
