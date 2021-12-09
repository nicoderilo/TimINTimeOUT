package com.example.timintimeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TimeInOutActivity extends AppCompatActivity {
    Button btnTimeIn, btnTimeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //this will hide the title of my proj
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_in_out);

        btnTimeIn = findViewById(R.id.btnTimeIn);
        btnTimeOut = findViewById(R.id.btnTimeOut);

        btnTimeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeInOutActivity.this, MainActivity.class);
                intent.putExtra("mode", "Time IN");
                startActivity(intent);


            }
        });

        btnTimeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeInOutActivity.this, MainActivity.class);
                intent.putExtra("mode", "Time OUT");
                startActivity(intent);
            }
        });
    }
}