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

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressDialog loginProgress;
    Button btn;
    TextView txt,txt2;
    EditText et1,et2;
    Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();

        btn=findViewById(R.id.btn_giris);
        et1=findViewById(R.id.et_kullanıcı_adi);
        et2=findViewById(R.id.et_sifre);
        txt=findViewById(R.id.txt_uye_ol);
        txt2=findViewById(R.id.textView);
        tf1= Typeface.createFromAsset(getAssets(),"fonts/ronansa.ttf");
        txt2.setTypeface(tf1);
        loginProgress=new ProgressDialog(this);
        if(mAuth.getCurrentUser()!=null)
        {
            Intent loginintent=new Intent(MainActivity.this,AppActivity.class);
            startActivity(loginintent);
            Toast.makeText(getApplicationContext(), "Oturum Açık", Toast.LENGTH_SHORT).show();
        }




        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=et1.getText().toString();
                String password=et2.getText().toString();

                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password))
                {
                    loginProgress.setTitle("Oturum Açılıyor");
                    loginProgress.setMessage("Hesabınıza Giriş Yapılıyor Lütfen Bekleyiniz..");
                    loginProgress.show();
                    login_user(email,password);
                }
                else{
                    loginProgress.dismiss();
                    Toast.makeText(getApplicationContext(),"Email veya Şifre Boş Bırakılamaz!",Toast.LENGTH_SHORT).show();

                }

                Intent intent=new Intent(MainActivity.this,AppActivity.class);
                startActivity(intent);
            }
        });

    }

    private void login_user(String email,String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    loginProgress.dismiss();
                    Toast.makeText(getApplicationContext(),"Giriş Başarılı",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,AppActivity.class);
                    startActivity(intent);
                }
                else
                {
                    loginProgress.dismiss();
                    Toast.makeText(getApplicationContext(),"Giriş Yapılamadı!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
