package com.codes.adminbilihjelantah;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codes.adminbilihjelantah.Adapter.Adapter.AdapterRiwayatTarik;
import com.codes.adminbilihjelantah.Adapter.Adapter.AdapterRiwayatTarikKonfirm;
import com.codes.adminbilihjelantah.Model.Penarikan;
import com.codes.adminbilihjelantah.ui.notifications.NotificationsViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class KonfirmasiActivity extends AppCompatActivity {

    private RecyclerView rvKonfirm;
    private NotificationsViewModel notificationsViewModel;
    ArrayList<Penarikan> setorMinyakArrayList;
    AdapterRiwayatTarikKonfirm adapterRiwayatSetorMinyak;
    DatabaseReference databaseReference2;
    ArrayList<String> listUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);
        initView();

        setorMinyakArrayList = new ArrayList<>();
        rvKonfirm.setHasFixedSize(true);
        rvKonfirm.setLayoutManager(new LinearLayoutManager(KonfirmasiActivity.this));

        SweetAlertDialog pDialog = new SweetAlertDialog(KonfirmasiActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        final DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("tarik_riwayat");
        reference2.orderByChild("status").equalTo("waiting").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                setorMinyakArrayList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        Penarikan l = npsnapshot.getValue(Penarikan.class);
                        setorMinyakArrayList.add(l);
                    }
                    pDialog.dismissWithAnimation();
                    adapterRiwayatSetorMinyak = new AdapterRiwayatTarikKonfirm(KonfirmasiActivity.this, setorMinyakArrayList);
                    adapterRiwayatSetorMinyak.notifyDataSetChanged();
                    rvKonfirm.setAdapter(adapterRiwayatSetorMinyak);
                } else {
                    pDialog.dismissWithAnimation();
                    new SweetAlertDialog(KonfirmasiActivity.this, SweetAlertDialog.WARNING_TYPE)
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

        private void initView () {
            rvKonfirm = (RecyclerView) findViewById(R.id.rv_konfirm);
        }
    }