package com.codes.adminbilihjelantah.Model;

public class Bonus {
    String code,tanggal;
    int target_liter;
    public Bonus(String code, int target_liter, String tanggal) {
        this.code = code;
        this.target_liter = target_liter;
        this.tanggal = tanggal;
    }

    public Bonus (){}

    public String getCode() {
        return code;
    }

    public int getTarget_liter() {
        return target_liter;
    }

    public String getTanggal() {
        return tanggal;
    }
}
