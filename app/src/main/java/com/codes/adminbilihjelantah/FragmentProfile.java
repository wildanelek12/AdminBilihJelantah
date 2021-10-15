package com.codes.adminbilihjelantah;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codes.adminbilihjelantah.Adapter.Adapter.AdapterRiwayatBonus;
import com.codes.adminbilihjelantah.Adapter.Adapter.AdapterRiwayatSetorMinyak;
import com.codes.adminbilihjelantah.Adapter.Adapter.AdapterRiwayatTarik;
import com.codes.adminbilihjelantah.Model.Bonus;
import com.codes.adminbilihjelantah.Model.Penarikan;
import com.codes.adminbilihjelantah.Model.SetorMinyak;
import com.codes.adminbilihjelantah.ui.notifications.NotificationsViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProfile extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private RecyclerView rvRiwayatSetor;
    ArrayList<Bonus> setorMinyakArrayList;
    AdapterRiwayatBonus adapterRiwayatSetorMinyak;
    DatabaseReference databaseReference2;
    ArrayList<String> listUser = new ArrayList<>();

    public FragmentProfile() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentProfile newInstance(String param1, String param2) {
        FragmentProfile fragment = new FragmentProfile();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        rvRiwayatSetor = (RecyclerView) root.findViewById(R.id.rv_klaim_bonus);
        setorMinyakArrayList = new ArrayList<>();
        rvRiwayatSetor.setHasFixedSize(true);
        rvRiwayatSetor.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseReference2 = FirebaseDatabase.getInstance().getReference("user");
        SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        final DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("bonus_riwayat");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                setorMinyakArrayList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        Bonus l = npsnapshot.getValue(Bonus.class);
                        setorMinyakArrayList.add(l);
                    }
                    pDialog.dismissWithAnimation();
                    adapterRiwayatSetorMinyak = new AdapterRiwayatBonus(getContext(), setorMinyakArrayList);
                    adapterRiwayatSetorMinyak.notifyDataSetChanged();
                    rvRiwayatSetor.setAdapter(adapterRiwayatSetorMinyak);
                } else {
                    pDialog.dismissWithAnimation();
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