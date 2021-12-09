package com.example.timintimeout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeActivity extends AppCompatActivity {
    EditText etEmpId,etFirstName,etLastName,etBirthdate,etEmail,etPhone,etPerHour,etUserName,etPassword;
    Button btnSave;
    ConnectionClass connectionClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        btnSave = findViewById(R.id.btnSave);
        connectionClass = new ConnectionClass();



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoRegister doRegister = new DoRegister();
                doRegister.execute("");
            }
        });
    }

    private class DoRegister extends AsyncTask<String,String,String> {

        String namestr = etFirstName.getText().toString();
        String userstr = etUserName.getText().toString();
        String passstr = etPassword.getText().toString();
        String z ="";
        boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            if (namestr.trim().equals("") || userstr.trim().equals("") || passstr.trim().equals(""))
                z = "Please enter fields..";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null){
                        z = "Please check your internet connection";
                    } else {
                        String query ="INSERT INTO employee (empId,empFirstName,empUsername,empPassword) VALUES(null,'" +namestr+"','"+userstr+"','"+passstr+"')";

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);

                        z = "Saved successfull";
                        isSuccess = true;
                    }

                } catch (Exception ex) {
                    isSuccess = false;
                    z = "Exceptions" + ex;
                }
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {

            if (isSuccess){
                Toast.makeText(getBaseContext(),""+z, Toast.LENGTH_LONG).show();
            }
        }
    }
}