package com.codes.adminbilihjelantah.Adapter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.codes.adminbilihjelantah.Model.Bonus;
import com.codes.adminbilihjelantah.R;

import java.util.ArrayList;

public class AdapterRiwayatBonus extends RecyclerView.Adapter<AdapterRiwayatBonus.RiwayatBonusViewHolder> {
    ArrayList<Bonus> bonusArrayList;
    Context context;

    public AdapterRiwayatBonus(Context context, ArrayList<Bonus> bonusArrayList) {
        this.bonusArrayList = bonusArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RiwayatBonusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat_bonus, parent, false);
        RiwayatBonusViewHolder holder = new RiwayatBonusViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatBonusViewHolder holder, int position) {
        Bonus bonus = bonusArrayList.get(position);
        holder.tvBonusSaldo.setText("Rp 5000");
        holder.tvTglKlaim.setText(bonus.getTanggal());
        holder.tvJumlahLiter.setText(String.valueOf(bonus.getTarget_liter()));
        holder.tvKodeKlaim.setText(bonus.getCode());
    }

    @Override
    public int getItemCount() {
        return bonusArrayList.size();
    }



    public class RiwayatBonusViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJumlahLiter;
        private TextView tvBonusSaldo;
        private TextView tvTglKlaim;
        private TextView tvKodeKlaim;

        public RiwayatBonusViewHolder(@NonNull View itemView) {

            super(itemView);
            tvJumlahLiter = (TextView) itemView.findViewById(R.id.tv_jumlah_liter);
            tvBonusSaldo = (TextView) itemView.findViewById(R.id.tv_bonus_saldo);
            tvTglKlaim = (TextView) itemView.findViewById(R.id.tv_tgl_klaim);
            tvKodeKlaim = (TextView) itemView.findViewById(R.id.tv_kode_klaim);
        }
    }
}
