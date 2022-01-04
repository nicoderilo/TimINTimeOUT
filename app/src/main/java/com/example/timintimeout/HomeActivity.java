package com.example.timintimeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    ImageView ivTimeInOut, ivSchedule, ivEmployee, ivReports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //this will hide the title of my proj
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ivTimeInOut = findViewById(R.id.ivTimeInOut);
        ivSchedule = findViewById(R.id.ivSchedule);
        ivEmployee = findViewById(R.id.ivEmployee);
        ivReports = findViewById(R.id.ivReports);

        ivTimeInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TimeInOutActivity.class);
                startActivity(intent);
            }
        });

        ivEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, EmployeeActivity.class);
                startActivity(intent);
            }
        });

        ivReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

    }
}