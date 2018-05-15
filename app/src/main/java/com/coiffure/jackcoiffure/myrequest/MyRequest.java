package com.coiffure.jackcoiffure.myrequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.coiffure.jackcoiffure.Service;
import com.coiffure.jackcoiffure.globalVariables.Global;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

        public class MyRequest {

         private Context context;
         private RequestQueue queue;

            public MyRequest(Context context, RequestQueue queue) {
                this.context = context;
                this.queue = queue;
            }

           public void register(final String pseudo, final String email, final String password, final String password2, final RegisterCallback callback){

                        //String url = "http://5.196.213.78/jack_coiffure/espacemembre/register.php";
                        //String url = Global.ip_serveur+"espacemembre/register.php";
                        String url = Global.ip_serveur_EM+"register.php";

                        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Map<String, String> errors = new HashMap<>();

                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if (!error)
                    {
                        callback.onSuccess("vous êtes bien inscrit");
                    }else{
                        JSONObject messages = json.getJSONObject("message");

                        if (messages.has("pseudo")){
                            errors.put("pseudo",messages.getString("pseudo"));
                        }
                        if (messages.has("email")){
                            errors.put("email",messages.getString("email"));
                        }
                        if (messages.has("password")){
                            errors.put("password",messages.getString("password"));
                        }

                        callback.inputErrors(errors);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    callback.onError("Impossible de se connecter");
                } else if (error instanceof VolleyError) {
                    callback.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String, String> map = new HashMap<>();
                                map.put("pseudo", pseudo);
                                map.put("email", email);
                                map.put("password", password);
                                map.put("password2", password2);

                                return map;
                            }
          };
          queue.add(request);
        }

        public interface  RegisterCallback {
           void onSuccess(String message);
           void inputErrors(Map<String,String> errors);
           void onError(String message);
        }



        public void connexion(final String pseudo, final String password, final LoginCallback callback){
            //String url = "http://192.168.1.35/espacemembre/login.php";
            //String url = "http://192.168.1.26/espacemembre/login.php";
            //String url = Global.ip_serveur+"espacemembre/login.php";
            String url = Global.ip_serveur_EM+"login.php";

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    JSONObject json= null;
                    try {
                        json = new JSONObject(response);
                        Boolean error= json.getBoolean("error");

                        if (!error){
                            String id= json.getString("id");
                            String pseudo= json.getString("pseudo");
                            callback.onSuccess(id,pseudo);

                        }else{
                            callback.onError(json.getString("message"));
                        }

                    } catch (JSONException e) {
                        callback.onError("Une erreur s'est produite");
                        e.printStackTrace();
                    }




                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error instanceof NetworkError) {
                        callback.onError("Impossible de se connecter");
                    } else if (error instanceof VolleyError) {
                        callback.onError("Une erreur s'est produite");
                    }
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> map = new HashMap<>();
                    map.put("pseudo", pseudo);
                    map.put("password", password);


                    return map;
                }
            };
            queue.add(request);


        }

        public interface LoginCallback{
                void onSuccess(String id, String pseudo);
                void onError(String message);
        }


        /*public void getServices() {

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
        }*/

    }