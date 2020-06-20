package com.example.tukarsampah.Dashboard.Admin.Adapter;

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
import com.example.tukarsampah.Dashboard.Admin.Api.Operasiadmin;
import com.example.tukarsampah.Dashboard.Admin.Model.Kelolakuriradmin;
import com.example.tukarsampah.Dashboard.Admin.Model.Responseoperasi;
import com.example.tukarsampah.MasukActivity;
import com.example.tukarsampah.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapterkelolakuriradmin extends RecyclerView.Adapter<Adapterkelolakuriradmin.HolderData>{
    private Context ctx;
    private List<Kelolakuriradmin> listKurir;
    private String IdUsername;
    private ConnectivityManager Koneksi;

    public Adapterkelolakuriradmin(Context ctx, List<Kelolakuriradmin> listKurir) {
        this.ctx = ctx;
        this.listKurir = listKurir;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.kelolakurir_admin_card, parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        Kelolakuriradmin kelolakuriradmin = listKurir.get(position);
        holder.tvId.setText(kelolakuriradmin.getId_kurir());
        holder.tvUsername.setText(kelolakuriradmin.getUsername_kurir());
    }

    @Override
    public int getItemCount() {
        return listKurir.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvId, tvUsername;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.tvidkelolakuriradmin);
            tvUsername = (TextView) itemView.findViewById(R.id.tvusernamekelolakuriradmin);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IdUsername = tvUsername.getText().toString().trim();
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(itemView.getContext());
                    dialogPesan.setCancelable(true);
                    dialogPesan.setTitle("Pilih Operasi");
                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (cekKoneksi()){
                                hapusKurir();
                            }else {
                                Toast.makeText(itemView.getContext(), "Mohon Periksa Koneksi", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    dialogPesan.setNegativeButton("Reset Password", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (cekKoneksi()){
                                resetpassKurir();
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

        private void hapusKurir(){
            Operasiadmin operasiadmin = Service.Koneksi().create(Operasiadmin.class);
            Call<Responseoperasi> responsehapusakunpengguna = operasiadmin.deletePengguna(IdUsername, "KURIR");
            responsehapusakunpengguna.enqueue(new Callback<Responseoperasi>() {
                @Override
                public void onResponse(Call<Responseoperasi> call, Response<Responseoperasi> response) {
                    if (response.body().getSTATUS().equalsIgnoreCase("BERHASIL")){
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

        private void resetpassKurir(){
            Operasiadmin operasiadmin = Service.Koneksi().create(Operasiadmin.class);
            Call<Responseoperasi> responseresetpasswordpengguna = operasiadmin.resetpassPengguna(IdUsername, "KURIR");
            responseresetpasswordpengguna.enqueue(new Callback<Responseoperasi>() {
                @Override
                public void onResponse(Call<Responseoperasi> call, Response<Responseoperasi> response) {
                    if (response.body().getSTATUS().equalsIgnoreCase("BERHASIL")){
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
