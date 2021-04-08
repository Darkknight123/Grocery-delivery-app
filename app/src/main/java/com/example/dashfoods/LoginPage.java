package com.example.dashfoods;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page);
        Button btn1=findViewById(R.id.Forget);
        Button btn2=findViewById(R.id.login);
        Button btn3=findViewById(R.id.SignUp);

        btn1.setOnClickListener(v -> {
            Intent int1=new Intent(LoginPage.this, Recover.class);
            startActivity(int1);
        });

        btn2.setOnClickListener(v -> {
            Intent int2=new Intent(LoginPage.this, HomePage.class);
            startActivity(int2);
        });

        btn3.setOnClickListener(v -> {
            Intent int3=new Intent(LoginPage.this, SignUp.class);
            startActivity(int3);
        });

    }
}