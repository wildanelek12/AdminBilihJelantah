package com.codes.adminbilihjelantah.Adapter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codes.adminbilihjelantah.KonfirmasiActivity;
import com.codes.adminbilihjelantah.Model.Penarikan;
import com.codes.adminbilihjelantah.Model.User;
import com.codes.adminbilihjelantah.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.UserViewHolder> {

    ArrayList<User> userList;
    Context context;



    public AdapterUser (Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        UserViewHolder holder = new UserViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.tv_nama.setText(user.getNama());
        holder.tv_email.setText(user.getEmail());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }




    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nama,tv_email;



        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_email = (TextView) itemView.findViewById(R.id.tv_email);
        }
    }
}
