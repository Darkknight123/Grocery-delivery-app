package com.example.dashfoods;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //variables
    Animation topAnim,bottomAnim;
    ImageView Image;
    TextView logo,slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_main);

        //Animations

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        topAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //hooks
        Image=findViewById(R.id.imageView);
        slogan=findViewById(R.id.textView);


        Image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);


        int SPLASH_SCREEN = 5000;
        new Handler().postDelayed(() -> {
            Intent intent=new Intent(MainActivity.this, LoginPage.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN);






    }
}