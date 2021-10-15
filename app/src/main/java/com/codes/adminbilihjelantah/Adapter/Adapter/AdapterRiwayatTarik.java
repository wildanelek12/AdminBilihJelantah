package com.codes.adminbilihjelantah.Adapter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codes.adminbilihjelantah.Model.Penarikan;
import com.codes.adminbilihjelantah.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterRiwayatTarik extends RecyclerView.Adapter<AdapterRiwayatTarik.RiwayatTarikViewHolder> {

    ArrayList<Penarikan> penarikanArrayList;
    Context context;



    public AdapterRiwayatTarik(Context context, ArrayList<Penarikan> penarikanArrayList) {
        this.context = context;
        this.penarikanArrayList = penarikanArrayList;
    }

    @NonNull
    @Override
    public RiwayatTarikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat_penarikan, parent, false);
        RiwayatTarikViewHolder holder = new RiwayatTarikViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatTarikViewHolder holder, int position) {
        Penarikan penarikan = penarikanArrayList.get(position);
        holder.status.setText(penarikan.getStatus());
        holder.tvDinBank.setText(penarikan.getBank());
        holder.tvDinNasabah.setText(penarikan.getNama());
        holder.tvDinNoBank.setText(penarikan.getNo_rek());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.tvJumlahTarik.setText(formatRupiah.format(penarikan.getNominal()));
        holder.tvTanggalTarik.setText(penarikan.getTanggal());
        holder.tvKodeTarik.setText(penarikan.getCode());

    }

    @Override
    public int getItemCount() {
        return penarikanArrayList.size();
    }




    public class RiwayatTarikViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJumlahTarik;
        private TextView tvTanggalTarik;
        private TextView tvDinBank;
        private TextView tvDinNoBank;
        private TextView tvDinNasabah;
        private TextView status;
        private TextView tvKodeTarik;

        public RiwayatTarikViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJumlahTarik = (TextView) itemView.findViewById(R.id.tv_jumlah_tarik);
            tvTanggalTarik = (TextView) itemView.findViewById(R.id.tv_tanggal_tarik);
            tvDinBank = (TextView) itemView.findViewById(R.id.tv_din_bank);
            tvDinNoBank = (TextView) itemView.findViewById(R.id.tv_din_no_bank);
            tvDinNasabah = (TextView) itemView.findViewById(R.id.tv_din_nasabah);
            status = (TextView) itemView.findViewById(R.id.status);
            tvKodeTarik = (TextView) itemView.findViewById(R.id.tv_kode_tarik);
        }
    }
}
