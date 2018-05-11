package com.coiffure.jackcoiffure;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.coiffure.jackcoiffure.myrequest.MyRequest;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout til_pseudo_log, til_password_log;
    private Button btn_send;
    private RequestQueue queue;
    private MyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        til_pseudo_log = (TextInputLayout) findViewById(R.id.til_pseudo_log);
        til_password_log = (TextInputLayout) findViewById(R.id.til_password_log);
        btn_send = (Button) findViewById(R.id.btn_send);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pseudo= til_pseudo_log.getEditText().getText().toString().trim();
                String password= til_password_log.getEditText().getText().toString().trim();
                if (pseudo.length()>0 && password.length()>0) {
                    request.connexion(pseudo, password, new MyRequest.LoginCallback() {
                        @Override
                        public void onSuccess(String id, String pseudo) {
                            Intent intent=new Intent(getApplicationContext(), HomePage.class);
                            startActivity(intent);
                            finish();

                        }

                        @Override
                        public void onError(String message) {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });

        }
    }


