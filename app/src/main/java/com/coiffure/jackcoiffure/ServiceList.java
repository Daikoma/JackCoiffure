/*package com.coiffure.jackcoiffure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.coiffure.jackcoiffure.InputStreamOperations;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;


public class ServiceList extends AppCompatActivity {

    public List service_list;
    public ArrayList<Service> services;

    public ServiceList() {
        this.service_list = null;
        this.services = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        service_list = getServices();
    }

    public static ArrayList<Service> getServices() {

        String url = "http://192.168.1.23/list_services/getServices.php";

        this.services = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject json;
                try {
                    json = new JSONObject(response);
                    JSONArray array = new JSONArray(json.getString("services"));

                    for (int i = 0; i < array.length(); i++) {
                        // On récupère un objet JSON du tableau
                        JSONObject obj = new JSONObject(array.getString(i));

                        // On fait le lien Service - Objet JSON
                        Service service = new Service();
                        service.setName(obj.getString("name"));
                        service.setDescription(obj.getString("description"));
                        service.setPrice(obj.getInt("price"));
                        // On ajoute la personne à la liste
                        this.services.add(service);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {

                } else if (error instanceof VolleyError) {

                }
            }
        });

        return services;
    }
}*/


