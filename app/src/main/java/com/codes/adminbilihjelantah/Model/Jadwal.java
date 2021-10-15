package com.codes.adminbilihjelantah.Model;

public class Jadwal {
    String key,hari,tanggal;

    public Jadwal(String key, String hari, String tanggal) {
        this.key = key;
        this.hari = hari;
        this.tanggal = tanggal;
    }

    public Jadwal(){}

    public String getKey() {
        return key;
    }

    public String getHari() {
        return hari;
    }

    public String getTanggal() {
        return tanggal;
    }
}
