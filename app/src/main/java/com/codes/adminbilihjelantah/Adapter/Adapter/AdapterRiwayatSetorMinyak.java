package com.codes.adminbilihjelantah.Adapter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codes.adminbilihjelantah.Model.SetorMinyak;
import com.codes.adminbilihjelantah.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterRiwayatSetorMinyak extends RecyclerView.Adapter<AdapterRiwayatSetorMinyak.SetorMinyakViewHolder> {

    ArrayList<SetorMinyak> setorMinyakArrayList;
    Context context;



    public AdapterRiwayatSetorMinyak(Context context, ArrayList<SetorMinyak> setorMinyakArrayList) {
        this.setorMinyakArrayList = setorMinyakArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public SetorMinyakViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat_setor, parent, false);
        SetorMinyakViewHolder holder = new SetorMinyakViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SetorMinyakViewHolder holder, int position) {
        SetorMinyak setorMinyak = setorMinyakArrayList.get(position);
        holder.tvJumlahLiter.setText(setorMinyak.getJumlah());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.tvBonusSaldo.setText(formatRupiah.format(setorMinyak.getTotal_saldo()));
        holder.tvKodeSetor.setText(setorMinyak.getCode());
        holder.tvTglSetor.setText(setorMinyak.getTanggal());
        holder.tvMetodeSetor.setText(setorMinyak.getMetode_setor());
        holder.tvMetodeBayar.setText(setorMinyak.getMetode_bayar());
        holder.tvNama.setText(setorMinyak.getNama());
        holder.tvAlamat.setText(setorMinyak.getAlamat());
        holder.tvNoHp.setText(setorMinyak.getHp());
    }

    @Override
    public int getItemCount() {
        return setorMinyakArrayList.size();
    }

    private void initView() {

    }


    public class SetorMinyakViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJumlahLiter;
        private TextView tvBonusSaldo;
        private TextView tvTglSetor;
        private TextView tvKodeSetor;
        private TextView tvMetodeSetor;
        private TextView tvMetodeBayar;
        private TextView tvNama;
        private TextView tvAlamat;
        private TextView tvNoHp;

        public SetorMinyakViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJumlahLiter = (TextView) itemView.findViewById(R.id.tv_jumlah_liter);
            tvBonusSaldo = (TextView) itemView.findViewById(R.id.tv_bonus_saldo);
            tvTglSetor = (TextView) itemView.findViewById(R.id.tv_tgl_setor);
            tvKodeSetor = (TextView) itemView.findViewById(R.id.tv_kode_setor);
            tvMetodeSetor = (TextView) itemView.findViewById(R.id.tv_metode_setor);
            tvMetodeBayar = (TextView) itemView.findViewById(R.id.tv_metode_bayar);
            tvNama = (TextView) itemView.findViewById(R.id.tv_nama);
            tvAlamat = (TextView) itemView.findViewById(R.id.tv_alamat);
            tvNoHp = (TextView) itemView.findViewById(R.id.tv_no_hp);
        }
    }
}
