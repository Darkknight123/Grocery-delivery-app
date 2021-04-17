package com.example.dashfoods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPage extends AppCompatActivity {
    private Retrofit retrofit;
    private  RetrofitInterface retrofitInterface;
    private String BASE_URL="http://192.168.1.104:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page);
        Button btn1=findViewById(R.id.Forget);
        Button btn2=findViewById(R.id.login);
        Button btn3=findViewById(R.id.SignUp);

        retrofit =new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLoginDialog();
            }
        });

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

    private void handleLoginDialog() {
        View view=getLayoutInflater().inflate(R.layout.activity_login_page,null);
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setView(view).show();
        Button btn2=view.findViewById(R.id.login);
        EditText emailEdit=findViewById(R.id.email);
        EditText passwordEdit=findViewById(R.id.Password);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String>map=new HashMap<>();
                map.put("email",emailEdit.getText().toString());
                map.put("Password",passwordEdit.getText().toString());

                Call<LoginResult> call=retrofitInterface.executeLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                        if (response.code()==200){

                            LoginResult result=response.body();

                            AlertDialog.Builder builder1=new AlertDialog.Builder(LoginPage.this);
                            builder1.setTitle(result.getUsername());
                            builder1.setMessage(result.getEmail());

                            builder1.show();

                        }else if (response.code()==404 ){
                            Toast.makeText(LoginPage.this,"wrong credentials",
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(LoginPage.this,t.getMessage(),
                                Toast.LENGTH_LONG).show();

                    }
                });

            }
        });
    }
}