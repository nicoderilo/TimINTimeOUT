package com.example.timintimeout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class ReportActivity extends AppCompatActivity {

    Button btnLoad, btnSend;
    public EditText etSendTo;
    public EditText etSubject,etEmpUserTest;
    LinearLayout llHeader;
    ImageButton imbtnHome,imbtnshrink;
    //public EditText mtSummary;
    //public static EditText etDate;
    public static TextView tvEmpUser;
    ListView lvSummary;
    public static TextView tvDatePicker, tvDate;
    Connection connect;
    String connectionResult="";
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hideNavigationBar(); //this will hide nav bar
        getSupportActionBar().hide(); //this will hide the title of my proj.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        btnLoad = findViewById(R.id.btnLoad);
        btnSend = findViewById(R.id.btnSend);
        etSendTo = findViewById(R.id.etSendTo);
        tvDate = findViewById(R.id.tvDate);
        llHeader = findViewById(R.id.llHeader);
        //etSubject = findViewById(R.id.etSubject);
        //etDate = findViewById(R.id.etDate);
       // mtSummary = findViewById(R.id.mtSummary);
        tvEmpUser = findViewById(R.id.tvEmpUser);
        lvSummary = findViewById(R.id.lvSummary);
        etEmpUserTest = findViewById(R.id.etEmpUserTest);
        tvDatePicker = findViewById(R.id.tvDatePicker);
        imbtnHome = findViewById(R.id.imbtnHome);
        imbtnshrink = findViewById(R.id.imbtnshrink);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        imbtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        imbtnshrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(ReportActivity.this, "test", Toast.LENGTH_SHORT).show();
                // Gets the layout params that will allow you to resize the layout
                ViewGroup.LayoutParams params = llHeader.getLayoutParams();
                if (params.height == 14) {
                    // NOTE: Changes the height and width to the specified *pixels*
                    //http://labs.rampinteractive.co.uk/android_dp_px_calculator/
                    // 135 Idpi = 175dp -IMPORTANT
                    params.height = 135;
                    //params.width = 100;
                    llHeader.setLayoutParams(params);
                    imbtnshrink.setImageResource(R.drawable.shrinkup);
                    Toast.makeText(ReportActivity.this, "test", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ReportActivity.this, "test2", Toast.LENGTH_SHORT).show();
                    // Changes the height and width to the specified *pixels*
                    params.height = 14;
                    //params.width = 100;
                    llHeader.setLayoutParams(params);
                    imbtnshrink.setImageResource(R.drawable.shrinkdown);
                }
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{etSendTo.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT,tvDatePicker.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,tvDatePicker.getText().toString());
                intent.setData(Uri.parse("mailto:"));
                if (intent.resolveActivity(getPackageManager()) !=null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(ReportActivity.this, "no application", Toast.LENGTH_SHORT).show();
                }
               // mtSummary.setText(lvSummary.getAutofillValue().toString());
            }
        });
        tvDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(
                        ReportActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog1.show();
                hideNavigationBar();
            }

        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = year + "-" +month+ "-" + dayOfMonth;
                tvDatePicker.setText(date);
            }
        };

//        etDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        ReportActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int day) {
//                        month = month+1;
//                       // String date = day+"-"+month+"-"+year;
//                        String date = year+"-"+month+"-"+day;
//                        etDate.setText(date);
//                    }
//                },year,month,day);
//                datePickerDialog.show();
//
//            }
//        });
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


    SimpleAdapter ad;
    public void  GetData(View v)
    {
        ListView lvSummary = (ListView) findViewById(R.id.lvSummary);
        //EditText mtSummary = (EditText) findViewById(R.id.mtSummary);

        List<Map<String,String>> MyDataList = null;
        ListItem MyData = new ListItem();
        MyDataList = MyData.getlist();

        String[] Fromw = {"timeId","empUser","empFName","startTime","endTime","duration","date"};
        int[] Tow = {R.id.tvTimeId,R.id.tvEmpUser,R.id.tvEmpFName,R.id.tvStartTime,R.id.tvEndTime,R.id.tvDuration,R.id.tvDate};
        ad= new SimpleAdapter(ReportActivity.this,MyDataList,R.layout.list_layout_template,Fromw,Tow);
        lvSummary.setAdapter(ad);

    }

    public void GetDatatoTextView(View v)
    {
        //EditText mtSummary = (EditText) findViewById(R.id.mtSummary);
        EditText etEmpUserTest = (EditText) findViewById(R.id.etEmpUserTest);
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.conclass();
            if (connect!=null){
               // Toast.makeText(ReportActivity.this, "Connected", Toast.LENGTH_LONG).show();

                String query = "Select * from timesummary";
                Statement smt = connect.createStatement();
                ResultSet rs = smt.executeQuery(query);
                //mtSummary.setText(query);

                while (rs.next())
                {
                   // lvSummary.setText(rs.getString(2));
                    //mtSummary.setText(rs.getString(4));
                  // mtSummary.setText(rs.getString(1) + "'\n'"+ mtSummary.setText(rs.getString(2) );
                    //mtSummary.setText(rs.getString(2));


                }

            } else if (connect==null) {
                Toast.makeText(getApplicationContext(), "NOT Connected", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ex)
        {
            Log.e("ErrorNICO",ex.getMessage());
        }
    }

}