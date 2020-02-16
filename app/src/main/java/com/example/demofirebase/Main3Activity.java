package com.example.demofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main3Activity extends AppCompatActivity {
Button logout_btn;
TextView current_user_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        logout_btn = findViewById(R.id.logout);
        current_user_text = findViewById(R.id.current_user);

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        current_user_text.setText(mUser.getEmail());



        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i =new Intent(Main3Activity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
