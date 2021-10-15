package com.codes.adminbilihjelantah;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codes.adminbilihjelantah.Adapter.Adapter.AdapterJadwal;
import com.codes.adminbilihjelantah.Adapter.Adapter.AdapterRiwayatTarikKonfirm;
import com.codes.adminbilihjelantah.Model.Jadwal;
import com.codes.adminbilihjelantah.Model.Penarikan;
import com.codes.adminbilihjelantah.ui.notifications.NotificationsViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class JadwalPenyetoran extends AppCompatActivity {

    private Button btn_add_jadwal;
    private RecyclerView rvJadwal;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    DatabaseReference databaseReference;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    EditText et_hari;
    EditText et_tanggal;

    ArrayList<Jadwal> jadwalArrayList ;
    AdapterJadwal adapterJadwal;
    DatabaseReference databaseReference2;

    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_penyetoran);
        initView();
        jadwalArrayList=new ArrayList<>();
        rvJadwal.setLayoutManager(new LinearLayoutManager(JadwalPenyetoran.this));
        final DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("jadwal");
        SweetAlertDialog pDialog = new SweetAlertDialog(JadwalPenyetoran.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jadwalArrayList.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        Jadwal l=npsnapshot.getValue(Jadwal.class);
                        jadwalArrayList.add(l);
                    }
                    pDialog.dismissWithAnimation();
                    adapterJadwal= new AdapterJadwal(JadwalPenyetoran.this,jadwalArrayList);
                    adapterJadwal.notifyDataSetChanged();
                    rvJadwal.setAdapter(adapterJadwal);
                }else {
                    pDialog.dismissWithAnimation();
                    new SweetAlertDialog(JadwalPenyetoran.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Perhatian")
                            .setContentText("Tidak ada data !")
                            .show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btn_add_jadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new AlertDialog.Builder(JadwalPenyetoran.this);
                inflater = getLayoutInflater();
                dialogView = inflater.inflate(R.layout.dialog_jadwal, null);
                dialog.setView(dialogView);
                dialog.setCancelable(true);
                dialog.setTitle("Form Jadwal");

                 et_hari = (EditText) dialogView.findViewById(R.id.et_hari);
               et_tanggal = (EditText) dialogView.findViewById(R.id.et_tanggal);
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();
                    }

                };

                et_tanggal.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       new DatePickerDialog(JadwalPenyetoran.this, date, myCalendar
                               .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                               myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                   }
               });

                dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (et_hari.getText().toString().equals("")||et_tanggal.getText().toString().equals("")){
                            et_tanggal.setError("Isi Semua Terlebih Dahulu");
                            et_hari.setError("Isi Semu Terlebih Dahulu");
                        }else{
                            databaseReference = FirebaseDatabase.getInstance().getReference("jadwal");
                            String key = databaseReference.push().getKey();
                            Jadwal jadwal = new Jadwal(key,et_hari.getText().toString(),et_tanggal.getText().toString());
                            databaseReference.child(key).setValue(jadwal);
                            dialog.dismiss();
                            new SweetAlertDialog(JadwalPenyetoran.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Perhatian ")
                                    .setContentText("Data Jadwal Berhasil Ditambahkan")
                                    .show();
                        }
                    }
                });
                dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    private void initView() {
        btn_add_jadwal = (Button) findViewById(R.id.button);
        rvJadwal = (RecyclerView) findViewById(R.id.rv_jadwal);
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        et_tanggal.setText(sdf.format(myCalendar.getTime()));
    }

}