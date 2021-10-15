package com.codes.adminbilihjelantah.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.codes.adminbilihjelantah.JadwalPenyetoran;
import com.codes.adminbilihjelantah.KonfirmasiActivity;
import com.codes.adminbilihjelantah.ListUser;
import com.codes.adminbilihjelantah.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ConstraintLayout btnMenuHome1;
    private ConstraintLayout btnMenuHome2;
    private ConstraintLayout btnMenuHome3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initView(root);

        btnMenuHome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KonfirmasiActivity.class);
                startActivity(intent);
            }
        });
        btnMenuHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), JadwalPenyetoran.class);
                startActivity(intent);
            }
        });

        btnMenuHome3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListUser.class);
                startActivity(intent);
            }
        });
        return root;
    }

    private void initView(View view) {
        btnMenuHome1 = (ConstraintLayout) view.findViewById(R.id.btn_menu_home_1);
        btnMenuHome2 = (ConstraintLayout) view.findViewById(R.id.btn_menu_home_2);
        btnMenuHome3 = (ConstraintLayout) view.findViewById(R.id.btn_menu_home_3);
    }
}