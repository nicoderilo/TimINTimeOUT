//if statement in OK button 12:57 am Dec3
package com.example.timintimeout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText etStart, etEnd,etTimeMode;
    Button btnDuration, btnTimeOut,btnOk,btnErase,btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    TextClock textClock1, textClockDate;
    TextView current_time_view,tvDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Instant start = Instant.now();
        // time passes
        Instant end = Instant.now();

        current_time_view = findViewById(R.id.current_time_view);
        tvDuration = findViewById(R.id.tvDuration);
        etStart = findViewById(R.id.etStart);
        etEnd = findViewById(R.id.etEnd);
        etTimeMode = findViewById(R.id.etTimeMode);
        btnDuration = findViewById(R.id.btnDuration);
        btnTimeOut = findViewById(R.id.btnTimeOut);
        btnOk = findViewById(R.id.btnOk);
        btnErase = findViewById(R.id.btnErase);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        textClock1 = findViewById(R.id.textClock1);
        textClockDate = findViewById(R.id.textClockDate);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        //SimpleDateFormat mdformat = new SimpleDateFormat("EEE-MMM-d \n HH:mm:ss");
        //String strDate = "Current Time : " + mdformat.format(calendar.getTime());
        String strDate = mdformat.format(calendar.getTime());
        display(strDate);

        btnTimeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startTime =  current_time_view.getText().toString();
                String endTime = current_time_view.getText().toString();
                etStart.setText(startTime);
                etEnd.setText(endTime);
//            Duration timeElapsed = Duration.between(endTime, startTime);
                //--------------------------------------
             //String startTime =  current_time_view.getText().toString();
             //String endTime = current_time_view.getText().toString();



            }
        });
        //btnDuration.
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etTimeMode.getText().toString() == "TimeIN") {
                  //save or insert start time, date,emp name,emp code,hours,minutes
                    tvDuration.setText("test");

                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                    try {
                        Date time1 = simpleDateFormat.parse(etStart.getText().toString());
                        Date time2 = simpleDateFormat.parse(etEnd.getText().toString());

                        // Calculating the difference in milliseconds
                        long differenceInMilliSeconds = Math.abs(time2.getTime() - time1.getTime());
                        // Calculating the difference in Hours
                        long differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;
                        System.out.println(differenceInHours);
                        System.out.println(differenceInMilliSeconds);
                        tvDuration.setText(differenceInHours + "");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }


            } //BtnOk - end
        });
    }

//    public void getCurrentTime(View view) {
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
//        //SimpleDateFormat mdformat = new SimpleDateFormat("EEE-MMM-d \n HH:mm:ss");
//        //String strDate = "Current Time : " + mdformat.format(calendar.getTime());
//        String strDate = mdformat.format(calendar.getTime());
//        display(strDate);
//
//
//    }

    private void display(String num) {
        TextView textView = (TextView) findViewById(R.id.current_time_view);
        textView.setText(num);
    }




}