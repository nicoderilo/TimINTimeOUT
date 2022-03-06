package com.example.timintimeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    ImageView ivTimeInOut, ivSchedule, ivEmployee, ivReports;
    LinearLayout llClockIn,llEmployee,llSchedule,llReports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        getSupportActionBar().hide(); //this will hide the title of my proj
        hideNavigationBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ivTimeInOut = findViewById(R.id.ivTimeInOut);
        ivSchedule = findViewById(R.id.ivSchedule);
        ivEmployee = findViewById(R.id.ivEmployee);
        ivReports = findViewById(R.id.ivReports);
        llClockIn = findViewById(R.id.llClockIn);
        llEmployee = findViewById(R.id.llEmployee);
        llSchedule = findViewById(R.id.llSchedule);
        llReports = findViewById(R.id.llReports);

        ivTimeInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TimeInOutActivity.class);
                startActivity(intent);
            }
        });
        llClockIn.setOnClickListener(new View.OnClickListener() {
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
        llEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, EmployeeActivity.class);
                startActivity(intent);
            }
        });
        ivSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Disabled", Toast.LENGTH_SHORT).show();
                //startActivity(intent);
            }
        });
        llSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Disabled", Toast.LENGTH_SHORT).show();
                //startActivity(intent);
            }
        });

        ivReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });
        llReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
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