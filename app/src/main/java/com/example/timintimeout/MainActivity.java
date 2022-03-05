//if statement in OK button 12:57 am Dec3
/*
* NOTE: Invisible duration,start,end name, date, time in Xml file
* */
package com.example.timintimeout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    EditText etStart, etEnd, etEmpCode,etEmpCode2,etEmpCode3,etEmpCode4;
    Button btnTimeOut,btnOk,btnErase,btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    TextClock textClock1, textClockDate;
    ImageButton imbtnHome;
    public TextView current_time_view,tvDuration,tvTimeMode,tvempName,tvTest;
    private Handler mHandler = new Handler();//for my timer
    Connection connection;

   // ConnectionHelper connectionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //this will hide the title of my proj
        super.onCreate(savedInstanceState);
        hideNavigationBar();
        setContentView(R.layout.activity_main);
        Instant start = Instant.now();
        // time passes
        Instant end = Instant.now();

        current_time_view = findViewById(R.id.current_time_view);
        tvDuration = findViewById(R.id.tvDuration);
        etStart = findViewById(R.id.etStart);
        etEnd = findViewById(R.id.etEnd);
        etEmpCode = findViewById(R.id.etEmpCode);
        tvTimeMode = findViewById(R.id.tvTimeMode);
        tvempName = findViewById(R.id.tvempName);
        tvTest = findViewById(R.id.tvTest);
        //btnTimeOut = findViewById(R.id.btnTimeOut);
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
        imbtnHome = findViewById(R.id.imbtnHome);

        textClock1 = findViewById(R.id.textClock1);
        textClockDate = findViewById(R.id.textClockDate);

        myTime.run(); //military time

        String TimeMode = getIntent().getExtras().getString("mode");
        tvTimeMode.setText(TimeMode);



        //----------------------------------------------------------------------------------------------------
