package com.example.timintimeout;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

//https://www.youtube.com/watch?v=pe0GmXHAda4
//NOTE: JAR file located at TimINTimeOUT\app\src\main\res
//File > Proj Structure > Dependencies > app > + > Jar dependency > copy the path including the filename.jar > apply

//HOW TO ENABLE TCP IP
// SQL server Conf Manager > SQL Server net config > Protocols for MSSQLSERVER > TCP IP mus be enabled > restart SQL
// server service
//this pc > manage > tcp dynamic ports: get port number here
public class ConnectionHelper {
    Connection con;
    String ip,port,db,un,pass;

    @SuppressLint("NewApi")
    public Connection conclass()
    {
        ip="192.168.0.100"; //rocs, pc inside
       // ip="192.168.0.14"; //rocs POS
        //ip="10.0.0.117";//gaming desktop
        //ip="10.0.0.119";//asus

        port="50379";
        //port="42690";
        //port="50833";//rocs port, pc inside
        db="timetrack";
        un="sa";
        pass="01Password";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL="jdbc:jtds:sqlserver://"+ip+":"+port+";databasename="+db+";User="+un+";password="+pass+";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (Exception exception)
        {
            Log.e("Error",exception.getMessage());
        }

        return connection;
    }

}
