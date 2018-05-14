package com.coiffure.jackcoiffure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.coiffure.jackcoiffure.InputStreamOperations;
import com.coiffure.jackcoiffure.globalVariables.Global;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;


public class ServiceList extends AppCompatActivity {

    private TextView service_list;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        service_list = findViewById(R.id.service_list);
        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                getServices();
            }
        });
    }

    public void getServices() {

        //String url = "http://192.168.1.23/list_services/getServices.php";
        //String url = Global.ip_serveur+"list_services/getServices.php";
        String url = Global.ip_serveur_LS+"getServices.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("services");

                            for (int i=0; i < jsonArray.length(); i++) {
                                JSONObject service = jsonArray.getJSONObject(i);

                                String name = service.getString("name");
                                String description = service.getString("description");
                                int price = service.getInt("price");

                                service_list.append(name + ", " + description + ", " +String.valueOf(price) + " \n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mQueue.add(request);
    }

}

