package com.coiffure.jackcoiffure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.coiffure.jackcoiffure.globalVariables.Global;

public class HomePage extends AppCompatActivity {

    private Button btn_rdv, btn_logout;
    private SessionManager sessionManager;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        btn_rdv = (Button) findViewById(R.id.btn_rdv);
        sessionManager = new SessionManager(this);
        textView = (TextView) findViewById(R.id.tv_pseudo);
        btn_logout = (Button) findViewById(R.id.btn_logout);

        if (sessionManager.isLogged()){
            String pseudo= sessionManager.getPseudo();
            String id = sessionManager.getId();
            textView.setText(pseudo);
        }

      /*  btn_rdv.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ServiceList.class);
                startActivity(intent);
            }
        });*/

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
                Intent intent = new Intent(getApplicationContext(), Planning.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
