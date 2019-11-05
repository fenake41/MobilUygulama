package com.kodlab.ara_takip_sistemi;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AracBilgisi extends AppCompatActivity {

    private EditText et1,et2,et3;
    private TextView tw2,tw3,tw4,tw5;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_bilgisi);
        tw3=findViewById(R.id.textView6);
        tw2=findViewById(R.id.textView2);
        et1=findViewById(R.id.et_ad_gelen);
        et2=findViewById(R.id.et_km_gelen);
        et3=findViewById(R.id.et_muayene_gelen);
        tf1= Typeface.createFromAsset(getAssets(),"fonts/ronansa2.ttf");
        tw2.setTypeface(tf1);
       final ArrayList<String> plaka= new ArrayList<String>();

        mAuth=FirebaseAuth.getInstance();
      final String user_id=mAuth.getCurrentUser().getUid();
        mDatabase=FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("Arac");
        final ValueEventListener valueEventListener = mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot>  keys=dataSnapshot.getChildren();
                  for( DataSnapshot key : keys)
                  {
                    plaka.add(key.child("arac_plaka").getValue().toString());
                          //  String plaka=  ds.child("users").child(user_id).child("Arac").child("-LWmlsva9O4G3aw2vYP0").child("arac_plaka").getValue().toString();
                  }



              // String plaka= dataSnapshot.child("users").child(user_id).child("Arac").child("-LWmlsva9O4G3aw2vYP0").child("arac_plaka").getValue().toString();
               //et1.setText(plaka);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







}
    }