package com.coiffure.jackcoiffure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;

import com.coiffure.jackcoiffure.InputStreamOperations;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;

public class ServiceList extends AppCompatActivity {

    public List service_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        service_list = getServices();
    }

    public static ArrayList<Service> getServices() {

        ArrayList<Service> services = new ArrayList<>();

        try {
            String myurl= "http://192.168.1.23/list_services/getServices.php";

            URL url = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            /*
             * InputStreamOperations est une classe complémentaire:
             * Elle contient une méthode InputStreamToString.
             */
            String result = InputStreamOperations.InputStreamToString(inputStream);

            // On récupère le JSON complet
            JSONObject jsonObject = new JSONObject(result);

            // On récupère le tableau d'objets qui nous concernent
            JSONArray array = new JSONArray(jsonObject.getString("services"));

            // Pour tous les objets on récupère les infos
            for (int i = 0; i < array.length(); i++) {
                // On récupère un objet JSON du tableau
                JSONObject obj = new JSONObject(array.getString(i));

                // On fait le lien Service - Objet JSON
                Service service = new Service();
                service.setName(obj.getString("name"));
                service.setDescription(obj.getString("description"));
                service.setPrice(obj.getInt("price"));
                // On ajoute la personne à la liste
                services.add(service);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // On retourne la liste des personnes
        return services;
    }
}
