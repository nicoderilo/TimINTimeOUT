package com.example.timintimeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class TimeInOutActivity extends AppCompatActivity {
    Button btnTimeIn, btnTimeOut;
    ImageButton imbtnIn,imbtnOut,imgbtnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        getSupportActionBar().hide(); //this will hide the title of my proj.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_in_out);

//        btnTimeIn = findViewById(R.id.btnTimeIn);
//        btnTimeOut = findViewById(R.id.btnTimeOut);
        imbtnIn = findViewById(R.id.imbtnIn);
        imbtnOut = findViewById(R.id.imbtnOut);
        imgbtnHome = findViewById(R.id.imgbtnHome);

        hideNavigationBar();

        imbtnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeInOutActivity.this, MainActivity.class);
                intent.putExtra("mode", "Time IN");
                //intent.putExtra("startTime", );
                startActivity(intent);


            }
        });

        imbtnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeInOutActivity.this, MainActivity.class);
                intent.putExtra("mode", "Time OUT");
                startActivity(intent);
            }
        });

        imgbtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeInOutActivity.this, HomeActivity.class);
                startActivity(intent);


            }
        });
    }



    public void hideNavigationBar() {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
    }
}