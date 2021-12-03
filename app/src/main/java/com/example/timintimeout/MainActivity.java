package com.example.timintimeout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText etStart, etEnd;
    Button btnDuration, btnTimeOut;
    TextClock textClock1, textClock2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Instant start = Instant.now();
        // time passes
        Instant end = Instant.now();


        etStart = findViewById(R.id.etStart);
        etEnd = findViewById(R.id.etEnd);
        btnDuration = findViewById(R.id.btnDuration);
        btnTimeOut = findViewById(R.id.btnTimeOut);
        textClock1 = findViewById(R.id.textClock1);
        textClock2 = findViewById(R.id.textClock2);

//        etStart.setText(start);

        btnTimeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startTime =  textClock1.getText().toString();
                String endTime = textClock2.getText().toString();

                etStart.setText(startTime);
                etEnd.setText(endTime);

//            Duration timeElapsed = Duration.between(endTime, startTime);

            }
        });

        btnDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //String startTime = etStart.getText().toString();
            //String endTime =etEnd.getText().toString();
           // Duration timeElapsed = Duration.between(etEnd.getText(), etStart.getText());

            }
        });
    }

    public void getCurrentTime(View view) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        //SimpleDateFormat mdformat = new SimpleDateFormat("EEE-MMM-d \n HH:mm:ss");
        String strDate = "Current Time : " + mdformat.format(calendar.getTime());
        display(strDate);


    }

    private void display(String num) {
        TextView textView = (TextView) findViewById(R.id.current_time_view);
        textView.setText(num);
    }




}