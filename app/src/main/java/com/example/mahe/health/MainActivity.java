package com.example.mahe.health;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    ImageView bgapp,clover;
    LinearLayout textsplash,texthome,menus;
    Animation frombottom;
    Button b1,b2,b3,b4,b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);

        bgapp.animate().translationY(-1900).setDuration(1500).setStartDelay(300);
        clover.animate().alpha(0).setDuration(1000).setStartDelay(600);
        textsplash.animate().translationY(140).alpha(0).setDuration(1000).setStartDelay(300);
        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);

        clickB1();
        clickB2();
        clickB3();
        clickB4();
        clickB5();
    }
    public void clickB1(){
        final Context c=this;
        b1= (Button) findViewById(R.id.button2);
        b1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0){
                            Intent i=new Intent(c,Doctor_L.class);
                            startActivity(i);
            }
        });
    }
    public void clickB2(){
        final Context c=this;
        b2= (Button) findViewById(R.id.button3);
        b2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0){
                Intent i=new Intent(c,Patient_L.class);
                startActivity(i);
            }
        });
    }
    public void clickB3(){
        final Context c=this;
        b3= (Button) findViewById(R.id.button4);
        b3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0){
                Intent i=new Intent(c,pharma_l.class);
                startActivity(i);
            }
        });
    }
    public void clickB4(){
        final Context c=this;
        b4= (Button) findViewById(R.id.button5);
        b4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0){
                Intent i=new Intent(c,radio_l.class);
                startActivity(i);
            }
        });
    }
    public void clickB5(){
        final Context c=this;
        b5= (Button) findViewById(R.id.button6);
        b5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0){
                Intent i=new Intent(c,demo.class);
                startActivity(i);
            }
        });
    }

}

