package com.example.cryptocurrency.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cryptocurrency.models.CryptoCurrency;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiUtilities {

    private Context context;
    private static final String URL_DATA = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing?start=1&limit=500";
    public ApiUtilities(Context context){
        this.context = context;
    }

    public void getData(){
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL_DATA, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(context, "connect to web", Toast.LENGTH_SHORT).show();
//                List<CryptoCurrency> currencyList = new ArrayList<CryptoCurrency>();
//                try {
//                    JSONArray jsonArray = response.getJSONObject(0).getJSONArray("cryptoCurrencyList");
//                    for (int i = 0 ; i < jsonArray.length() ; i++){
//                        CryptoCurrency currency = new CryptoCurrency();
//                        JSONObject object = jsonArray.getJSONObject(i);
//                        currency.setId(object.getInt("id"));
//                        currency.setName(object.getString("name"));
//                        currency.getQuotes().get(0).setPercentChange24h(object.getJSONArray("quotes").getJSONObject(0).getDouble("percentChange24h"));
//                        currencyList.add(currency);
//                        Log.i("VOLLEY_TEST" , currencyList+ "");
//                    }
//
//
//                }
//                catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }

    public void getData2(){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_DATA, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, "وصلی", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "وصل نیستی", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    public List<CryptoCurrency> getModelTopCurrency(JSONArray array){
        List<CryptoCurrency> currencyList = new ArrayList<CryptoCurrency>();
        try {
            JSONArray jsonArray = array.getJSONObject(0).getJSONArray("cryptoCurrencyList");
            for (int i = 0 ; i < jsonArray.length() ; i++){
                CryptoCurrency currency = new CryptoCurrency();
                JSONObject object = jsonArray.getJSONObject(i);
                currency.setId(object.getInt("id"));
                currency.setName(object.getString("name"));
                currency.getQuotes().get(0).setPercentChange24h(object.getJSONArray("quotes").getJSONObject(0).getDouble("percentChange24h"));
                currencyList.add(currency);
            }
            return currencyList;

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
