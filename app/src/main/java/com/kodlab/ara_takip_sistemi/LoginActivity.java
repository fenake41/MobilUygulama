package com.kodlab.ara_takip_sistemi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    Button btn;
    EditText et1,et2,et3,et4,et5;
    TextView txt;
    Typeface tf1;
    private ProgressDialog registerProgress;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn=findViewById(R.id.btn_uyeol);
        txt=findViewById(R.id.txt_hesap);
        et1=findViewById(R.id.et_username);
        et2=findViewById(R.id.et_pass);
        et3=findViewById(R.id.et_pass2);
        et4=findViewById(R.id.et_km);
        et5=findViewById(R.id.et_muayene);
        registerProgress = new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();


        tf1= Typeface.createFromAsset(getAssets(),"fonts/ronansa2.ttf");
        txt.setTypeface(tf1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=et1.getText().toString();
                String pw=et2.getText().toString();
                String araba=et3.getText().toString();
                String km=et4.getText().toString();
                String muayene=et5.getText().toString();

                if(!TextUtils.isEmpty(id) || !TextUtils.isEmpty(pw) || !TextUtils.isEmpty(araba) || !TextUtils.isEmpty(km) || !TextUtils.isEmpty(muayene))
                {
                    registerProgress.setTitle("Kaydediliyor");
                    registerProgress.setMessage("Hesabınız oluşturuluyor.Lütfen bekleyiniz..");
                    registerProgress.setCanceledOnTouchOutside(false);
                    registerProgress.show();
                    register_user(id,pw,araba,km,muayene);

                }

            }
        });

    }

    private void register_user(final String id, String pw, final String araba, final String km, final String muayene) {
        mAuth.createUserWithEmailAndPassword(id,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    String user_id=mAuth.getCurrentUser().getUid();
                    mDatabase=FirebaseDatabase.getInstance().getReference().child("users").child(user_id);
                    HashMap<String,String> userMap=new HashMap<>();
                    userMap.put("AracAdi",araba);
                    userMap.put("AracKm",km);
                    userMap.put("AracMuayeneTarih",muayene);
                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                registerProgress.dismiss();
                                Intent appintent=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(appintent);
                            }
                        }
                    });

                }
                else
                {
                    registerProgress.dismiss();
                    Toast.makeText(getApplicationContext(),"Hata"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
