package com.codes.adminbilihjelantah;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codes.adminbilihjelantah.Adapter.Adapter.AdapterRiwayatBonus;
import com.codes.adminbilihjelantah.Adapter.Adapter.AdapterRiwayatTarikKonfirm;
import com.codes.adminbilihjelantah.Adapter.Adapter.AdapterUser;
import com.codes.adminbilihjelantah.Model.Bonus;
import com.codes.adminbilihjelantah.Model.Penarikan;
import com.codes.adminbilihjelantah.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ListUser extends AppCompatActivity {

    private RecyclerView rvUser;
    ArrayList<User> userArrayList;
    AdapterUser adapterUser;
    DatabaseReference databaseReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        initView();

        userArrayList = new ArrayList<>();
        rvUser.setHasFixedSize(true);
        rvUser.setLayoutManager(new LinearLayoutManager(ListUser.this));

        databaseReference2 = FirebaseDatabase.getInstance().getReference("user");
        SweetAlertDialog pDialog = new SweetAlertDialog(ListUser.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        final DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("user");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userArrayList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        User l = npsnapshot.getValue(User.class);
                        userArrayList.add(l);
                    }
                    pDialog.dismissWithAnimation();
                    adapterUser = new AdapterUser(ListUser.this, userArrayList);
                    adapterUser.notifyDataSetChanged();
                    rvUser.setAdapter(adapterUser);
                } else {
                    pDialog.dismissWithAnimation();
                    new SweetAlertDialog(ListUser.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Perhatian")
                            .setContentText("Tidak ada data !")
                            .show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void initView() {
        rvUser = (RecyclerView) findViewById(R.id.rv_user);
    }
}