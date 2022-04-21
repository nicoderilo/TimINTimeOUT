package com.example.timintimeout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
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

//import java.io.File;
import com.google.protobuf.compiler.PluginProtos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReportActivity extends AppCompatActivity {

    Button btnLoad, btnSend, btnScreenshot;
    public EditText etSendTo;
    public EditText etSubject, etEmpUserTest;
    LinearLayout llHeader;
    ImageButton imbtnHome, imbtnshrink;
    //public EditText mtSummary;
    //public static EditText etDate;
    public static TextView tvEmpUser;
    ListView lvSummary;
    public static TextView tvDatePicker, tvDate;
    Connection connect;
    String connectionResult = "";
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hideNavigationBar(); //this will hide nav bar
        getSupportActionBar().hide(); //this will hide the title of my proj.
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_report);
        btnLoad = findViewById(R.id.btnLoad);
        btnSend = findViewById(R.id.btnSend);
        btnScreenshot = findViewById(R.id.btnScreenshot);
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

        // verifyStoragePermision(this);

        btnScreenshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // takeScreenshot(getWindow().getDecorView().getRootView(),"result");
                SaveImage();
                //galleryAddPic();
            }
        });

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
                intent.putExtra(Intent.EXTRA_SUBJECT, tvDatePicker.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, tvDatePicker.getText().toString());
                intent.setData(Uri.parse("mailto:"));
                if (intent.resolveActivity(getPackageManager()) != null) {
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
                        , setListener, year, month, day);
                datePickerDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog1.show();
                hideNavigationBar();
            }

        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "-" + month + "-" + dayOfMonth;
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            SaveImage();
        } else
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        
    }


    private void SaveImage() {
        if (!CheckPermission())
            return;

        try {
            String path = Environment.getExternalStorageDirectory().toString() + "/AppName";
            File fileDir = new File(path);
            if (!fileDir.exists())
                fileDir.mkdir();
            String mPath = path + "/Screenshot_" + new Date().getTime() + ".png";

            Bitmap bitmap = screenShot();
            File file = new File(mPath);
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fOut);
            fOut.flush();
            fOut.close();

            Toast.makeText(this, "Image saved successfully", Toast.LENGTH_LONG).show();

            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(path))));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private Bitmap screenShot() {
        View v  = findViewById(R.id.rootView);
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(),v.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    private boolean CheckPermission() {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return false;
        }
        return true;
    }



    /*protected  static File takeScreenshot(View view, String filename) {
        Date date = new Date();
        CharSequence format = DateFormat.getDateTimeInstance().format("yyyy-MM-dd_hh:mm:ss");

        try {
            String dirPath = Environment.getExternalStorageDirectory().toString() + "/rocstime";
            File fileDir = new File(dirPath);
            if (!fileDir.exists()) {
                boolean mkdir = fileDir.mkdir();
            }

            String path = dirPath + "/" + filename + "-" + format + ".jpeg";

            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            File imageFile = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return imageFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        }

        private  static  final  int REQUEST_EXTERNAL_STORAGE=1;
        private  static  String[] PERMISSION_STORAGE = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
    };

        public static void verifyStoragePermision(Activity activity) {
            int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permission!= PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity,
                PERMISSION_STORAGE,
                REQUEST_EXTERNAL_STORAGE);
            }
        }*/

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