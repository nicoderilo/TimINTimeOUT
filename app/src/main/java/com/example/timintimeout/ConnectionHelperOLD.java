package com.example.timintimeout;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelperOLD {
    String classs = "com.mysql.jdbc.Driver";

    String url = "jdbc:mysql://10.0.0.106/timetrack";
    String un = "rocs";
    String password = "rocspassword";


    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Class.forName(classs);

            conn = DriverManager.getConnection(url,un,password);

            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("Error", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("Error", e.getMessage());
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return conn;
    }
}
