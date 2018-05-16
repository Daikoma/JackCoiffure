package com.coiffure.jackcoiffure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.coiffure.jackcoiffure.myrequest.MyRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class TimeTable extends AppCompatActivity {

    private TextView textView;
    private ListView timetable_list;
    private MyRequest request;
    private RequestQueue mQueue;
    private ArrayList<String> data;

    private String items[];
    private String date;

    private String service;
    private String day;
    private String hour;
    private String pseudo;
    private String til_service;
    private String til_day;
    private String hour;
    private String pseudo;

    private SessionManager sessionManager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        mQueue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, mQueue);

        textView = findViewById(R.id.textView);
        service = getIntent().getStringExtra("service");
        String arr[] = service.split("\n");
        service = arr[0];

        sessionManager = new SessionManager(this);
        pseudo = sessionManager.getPseudo();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date day_date = Calendar.getInstance().getTime();
        day = sdf.format(day_date);

        textView.setText(day);

        timetable_list = findViewById(R.id.listView);

        //Crée la liste et la rend clickable.
        mQueue = Volley.newRequestQueue(this);
        data = new ArrayList<>();

        items = new String[] {"9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "14:00", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30"};



        ArrayAdapter<String> adapter = new ArrayAdapter<>(TimeTable.this, android.R.layout.simple_list_item_1, items);
        timetable_list.setAdapter(adapter);

        timetable_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                hour = items[position]+":00";
                Toast.makeText(TimeTable.this, hour, Toast.LENGTH_SHORT).show();

                //Le taff et là et dans MyRequest
                request.take_rdv(service, day, hour, pseudo, new MyRequest.RegisterCallback() {
                    @Override
                    public void onSuccess(String message) {
                        //pb_loader.setVisibility(View.GONE);
                        //Intent intent = new Intent(getApplicationContext(),Planning.class);
                        //startActivity(intent);
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        finish();


                    }

                    @Override
                    public void inputErrors(Map<String, String> errors) {
                        //pb_loader.setVisibility(View.GONE);

                        /*if (errors.get("pseudo") != null) {
                            til_pseudo.setError(errors.get("pseudo"));
                        } else {
                            til_pseudo.setErrorEnabled(false);
                        }
                        if (errors.get("email") != null) {
                            til_email.setError(errors.get("email"));
                        } else {
                            til_email.setErrorEnabled(false);
                        }
                        if (errors.get("password") != null) {
                            til_password.setError(errors.get("password"));
                        } else {
                            til_password.setErrorEnabled(false);
                        }*/

                    }

                    @Override
                    public void onError(String message) {
                        //pb_loader.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

                    }
                });


                    }
        });

    }

}
