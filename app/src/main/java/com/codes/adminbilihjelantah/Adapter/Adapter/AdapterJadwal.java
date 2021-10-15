package com.codes.adminbilihjelantah.Adapter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codes.adminbilihjelantah.JadwalPenyetoran;
import com.codes.adminbilihjelantah.Model.Jadwal;
import com.codes.adminbilihjelantah.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AdapterJadwal extends RecyclerView.Adapter<AdapterJadwal.RiwayatBonusViewHolder> {
    ArrayList<Jadwal> bonusArrayList;
    Context context;


    public AdapterJadwal(Context context, ArrayList<Jadwal> bonusArrayList) {
        this.bonusArrayList = bonusArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RiwayatBonusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal, parent, false);
        RiwayatBonusViewHolder holder = new RiwayatBonusViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatBonusViewHolder holder, int position) {
        Jadwal jadwal = bonusArrayList.get(position);
        holder.tvHari.setText(jadwal.getHari());
        holder.tvTanggal.setText(jadwal.getTanggal());
        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Perhatian")
                        .setContentText("Data jadwal akan dihapus ! ")
                        .setCancelText("Batal")
                        .setConfirmText("Hapus")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("jadwal").child(jadwal.getKey());
                                databaseReference.removeValue();
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        })
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return bonusArrayList.size();
    }

    public class RiwayatBonusViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHari;
        private TextView tvTanggal;
        private Button btnHapus;
        public RiwayatBonusViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHari = (TextView) itemView.findViewById(R.id.tv_hari);
            tvTanggal = (TextView) itemView.findViewById(R.id.tv_tanggal);
            btnHapus = (Button) itemView.findViewById(R.id.btn_hapus);

        }
    }
}