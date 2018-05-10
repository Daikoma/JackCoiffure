package com.coiffure.jackcoiffure;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.coiffure.jackcoiffure.myrequest.MyRequest;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_send;
    private TextInputLayout til_pseudo, til_email, til_password, til_password2;
    private ProgressBar pb_loader;
    private RequestQueue queue;
    private MyRequest request;


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

         btn_send = (Button) findViewById(R.id.btn_send);
         pb_loader= (ProgressBar) findViewById(R.id.pb_loader);
         til_pseudo = (TextInputLayout) findViewById(R.id.til_pseudo);
         til_email = (TextInputLayout) findViewById(R.id.til_email);
         til_password = (TextInputLayout) findViewById(R.id.til_password);
         til_password2 = (TextInputLayout) findViewById(R.id.til_password2);



            queue = VolleySingleton.getInstance(this).getRequestQueue();
            request = new MyRequest(this, queue);

            btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            pb_loader.setVisibility(View.VISIBLE);
            String pseudo = til_pseudo.getEditText().getText().toString().trim();
            String email = til_email.getEditText().getText().toString().trim();
            String password = til_password.getEditText().getText().toString().trim();
            String password2 = til_password2.getEditText().getText().toString().trim();
            if (pseudo.length() >0 && email.length() > 0 && email.length() > 0 && email.length() > 0)
            request.register(pseudo, email, password, password2, new MyRequest.RegisterCallback() {
                @Override
                public void onSuccess(String message) {

                }

                @Override
                public void inputErrors(Map<String, String> errors) {

                }

                @Override
                public void onError(String message) {

                }
            });
            }
        });
    }

}