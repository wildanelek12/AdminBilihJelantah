package com.codes.adminbilihjelantah.Model;

public class SetorMinyak {
    String code,nama,alamat,hp,tanggal,jumlah,metode_setor,metode_bayar;
    double total_saldo;

    public SetorMinyak(String code, String nama, String alamat, String hp, String tanggal, String jumlah, String metode_setor, String metode_bayar, double total_saldo) {
        this.code = code;
        this.nama = nama;
        this.alamat = alamat;
        this.hp = hp;
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.metode_setor = metode_setor;
        this.metode_bayar = metode_bayar;
        this.total_saldo = total_saldo;
    }

    public SetorMinyak (){}

    public String getCode() {
        return code;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getHp() {
        return hp;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getMetode_setor() {
        return metode_setor;
    }

    public String getMetode_bayar() {
        return metode_bayar;
    }

    public double getTotal_saldo() {
        return total_saldo;
    }
}
