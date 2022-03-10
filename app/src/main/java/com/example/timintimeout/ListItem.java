package com.example.timintimeout;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* this class will call the table timesummary and display it to textview of list_view_template.xml
*/

public class ListItem {

    Connection connect;
    String ConnectionResult="";
    Boolean isSuccess=false;
    ReportActivity reportActivity;

    public List<Map<String,String>>getlist()
    {
        List<Map<String,String>> data = null;
        data = new ArrayList<Map<String,String>>();
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.conclass();
            if (connect !=null)
            {
                //this old query below uses etDate edit text - see Reports activity and xml
               // String query = "SELECT * FROM timesummary WHERE date = '"+ ReportActivity.etDate.getText().toString() +"'";
                String query = "SELECT * FROM timesummary WHERE date = '"+ ReportActivity.tvDatePicker.getText().toString() +"'";
               // String query = "SELECT [empFName],CONVERT(VARCHAR[startTime],108) AS startTime,[endTime],[date]  FROM timesummary WHERE date = '"+ ReportActivity.etDate.getText().toString() +"'";
                //String query = "SELECT empFName,CONVERT(VARCHAR,startTime,108),endTime,date FROM timesummary WHERE date = '"+ ReportActivity.etDate.getText().toString() +"'";
                //String query = "SELECT * FROM timesummary WHERE date = '2022-01-06'";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next())
                {
                    //NOTE: we are calling the textview name from(list_layout_template) and the column name of database in mssql
                    Map<String,String> dtname = new HashMap<String,String>();
                   // dtname.put("timeId",resultSet.getString("timeId"));
                    //dtname.put("empUser",resultSet.getString("empUser"));
                    dtname.put("empFName",resultSet.getString("empFName"));
                    dtname.put("startTime",resultSet.getString("startTime"));
                    dtname.put("endTime",resultSet.getString("endTime"));
                   // dtname.put("duration",resultSet.getString("duration"));
                    dtname.put("date",resultSet.getString("date"));
                    data.add(dtname);
                }
                ConnectionResult="Success";
                isSuccess = true;
                connect.close();
            } else  {
                ConnectionResult="Failed";

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data;
    }
}
