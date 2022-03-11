package com.example.timintimeout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class EmployeeActivity extends AppCompatActivity {
    EditText etEmpId,etFirstName,etLastName,etBirthdate,etEmail,etPhone,etPerHour,etUserName,etPassword;
    Button btnSave, btnUpdate, btnDelete;
    ImageButton imbtnHome;
    //ConnectionClass connectionClass;
    //ConnectionHelper connectionHelper;
    Connection connection;
    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hideNavigationBar(); //this will hide nav bar
        getSupportActionBar().hide(); //this will hide the title of my proj.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        etEmpId = findViewById(R.id.etEmpId);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etBirthdate = findViewById(R.id.etBirthdate);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPerHour = findViewById(R.id.etPerHour);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        imbtnHome = findViewById(R.id.imbtnHome);
        btnSave = findViewById(R.id.btnSave);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        //connectionHelper = new ConnectionHelper();
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        ConnectionHelper connectionHelper = new ConnectionHelper();



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DoRegister doRegister = new DoRegister();
//                doRegister.execute("");
               // Connection connection = connectionClass();
            if (TextUtils.isEmpty(etFirstName.getText().toString()) || TextUtils.isEmpty(etUserName.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please input required data", Toast.LENGTH_LONG).show();
            } else {
                connection = connectionHelper.conclass();
                try {
                    if (connection !=null){
                        //String sqlinsert ="INSERT INTO employee VALUES ('"+ etEmpId.getText().toString()+"','"+ etFirstName.getText().toString() +"','"+etLastName.getText().toString()+"','"+etBirthdate.getText().toString()+"','"+etEmail.getText().toString()+"','"+etPhone.getText().toString()+"','"+etPerHour.getText().toString()+"','"+etUserName.getText().toString()+"','"+etPassword.getText().toString()+"')";
                        String sqlinsert ="INSERT INTO employee  ([empFirstName],[empLastName],[empBirthdate],[empEmail],[empPhone],[empPerHour],[empUser],[empPassword]) VALUES ('"+ etFirstName.getText().toString() +"','"+etLastName.getText().toString()+"','"+etBirthdate.getText().toString()+"','"+etEmail.getText().toString()+"','"+etPhone.getText().toString()+"','"+etPerHour.getText().toString()+"','"+etUserName.getText().toString()+"','"+etPassword.getText().toString()+"')";
                        Toast.makeText(getApplicationContext(), "Added Successful", Toast.LENGTH_LONG).show();
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlinsert);
                    }
                }  catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                }
            }


            } //end
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Connection connection = connectionClass();
                try {
                    if (connection !=null){
                        //String sqlinsert ="INSERT INTO employee VALUES ('"+ etEmpId.getText().toString()+"','"+ etFirstName.getText().toString() +"','"+etLastName.getText().toString()+"','"+etBirthdate.getText().toString()+"','"+etEmail.getText().toString()+"','"+etPhone.getText().toString()+"','"+etPerHour.getText().toString()+"','"+etUserName.getText().toString()+"','"+etPassword.getText().toString()+"')";
                        String sqlinsert ="UPDATE employee  SET empFirstName='"+ etFirstName.getText().toString() +"',empLastName='"+etLastName.getText().toString()+"',empBirthdate='"+etBirthdate.getText().toString()+"',empEmail='"+etEmail.getText().toString()+"',empPhone='"+etPhone.getText().toString()+"',empPerHour='"+etPerHour.getText().toString()+"',empUser='"+etUserName.getText().toString()+"',empPassword='"+etPassword.getText().toString()+"' WHERE empId ='"+ etEmpId.getText().toString() +"'";
                        Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_LONG).show();
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlinsert);

                    }
                }  catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Connection connection = connectionClass();
                try {
                    if (connection !=null){
                        //String sqlinsert ="INSERT INTO employee VALUES ('"+ etEmpId.getText().toString()+"','"+ etFirstName.getText().toString() +"','"+etLastName.getText().toString()+"','"+etBirthdate.getText().toString()+"','"+etEmail.getText().toString()+"','"+etPhone.getText().toString()+"','"+etPerHour.getText().toString()+"','"+etUserName.getText().toString()+"','"+etPassword.getText().toString()+"')";
                        String sqlDelete ="DELETE employee where empId = '"+ etEmpId.getText().toString() +"'";
                        Toast.makeText(getApplicationContext(), "Deleted Successful", Toast.LENGTH_LONG).show();
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlDelete);

                        //Toast.makeText(getApplicationContext(), "Deleted Successful", Toast.LENGTH_LONG).show();
                    }
                }  catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                }
            }
        });

        imbtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        etBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(
                        EmployeeActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog1.show();

            }

        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = year + "-" +month+ "-" + dayOfMonth;
                etBirthdate.setText(date);
            }
        };
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

//    private class DoRegister extends AsyncTask<String,String,String> {
//
//        String namestr = etFirstName.getText().toString();
//        String userstr = etUserName.getText().toString();
//        String passstr = etPassword.getText().toString();
//        String z ="";
//        boolean isSuccess = false;
//
//        @Override
//        protected void onPreExecute() {
//            //super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            if (namestr.trim().equals("") || userstr.trim().equals("") || passstr.trim().equals(""))
//                z = "Please enter fields..";
//            else
//            {
//                try {
//                    Connection con = connectionClass.CONN();
//                    if (con == null){
//                        z = "Please check your internet connection";
//                    } else {
//                        String query ="INSERT INTO employee (empId,empFirstName,empUsername,empPassword) VALUES(null,'" +namestr+"','"+userstr+"','"+passstr+"')";
//
//                        Statement stmt = con.createStatement();
//                        stmt.executeUpdate(query);
//
//                        z = "Saved successfull";
//                        isSuccess = true;
//                    }
//
//                } catch (Exception ex) {
//                    isSuccess = false;
//                    z = "Exceptions" + ex;
//                }
//            }
//            return z;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//
//            if (isSuccess){
//                Toast.makeText(getBaseContext(),""+z, Toast.LENGTH_LONG).show();
//            }
//        }
//    }


//    @SuppressLint("NewApi")//
//    public Connection connectionClass(){
//        Connection con = null;
//        String ip="10.0.0.106", port="50379", username="sa",password="01Password", databasename="timetrack";
//        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(tp);
//        try {
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
//            String connectionUrl="jdbc:jtds:sqlserver://"+ip+":"+port+";databasename="+databasename+";User="+username+";password="+password+";";
//            con = DriverManager.getConnection(connectionUrl);
//        }
//        catch (Exception exception){
//            Log.e("Error",exception.getMessage());
//        }
//        return  con;
//    }
}