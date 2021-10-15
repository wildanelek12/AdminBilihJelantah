package com.codes.adminbilihjelantah.Model;



public class Penarikan {
    String code,bank,no_rek,nama,status,tanggal,uid,childnya;
    double nominal;

    public Penarikan(String code, String bank, String no_rek, String nama, String status, double nominal ,String tanggal,String uid,String childnya) {
        this.code = code;
        this.bank = bank;
        this.no_rek = no_rek;
        this.nama = nama;
        this.status = status;
        this.nominal = nominal;
        this.childnya = childnya;
        this.uid = uid;
        this.tanggal = tanggal;
    }

    public String getUid() {
        return uid;
    }

    public String getChildnya() {
        return childnya;
    }

    public String getTanggal() {
        return tanggal;
    }

    public Penarikan(){};

    public String getCode() {
        return code;
    }

    public String getBank() {
        return bank;
    }

    public String getNo_rek() {
        return no_rek;
    }

    public String getNama() {
        return nama;
    }

    public String getStatus() {
        return status;
    }

    public double getNominal() {
        return nominal;
    }
}
