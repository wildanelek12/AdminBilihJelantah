package com.codes.adminbilihjelantah.Model;

public class User {
    String nama,email;

    public User(String nama, String email) {
        this.nama = nama;
        this.email = email;
    }

    public User(){}

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }
}
