package com.coiffure.jackcoiffure;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.coiffure.jackcoiffure.globalVariables.Global;
import com.coiffure.jackcoiffure.myrequest.MyRequest;

import java.util.ArrayList;


public class ServiceList extends AppCompatActivity {

    private ListView service_list;
    private RequestQueue mQueue;
    private ArrayList<String> data;

    private MyRequest request;

    private ProgressBar pb_loader;
    private String items[];
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        service_list = findViewById(R.id.service_list);

        handler = new Handler();

        mQueue = Volley.newRequestQueue(this);
        data = new ArrayList<>();
        pb_loader = findViewById(R.id.pb_loader);

        pb_loader.setVisibility(View.VISIBLE);

        getServices();

        //items = new String[]{"Coupe classique", "Coupe barbe", "Coupe femme", "Couleur"};

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ServiceList.this, android.R.layout.simple_list_item_1, data);
                service_list.setAdapter(adapter);
                pb_loader.setVisibility(View.GONE);
            }
        }, 2000);

        service_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Toast.makeText(ServiceList.this, data.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), TimeTable.class);
                intent.putExtra("service", data.get(position));
                startActivity(intent);
                finish();
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

                                data.add(name + "\n" + description + "\n" + price+"€");
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

