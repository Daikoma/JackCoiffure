package com.coiffure.jackcoiffure;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.coiffure.jackcoiffure.globalVariables.Global;

import java.util.ArrayList;
import java.util.List;


public class ServiceList extends AppCompatActivity {

    private ListView service_list;
    private RequestQueue mQueue;
    private ArrayList<String> data;

    private String items[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        mQueue = Volley.newRequestQueue(this);
        data = new ArrayList<>();

        getServices();
        
        String zizi_string = "" + data.size();
        
        items = new String[] {zizi_string, "caca"};
        /*items = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            items[i] = data.get(i);
        }*/


        service_list = findViewById(R.id.service_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        service_list.setAdapter(adapter);

        service_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(ServiceList.this, data.get(position), Toast.LENGTH_SHORT).show();
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

                                //service_list.append(name + ", " + description + ", " +String.valueOf(price) + " \n\n");
                                //data.add(name + ", " + description + ", " +String.valueOf(price) + " \n\n");
                                data.add(name);
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


    public void makeClickable() {

    }


    /*public void text_clicked(View view) {
        Toast.makeText(getApplicationContext(), separated.get(0), Toast.LENGTH_SHORT).show();
    }*/

}