//        btnTimeOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String startTime =  current_time_view.getText().toString();
//                String endTime = current_time_view.getText().toString();
//                etStart.setText(startTime);
//                etEnd.setText(endTime);
//            }
//        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TASK: save or insert start time, date,emp name,emp code,hours,minutes
               // this code will identify customer's user name tru empUser


                //DISPLAY timesummary id - START
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connection = connectionHelper.conclass();
                //Connection connection = connectionClass();
                try {
                    if (connection !=null){

                        Toast.makeText(getApplicationContext(), "connected to server", Toast.LENGTH_SHORT).show();
                    } else if (connection ==null){
                    Toast.makeText(getApplicationContext(), "Not connected to server", Toast.LENGTH_SHORT).show();
                }
                }  catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                } //DISPLAY timesummary id - END



                //this code will get the time and display in edit text - START
                if (tvTimeMode.getText().equals("Time IN")) {
                    Toast.makeText(getApplicationContext(), "Time in", Toast.LENGTH_LONG).show();
                    try {
                        if (connection !=null){

                            String sqlGetByCodeAndDate ="SELECT * FROM timeSummary WHERE empUser = '"+ etEmpCode.getText().toString() +"' and date = '"+ textClockDate.getText().toString() +"'";
                           // String sqlGetByCodeAndDate ="SELECT * FROM timeSummary WHERE empUser = '"+ etEmpCode.getText().toString() +"' and date = '2022-01-02'";

                            Statement st = connection.createStatement();
                            ResultSet rs = st.executeQuery(sqlGetByCodeAndDate);

                            while(rs.next()){
                                etStart.setText(rs.getString(4)); //get timeIn column value if availble
                                tvempName.setText(rs.getString(3)); //get empFName column value if availble

                            }

                            //save startTime - if no timeIn yet or null - START
                            String Time_IN_Value = etStart.getText().toString();
                            if (Time_IN_Value.equals("")) {

                                String sqlGetName ="SELECT * FROM employee WHERE empUser = '"+ etEmpCode.getText().toString() +"'";

                                st = connection.createStatement();
                                rs = st.executeQuery(sqlGetName);

                                while(rs.next()){

                                    tvempName.setText(rs.getString(2)); //get empFName to save

                                }
                                etStart.setText(current_time_view.getText().toString());
                                        String sqlinsert ="INSERT INTO timeSummary ([empUser],[empFName],[startTime],[endTime],[duration],[date]) VALUES ('"+ etEmpCode.getText().toString() +"','"+tvempName.getText().toString()+"','"+etStart.getText().toString()+"','"+etEnd.getText().toString()+"','"+tvDuration.getText().toString()+"','"+ textClockDate.getText().toString()+"')";
                                        Toast.makeText(getApplicationContext(), "Time In Successful", Toast.LENGTH_LONG).show();
                                        st.executeQuery(sqlinsert);
                                Toast.makeText(getApplicationContext(), "Time IN Successful", Toast.LENGTH_SHORT).show();
                            }//save startTime - END


                        } else if (connection ==null){
                            Toast.makeText(getApplicationContext(), "Not connected to server", Toast.LENGTH_LONG).show();
                        }
                    }  catch (Exception exception){
                        Log.e("Error",exception.getMessage());
                    } //end
                } else if (tvTimeMode.getText().equals("Time OUT")) {
                    String endTime = current_time_view.getText().toString();
                    etEnd.setText(endTime);
                    Toast.makeText(getApplicationContext(), "Time OUT", Toast.LENGTH_LONG).show();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                    try {

                        try {
                            //get the value of user with timeIN and display it with name - START
                            String sqlGetByCodeAndDate = "SELECT * FROM timeSummary WHERE empUser = '" + etEmpCode.getText().toString() + "' and date = '" + textClockDate.getText().toString() + "'";
                            Statement st = connection.createStatement();
                            ResultSet rs = st.executeQuery(sqlGetByCodeAndDate);

                            while (rs.next()) {
                                etStart.setText(rs.getString(4)); //get timeIn column value if availble
                                tvempName.setText(rs.getString(3)); //get empFName column value if availble
                                 }
                            } catch(SQLException e){
                                e.printStackTrace();
                            }
                        //get the value of user with timeIN and display it with name - END

                        //computation of Duration - START
                        Date time1 = simpleDateFormat.parse(etStart.getText().toString());
                        Date time2 = simpleDateFormat.parse(etEnd.getText().toString());

                        // Calculating the difference in milliseconds
                        long differenceInMilliSeconds = Math.abs(time2.getTime() - time1.getTime());
                        // Calculating the difference in Hours
                        long differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;
                        System.out.println(differenceInHours);
                        System.out.println(differenceInMilliSeconds);
                        tvDuration.setText(differenceInHours + "");
                        //computation of Duration - END

                        //update table for timeOut using empcode and date
                        String sqlinsert ="UPDATE timeSummary SET endTime ='"+ etEnd.getText().toString() +"',duration='"+tvDuration.getText().toString()+"' WHERE empUser = '"+ etEmpCode.getText().toString() +"' and date = '"+ textClockDate.getText().toString() +"'";
                        Toast.makeText(getApplicationContext(), "Added Successful", Toast.LENGTH_LONG).show();
                        Statement st = connection.createStatement();
                        st.executeQuery(sqlinsert);
                        Toast.makeText(getApplicationContext(), "Time OUT Successful", Toast.LENGTH_SHORT).show();
                    } catch (ParseException | SQLException e) {
                        e.printStackTrace();
                    }

                } //this code will get the time and display in edit text - END

                Intent intent = new Intent(MainActivity.this, TimeInOutActivity.class);
                startActivity(intent);
            } //BtnOk - end

        });

        imbtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmpCode.setText(etEmpCode.getText().toString() + "1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmpCode.setText(etEmpCode.getText().toString() + "2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmpCode.setText(etEmpCode.getText().toString() + "3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmpCode.setText(etEmpCode.getText().toString() + "4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmpCode.setText(etEmpCode.getText().toString() + "5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmpCode.setText(etEmpCode.getText().toString() + "6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmpCode.setText(etEmpCode.getText().toString() + "7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmpCode.setText(etEmpCode.getText().toString() + "8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmpCode.setText(etEmpCode.getText().toString() + "9");
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmpCode.setText(etEmpCode.getText().toString() + "0");
            }
        });

        btnErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmpCode.setText("");
            }
        });

    }

    private void hideNavigationBar() {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
    }


    private void display(String num) {
        TextView textView = (TextView) findViewById(R.id.current_time_view);
        textView.setText(num);
    }

    //this will run my function every 1 sec
    private final Runnable myTime = new Runnable() {
        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
            String strDate = mdformat.format(calendar.getTime());
            display(strDate);
            mHandler.postDelayed(this, 1000);
        }

    };

    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
}