package com.example.dashfoods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUp extends AppCompatActivity {
    //variables
    TextInputLayout regName,regUserName,regEmail,regPhoneNo,regPassword;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private Object view;

    protected void onCreate(Bundle savedInstanceState, Object view) {
        this.view = view;
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        // hooks to all xml elements in activity_sign_up.xml
        regName =findViewById(R.id.name);
        regEmail =findViewById(R.id.email);
        regUserName =findViewById(R.id.username);
        regPhoneNo =findViewById(R.id.phone);
        regPassword =findViewById(R.id.Password);

        Button btn= findViewById(R.id.SignUp);
        //save data in firebase on button click
        btn.setOnClickListener(v -> {
            rootNode=FirebaseDatabase.getInstance();
            reference= rootNode.getReference("users");


            reference.setValue("first data storage");
        });

        btn.setOnClickListener(v -> {
            Intent int1=new Intent(SignUp.this,HomePage.class);
            startActivity(int1);
        });



    }

    private Boolean validateName(){
        String val= Objects.requireNonNull(regName.getEditText()).getText().toString();
        if (val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }
        else {
            regName.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateUserName(){
        String val= Objects.requireNonNull(regUserName.getEditText()).getText().toString();
        String noWhiteSpace=getString(R.string.whitespace);
        if (val.isEmpty()){
            regUserName.setError("Field cannot be empty");
            return false;
        }else if (val.length()>=15){
            regUserName.setError("Username too long");
            return false;

        }else if (!val.matches(noWhiteSpace)){
            regUserName.setError("White Spaces are not allowed ");

        }
        else {
            regUserName.setError(null);
            return true;
        }

        return null;
    }
    private Boolean validateEmail(){
        String val= Objects.requireNonNull(regEmail.getEditText()).getText().toString();
        String emailPattern=getString(R.string.values);
        if (val.isEmpty()){
            regEmail.setError("Field cannot be empty");
            return false;
        }else if (val.matches(emailPattern)){
            regEmail.setError("Invalid Email address");
            return false;
        }
        else {
            regEmail.setError(null);

            return true;
        }

    }
    private Boolean validatePhoneNo(){
        String val= Objects.requireNonNull(regPhoneNo.getEditText()).getText().toString();
        if (val.isEmpty()){
            regPhoneNo.setError("Field cannot be empty");
            return false;
        }
        else {
            regPhoneNo.setError(null);
            return true;
        }

    }
    private Boolean validatePassword(){
        String val= Objects.requireNonNull(regPassword.getEditText()).getText().toString();

        if (val.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        }
        else {

            regPassword.setError(null);
            return true;
        }

    }


    //save data in firebase on button click
    public SignUp(View view){
        this.view = view;

        if (!validateName() | !validateUserName() | !validateEmail() | !validatePhoneNo() | !validatePassword()){
            return;
        }
        //get all the values in the string
        String name= Objects.requireNonNull(regName.getEditText()).getText().toString();
        String userName= Objects.requireNonNull(regUserName.getEditText()).getText().toString();
        String email= Objects.requireNonNull(regEmail.getEditText()).getText().toString();
        String phoneNo= Objects.requireNonNull(regPhoneNo.getEditText()).getText().toString();
        String password= Objects.requireNonNull(regPassword.getEditText()).getText().toString();
        UserHelperClass helperClass=new UserHelperClass(name,userName,email,phoneNo,password);
        reference.child(userName).setValue(helperClass);

    }
}