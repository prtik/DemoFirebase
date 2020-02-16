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

public class Main2Activity extends AppCompatActivity {

    EditText et_email_reg,et_pass_reg,et_conform_pass;
    TextView tv_login;
    Button btn_reg;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et_email_reg = findViewById(R.id.edit_name_reg);
        et_pass_reg = findViewById(R.id.edit_pass_reg);
        btn_reg = findViewById(R.id.button_register);
        tv_login = findViewById(R.id.logintext);
        et_conform_pass = findViewById(R.id.conform_pass);
        firebaseAuth = FirebaseAuth.getInstance();

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(i);
            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_email_reg.getText().toString()))
                {
                    et_email_reg.setError("please enter email");
                    return;
                }
                if (TextUtils.isEmpty(et_pass_reg.getText().toString()))
                {
                    et_pass_reg.setError("please enter password");
                    return;
                }
                if (TextUtils.isEmpty(et_conform_pass.getText().toString()))
                {
                    et_conform_pass.setError("enter password again");
                    return;
                }
                if (!et_pass_reg.getText().toString().equals(et_conform_pass.getText().toString()))
                {
                    Toast.makeText(Main2Activity.this,"password not match",Toast.LENGTH_SHORT).show();
                }

                String mStrEmail = et_email_reg.getText().toString();
                String mStrPass = et_pass_reg.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(mStrEmail,mStrPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                        }
                        else {
                            Toast.makeText(Main2Activity.this,"Registration failed",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }
}
