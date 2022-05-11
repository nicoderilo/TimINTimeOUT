package com.example.timintimeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimeInOutActivity extends AppCompatActivity {
    Button btnTimeIn, btnTimeOut;
    ImageButton imbtnIn,imbtnOut,imgbtnHome;
    public TextView tvNameDisplay;
    TextClock tcTime,tcDate;
    EditText etTest;
    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        getSupportActionBar().hide(); //this will hide the title of my proj.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_in_out);
        tcTime = findViewById(R.id.tcTime);
        tcDate = findViewById(R.id.tcDate);
//        btnTimeIn = findViewById(R.id.btnTimeIn);
//        btnTimeOut = findViewById(R.id.btnTimeOut);
        etTest = findViewById(R.id.etTest);
        imbtnIn = findViewById(R.id.imbtnIn);
        imbtnOut = findViewById(R.id.imbtnOut);
        imgbtnHome = findViewById(R.id.imgbtnHome);
        tvNameDisplay = findViewById(R.id.tvNameDisplay);

        hideNavigationBar();
        tvNameDisplay.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

            }
        });
        //---------------------test codes


//
//        Calendar today = Calendar.getInstance();
//        today.set(Calendar.HOUR_OF_DAY, 2);
//        today.set(Calendar.MINUTE, 0);
//        today.set(Calendar.SECOND, 0);
//
//// every night at 2am you run your task
//        Timer timer = new Timer();
//        timer.schedule(new YourTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period: 1 day

//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 03);
//        calendar.set(Calendar.MINUTE, 30);
//        calendar.set(Calendar.SECOND, 0);
//
//        Date alarmTime = calendar.getTime();
//
//        Timer _timer = new Timer();
//        _timer.schedule(testRun(), alarmTime);

        //Date currentTime = Calendar.getInstance().getTime();
        String testtime = tcTime.getText().toString();
        String sendtime = "19:41:00";
        String sendtime2 = "02:45:00";
        String testDate = "24-Apr-2022";




        //if (testDate.equals("24-Apr-2022")) {
          // Toast.makeText(TimeInOutActivity.this, "time test", Toast.LENGTH_LONG).show();
        //}
        //refresh
//        final Handler handler = new Handler();
//        Runnable refresh = new Runnable() {
//            @Override
//            public void run() {
//                // data request
//                String currentTime2 = new SimpleDateFormat("HH", Locale.getDefault()).format(new Date());
//               String testtctime = tcTime.getText().toString();
//                etTest.setText(currentTime2);
//                if (tvNameDisplay.equals("N A M E")){
//                    Toast.makeText(TimeInOutActivity.this, "time test tctime", Toast.LENGTH_LONG).show();
//                }
//                //Toast.makeText(TimeInOutActivity.this, "test 1sec", Toast.LENGTH_SHORT).show();
//                if (tvNameDisplay.equals(sendtime)) {
//                    Toast.makeText(TimeInOutActivity.this, "time test", Toast.LENGTH_SHORT).show();
//
//                    //start
//                    Calendar now = new GregorianCalendar();
//                    String am_pm;
//                    int hour = now.get(Calendar.HOUR);
//                    int minute = now.get(Calendar.MINUTE);
//                    if (now.get(Calendar.AM_PM) == 0){
//                        am_pm = "AM";
//                    }
//                    else{
//                        am_pm = "PM";
//                    }
//                    String time = hour + ":" + minute + " " + am_pm;
//                    String Scan_hour = "12:23 AM";
//                    System.out.println(time + "--" + Scan_hour);
//                    if (time.equals(Scan_hour)){
//                        Toast.makeText(TimeInOutActivity.this, "time test", Toast.LENGTH_SHORT).show();
//                        System.out.println("Time matchs");
//                    }
//                    else{
//                        System.out.println("Time Doesn't Match");
//                    }//end
//                }
//                handler.postDelayed(this, 0);
//            }
//        };
//        handler.postDelayed(refresh, 0);
//        //‐‐------------------------------
//        String texttime = etTest.getText().toString().trim();
//        String texttime2 = "04";
//
//
//        String tcdatetext = tcDate.getText().toString();
//
//        if (texttime.contains("04")){
//            Toast.makeText(TimeInOutActivity.this, "time test tctime", Toast.LENGTH_LONG).show();
//        }
        //---------------------test codes end
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

//    public TimerTask testRun(){
//       // String testrunning = tcTime.getText().toString();
//        //if (testrunning.equals("12:18:00")) {
//        tvNameDisplay.setText("Gumana");
//            Toast.makeText(this, "testing lng", Toast.LENGTH_LONG).show();
//        //}
//
//        return null;
//    }


}