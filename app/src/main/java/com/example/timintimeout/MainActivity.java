//if statement in OK button 12:57 am Dec3
package com.example.timintimeout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
    Button btnDuration, btnTimeOut,btnOk,btnErase,btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    TextClock textClock1, textClockDate;
    public TextView current_time_view,tvDuration,tvTimeMode,tvempName,tvTest;
    private Handler mHandler = new Handler();//for my timer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //this will hide the title of my proj
        super.onCreate(savedInstanceState);
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

        myTime.run(); //military time

        String TimeMode = getIntent().getExtras().getString("mode");
        tvTimeMode.setText(TimeMode);



        //----------------------------------------------------------------------------------------------------
        btnTimeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startTime =  current_time_view.getText().toString();
                String endTime = current_time_view.getText().toString();
                etStart.setText(startTime);
                etEnd.setText(endTime);
//            Duration timeElapsed = Duration.between(endTime, startTime);
//                --------------------------------------
//                String startTime =  current_time_view.getText().toString();
//                String endTime = current_time_view.getText().toString();



            }
        });
        //btnDuration.
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save or insert start time, date,emp name,emp code,hours,minutes
               // this code will identify customer's user name tru empUser

                //String testtoast = textClockDate.getText().toString();
               // Toast.makeText(getApplicationContext(), testtoast, Toast.LENGTH_LONG).show();
//                Connection connection = connectionClass();
//                String sqlId = "SELECT timeId FROM timeSummary WHERE empUser = '"+ etEmpCode.getText().toString() +" and date ="+ textClockDate.getText().toString() +"'";
//                Statement st = connection.createStatement();
//                ResultSet rs = st.executeQuery(sqlGet);

                //display timesummary id
                Connection connection = connectionClass();
                try {
                    if (connection !=null){
                       // String sqlGet ="SELECT * FROM employee WHERE empUser = '"+ etEmpCode.getText().toString() +"'";
                       // String sqlId = "SELECT * FROM timeSummary WHERE empUser = '"+ etEmpCode.getText().toString() +" and date ='"+ textClockDate.getText().toString() +"'";
                        String sqlDate = "SELECT convert(varchar,'2021-10-17',105)";
//                        String sqlDate = "SELECT convert(varchar,'"+ textClockDate.getText().toString() +"',105)";
                                        //SELECT * from timesummary where empUser = 2892 and date = '2021-10-17'
                        Statement st = connection.createStatement();
                       // ResultSet rs = st.executeQuery(sqlGet);
                        ResultSet rs = st.executeQuery(sqlDate);
                       // tvTest.setText(sqlDate);
                        while(rs.next()){
                           // tvempName.setText(rs.getString(3));
                            tvTest.setText(rs.getString(1));

                        }
                    } else if (connection ==null){
                    Toast.makeText(getApplicationContext(), "Not connected to server", Toast.LENGTH_LONG).show();
                }
                }  catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                } // //display timesummary id end



                //this code will get the time and display in edit text-------------------------
                if (tvTimeMode.getText().equals("Time IN")) {
                    //String startTime = current_time_view.getText().toString();
                   // etStart.setText(startTime);
                    try {
                        if (connection !=null){
                            String sqlGetById ="SELECT * FROM timeSummary WHERE empUser = '"+ etEmpCode.getText().toString() +"'";
                            Statement st = connection.createStatement();
                            ResultSet rs = st.executeQuery(sqlGetById);

                            while(rs.next()){
                                etStart.setText(rs.getString(4));
                                //address.setText(rs.getString(3));
                            }
                        } else if (connection ==null){
                            Toast.makeText(getApplicationContext(), "Not connected to server", Toast.LENGTH_LONG).show();
                        }
                    }  catch (Exception exception){
                        Log.e("Error",exception.getMessage());
                    } //end
                } else if (tvTimeMode.getText().equals("Time OUT")) {
                    String endTime = current_time_view.getText().toString();
                    etEnd.setText(endTime);
                }//end

                //----------------------
                if (tvTimeMode.getText().equals("Time IN")) {

                   // tvDuration.setText("Time IN");
                    Toast.makeText(getApplicationContext(), "Time in", Toast.LENGTH_LONG).show();

                } else if (tvTimeMode.getText().equals("Time OUT")){


                    //tvDuration.setText("Time OUT");
                    Toast.makeText(getApplicationContext(), "Time OUT", Toast.LENGTH_LONG).show();


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
                } else {
                    Toast.makeText(getApplicationContext(), "if statement not working", Toast.LENGTH_LONG).show();
                }
                //save startTime
                try {
                    if (connection !=null){
                        String sqlinsert ="INSERT INTO timeSummary ([empUser],[empFName],[startTime],[endTime],[duration],[date]) VALUES ('"+ etEmpCode.getText().toString() +"','"+tvempName.getText().toString()+"','"+etStart.getText().toString()+"','"+etEnd.getText().toString()+"','"+tvDuration.getText().toString()+"','"+ textClockDate.getText().toString()+"')";
                        Toast.makeText(getApplicationContext(), "Added Successful", Toast.LENGTH_LONG).show();
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlinsert);



                    } else if (connection ==null){
                        Toast.makeText(getApplicationContext(), "Not connected to server", Toast.LENGTH_LONG).show();
                    }
                }  catch (Exception exception){
                    Log.e("Error",exception.getMessage());

                } ////save startTime end

            } //BtnOk - end


            @SuppressLint("NewApi")
            public Connection connectionClass(){
                Connection con = null;
                String ip="10.0.0.106", port="50379", username="sa",password="01Password", databasename="timetrack";
                StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(tp);
                try {
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    String connectionUrl="jdbc:jtds:sqlserver://"+ip+":"+port+";databasename="+databasename+";User="+username+";password="+password+";";
                    con = DriverManager.getConnection(connectionUrl);
                }
                catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                }
                return  con;
            }


        });
        //String empCode1 = etEmpCode1.getText().toString();
        // String empCode2 = etEmpCode2.getText().toString();
//        String empCode3 = etEmpCode3.getText().toString();
//        String empCode4 = etEmpCode4.getText().toString();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //etEmpCode1.setText("1");

                etEmpCode.setText(etEmpCode.getText().toString() + "1");
//                if (etEmpCode1.getText().toString().equals("-")) {
//                    etEmpCode1.setText("1");
//                } else if (etEmpCode1.getText().toString().equals("1")) {
//                    etEmpCode2.setText("1");
//                }
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
//        //SimpleDateFormat mdformat = new SimpleDateFormat("EEE-MMM-d \n HH:mm:ss");
//        //String strDate = "Current Time : " + mdformat.format(calendar.getTime());
            String strDate = mdformat.format(calendar.getTime());
            display(strDate);
            mHandler.postDelayed(this, 1000);
        }
    };
}