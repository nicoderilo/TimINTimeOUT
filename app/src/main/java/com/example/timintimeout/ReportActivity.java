package com.example.timintimeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class ReportActivity extends AppCompatActivity {
    Button btnLoad, btnSend;
    public EditText etSendTo;
    public EditText etSubject;
    public static EditText etDate;
    ListView lvSummary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //this will hide the title of my proj.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        btnLoad = findViewById(R.id.btnLoad);
        btnSend = findViewById(R.id.btnSend);
        etSendTo = findViewById(R.id.etSendTo);
        etSubject = findViewById(R.id.etSubject);
        etDate = findViewById(R.id.etDate);
        lvSummary = findViewById(R.id.lvSummary);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{etSendTo.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT,etSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,etSubject.getText().toString());
                intent.setData(Uri.parse("mailto:"));
                if (intent.resolveActivity(getPackageManager()) !=null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(ReportActivity.this, "no application", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    SimpleAdapter ad;
    public void  GetData(View v)
    {
        ListView lvSummary = (ListView) findViewById(R.id.lvSummary);

        List<Map<String,String>> MyDataList = null;
        ListItem MyData = new ListItem();
        MyDataList = MyData.getlist();

        String[] Fromw = {"timeId","empUser","empFName","startTime","endTime","duration","date"};
        int[] Tow = {R.id.tvTimeId,R.id.tvEmpUser,R.id.tvEmpFName,R.id.tvStartTime,R.id.tvEndTime,R.id.tvDuration,R.id.tvDate};
        ad= new SimpleAdapter(ReportActivity.this,MyDataList,R.layout.list_layout_template,Fromw,Tow);
        lvSummary.setAdapter(ad);
    }



}