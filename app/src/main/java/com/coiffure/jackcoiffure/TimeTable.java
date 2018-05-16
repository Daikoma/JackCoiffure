package com.coiffure.jackcoiffure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TimeTable extends AppCompatActivity {

    private TextView textView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        textView = findViewById(R.id.textView);
        String service = getIntent().getStringExtra("service");
        textView.setText(service);

        


    }
}
