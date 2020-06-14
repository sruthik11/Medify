package com.example.mahe.health;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.Trace;

import org.w3c.dom.Text;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Doctor extends AppCompatActivity {
    EditText ed4,ed3,ed5,ed6,ed2,ed7;
    TextView tv1;
    public String name= "";
    private String publicKey="";
    private String privateKey="";
    private byte [] encodeData = null;
    Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);
        ed5 = (EditText) findViewById(R.id.editText5);
        ed6 = (EditText) findViewById(R.id.editText6);
        ed2 = (EditText) findViewById(R.id.editText);
        ed7 = (EditText) findViewById(R.id.editText7);
        random = new Random();
        Intent i =getIntent();
        name = i.getStringExtra("data");
        ed2.setText(name);
        ed2.setEnabled(false);
        try {
            Map<String, Object> keyMap = rsa.initKey();
            publicKey = rsa.getPublicKey(keyMap);
            privateKey = rsa.getPrivateKey(keyMap);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void encrypt (View v){
        HashMap<String,Object> map = new HashMap<>();
        HashMap<String,Object> map1 = new HashMap<>();
        String Pname = ed3.getText().toString();
        String age = ed4.getText().toString();
        String prescription = ed6.getText().toString();
        String lab=ed7.getText().toString();
        map.put("Name",name);
        map1.put("Name",name);
        map.put("PName",Pname);
        map1.put("PName",Pname);
        map.put("Age",age);
        map1.put("Age",age);
        map.put("Prescription",prescription);
        map1.put("Prescription",prescription);
        map.put("Lab Test",lab);
        map1.put("Lab Test",lab);
        String publicKey = getPublicKey();
        String privateKey = getPrivateKey();
        byte[] rsaData = ed5.getText().toString().getBytes();
        try {
            encodeData = rsa.encryptByPublicKey(rsaData, publicKey);
            String encodeStr = new BigInteger(1, encodeData).toString();
            map.put("Diagnosis",encodeStr);
            map1.put("Diagnosis",encodeStr);
            map.put("DecryptDiag",ed5.getText().toString());
            //byte[] decodeData = rsa.encryptByPrivateKey(encodeData, privateKey);
            //String decodeStr = new String(decodeData);
            //tv1.setText(decodeStr);
        } catch (Exception e){
            e.printStackTrace();

        }
        FirebaseDatabase.getInstance().getReference().child("DoctorR").push().setValue(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Doctor.this, "Successful", Toast.LENGTH_LONG).show();
                        Log.i("vbb","onComplete");

                    }
                });
        FirebaseDatabase.getInstance().getReference().child("Doc_Details").push().setValue(map1)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //Toast.makeText(Doctor.this, "Successful", Toast.LENGTH_LONG).show();
                        Log.i("vbb","onComplete");

                    }
                });
        /*Trace tracer = FirebasePerformance.getInstance().newTrace("loadDataTrace");
        tracer.start();
        int value=random.nextInt(100);
        if(value<50){
            tracer.incrementCounter("load_data_failed");
        }
        else{
            tracer.incrementCounter("load_data_success");
        }
        tracer.stop();
       // mHandlerMainThread.sendEmptyMessage(0);*/

        }

    public  void decrypt(View v){
        String privateKey = getPrivateKey();

        try {
            byte[] decodeData = rsa.encryptByPrivateKey(encodeData, privateKey);
            String decodeStr = new String(decodeData);
            tv1.setText(decodeStr);
        } catch (Exception e){
            e.printStackTrace();

        }
    }

    public String getPublicKey() {
        return publicKey;
    }


    public String getPrivateKey() {
        return privateKey;
    }


}
