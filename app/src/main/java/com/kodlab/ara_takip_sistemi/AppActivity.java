package com.kodlab.ara_takip_sistemi;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AppActivity extends AppCompatActivity   {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    TextView txt;
    Button btn_arac_konum,btn_arac_bilgi,btn_logout;
         @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        txt=findViewById(R.id.txt_arac_gelen);
        btn_arac_bilgi=findViewById(R.id.btn_arac);
        btn_arac_konum=findViewById(R.id.btn_haritalar);
        btn_logout=findViewById(R.id.btn_cikis);
        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()==null)
        {
            Intent loginintent=new Intent(AppActivity.this,MainActivity.class);
            startActivity(loginintent);

        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(getApplicationContext(), "Oturum Kapatıldı", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AppActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn_arac_bilgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppActivity.this,AracBilgisi.class);
                startActivity(intent);

            }
        });

        btn_arac_konum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AppActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });


    }



}
