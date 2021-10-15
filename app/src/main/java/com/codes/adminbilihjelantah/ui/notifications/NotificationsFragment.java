package com.codes.adminbilihjelantah.ui.notifications;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codes.adminbilihjelantah.Adapter.Adapter.AdapterRiwayatSetorMinyak;
import com.codes.adminbilihjelantah.Adapter.Adapter.AdapterRiwayatTarik;
import com.codes.adminbilihjelantah.JadwalPenyetoran;
import com.codes.adminbilihjelantah.Model.Penarikan;
import com.codes.adminbilihjelantah.Model.SetorMinyak;
import com.codes.adminbilihjelantah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private RecyclerView rvRiwayatSetor;
    ArrayList<Penarikan> setorMinyakArrayList;
    AdapterRiwayatTarik adapterRiwayatSetorMinyak;
    DatabaseReference databaseReference2;
    ArrayList<String> listUser = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        rvRiwayatSetor = (RecyclerView) root.findViewById(R.id.rv_riwayat_tarik);
        setorMinyakArrayList = new ArrayList<>();
        rvRiwayatSetor.setHasFixedSize(true);
        rvRiwayatSetor.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseReference2 = FirebaseDatabase.getInstance().getReference("user");
        SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        final DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("tarik_riwayat");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                setorMinyakArrayList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        Penarikan l = npsnapshot.getValue(Penarikan.class);
                        setorMinyakArrayList.add(l);
                    }
                    pDialog.dismissWithAnimation();
                    adapterRiwayatSetorMinyak = new AdapterRiwayatTarik(getContext(), setorMinyakArrayList);
                    adapterRiwayatSetorMinyak.notifyDataSetChanged();
                    rvRiwayatSetor.setAdapter(adapterRiwayatSetorMinyak);
                } else {
                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Perhatian")
                            .setContentText("Tidak ada data !")
                            .show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return root;
    }
}