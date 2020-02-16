package com.example.demofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    EditText et_email,et_pass;
    Button btn_login;
    TextView reg_txt;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_email = findViewById(R.id.edit_name_login);
        et_pass = findViewById(R.id.edit_pass_login);
        btn_login = findViewById(R.id.button_login);
        reg_txt = findViewById(R.id.please_reg_txt);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this,Main3Activity.class);
            startActivity(i);
            finish();
        }

        reg_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_email.getText().toString()))
                {
                    et_email.setError("please etnter email");
                    return;
                }
                if (TextUtils.isEmpty(et_pass.getText().toString()))
                {
                    et_pass.setError("please etnter password");
                    return;
                }
                String mStrEmail = et_email.getText().toString();
                String mStrPass = et_pass.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(mStrEmail,mStrPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                            Intent i =new Intent(MainActivity.this,Main3Activity.class);
                            startActivity(i);
                        }else {
                            Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });


    }
}
