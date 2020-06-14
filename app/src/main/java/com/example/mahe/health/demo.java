package com.example.mahe.health;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class demo extends AppCompatActivity {
    EditText ed,ed3,ed2;
    private String publicKey="";
    public String text= "";
    private String privateKey="";
    private byte [] encodeData = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ed = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed3.setEnabled(false);
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
        text = ed.getText().toString();
        map.put("Text",text);
        map1.put("Text",text);
        String publicKey = getPublicKey();
        String privateKey = getPrivateKey();
        byte[] rsaData = ed.getText().toString().getBytes();
        try {
            encodeData = rsa.encryptByPublicKey(rsaData, publicKey);
            String encodeStr = new BigInteger(1, encodeData).toString();
            ed2.setText(encodeStr);
            map.put("Encrypted Text",encodeStr);
            map1.put("Encrypted Text",encodeStr);
            //ed2.setEnabled(false);
            map.put("Decrypted Text",ed.getText().toString());
            //byte[] decodeData = rsa.encryptByPrivateKey(encodeData, privateKey);
            //String decodeStr = new String(decodeData);
            //tv1.setText(decodeStr);
        } catch (Exception e){
            e.printStackTrace();

        }
        FirebaseDatabase.getInstance().getReference().child("EncryptionF").push().setValue(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //Toast.makeText(Doctor.this, "Successful", Toast.LENGTH_LONG).show();
                        Log.i("vbb","onComplete");

                    }
                });
        FirebaseDatabase.getInstance().getReference().child("Encryption").push().setValue(map1)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //Toast.makeText(Doctor.this, "Successful", Toast.LENGTH_LONG).show();
                        Log.i("vbb","onComplete");

                    }
                });

    }
    public  void decrypt(View v){

        ed3.setText(text);


    }
    public String getPublicKey() {
        return publicKey;
    }


    public String getPrivateKey() {
        return privateKey;
    }

}