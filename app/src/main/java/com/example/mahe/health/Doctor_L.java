package com.example.mahe.health;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;


public class Doctor_L extends AppCompatActivity {
    Animation frombottom,ft;
    FirebaseDatabase db;
    DatabaseReference Doctor;
    EditText un,pass;
    Button login,help;
    public String user1=" ";
    public String pass1=" ";

    private FirebaseAuth firebaseAuth;
    RelativeLayout rellay1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__l);
        frombottom = AnimationUtils.loadAnimation(this,R.anim.fb);
        ft = AnimationUtils.loadAnimation(this,R.anim.ft);
        firebaseAuth=FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        rellay1 = (RelativeLayout) findViewById(R.id.reallay1);
        Doctor = db.getReference().child("Doctor");
        un = (EditText) findViewById(R.id.editText);

        pass = (EditText) findViewById(R.id.editText2);
        login = (Button) findViewById(R.id.button5);
        help = (Button) findViewById(R.id.button7);
        un.startAnimation(ft);
        pass.startAnimation(ft);
        login.startAnimation(frombottom);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if (un.getText().toString().trim().length() == 0) {
                    un.setError("Username is not entered");
                    un.requestFocus();
                }
                if (pass.getText().toString().trim().length() == 0) {
                    pass.setError("Password is not entered");
                    pass.requestFocus();
                } else {
                    signIn(un.getText().toString(), pass.getText().toString());
                }
            }


        });
        help.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), pad.class);
                startActivity(i);
            }
        });

    }
    private void signIn(final String username, final String password){
        final Context c=this;
        Doctor.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String un1= " ";
                String pass1 = " ";
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    un1 = snapshot.child("Username").getValue().toString();
                    pass1 = snapshot.child("Password").getValue().toString();
                    if (username.equals(un1) && password.equals(pass1)) {
                        Toast.makeText(Doctor_L.this, "Correct Credentials", Toast.LENGTH_LONG).show();
                        String name = snapshot.child("Name").getValue().toString();
                        Intent i=new Intent(c,Doctor.class);
                        i.putExtra("data",name);
                        startActivity(i);
                    }

                }

               }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
