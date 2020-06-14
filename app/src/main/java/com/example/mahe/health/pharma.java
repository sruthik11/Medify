package com.example.mahe.health;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class pharma extends AppCompatActivity {
    Spinner spinner;
    private TextView text;
    String name = " ";
    public String item = "";
    DatabaseReference Doctor1;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharma);
        text = (TextView) findViewById(R.id.text);
        spinner = (Spinner) findViewById(R.id.mySpinner);
        Doctor1 = FirebaseDatabase.getInstance().getReference().child("Patients");
        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(pharma.this, android.R.layout.simple_spinner_dropdown_item, spinnerDataList);
        spinner.setAdapter(adapter);
        spinnerDataList.add(0, "Select the Patient");
        retriveData();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Select the Patient")){
                    text.setText(" ");
                }
                else{
                    item = parent.getItemAtPosition(position).toString();
                    FirebaseDatabase.getInstance().getReference().child("DoctorR")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String un1= " ";
                                    String pass =" ";
                                    String s = "";
                                    for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                        //un1 = snapshot.child("Name").getValue().toString();
                                        pass = snapshot.child("PName").getValue().toString();
                                        if (pass.equals(item)) {
                                            s = s + "\n" + "Patient Name :" + snapshot.child("PName").getValue().toString() + "\n" +
                                                    "\n" + "Doctor Name : " + snapshot.child("Name").getValue().toString() + "\n" +
                                                    "\n" + "Patient Age : " + snapshot.child("Age").getValue().toString() + "\n" +
                                                    "\n" + "Prescription : " + snapshot.child("Prescription").getValue().toString() + "\n" +
                                                     "\n \n";

                                        }


                                    }
                                    text.setText(s);

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void retriveData(){
        listener = Doctor1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String un1= " ";
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    un1 = snapshot.child("Name").getValue().toString();
                    spinnerDataList.add(un1);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
