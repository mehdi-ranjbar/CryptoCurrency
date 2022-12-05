package com.example.cryptocurrency.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cryptocurrency.R;
import com.example.cryptocurrency.adapter.MarketAdapter;
import com.example.cryptocurrency.models.CryptoCurrency;
import com.github.ybq.android.spinkit.SpinKitView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class TopLossGainFragment extends Fragment{

    public static final String URL_DATA = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing?start=1&limit=100";
    List<CryptoCurrency> currencyList;
    List<CryptoCurrency> list;
    RecyclerView recyclerView;
    MarketAdapter marketAdapter;
    SpinKitView kitView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_loss_gain, container, false);
        //
        getMarketData();
        CastView(view);
        kitView.setVisibility(View.GONE);
        //
        return view;
    }


    private void CastView(View view) {
        recyclerView = view.findViewById(R.id.topGainLoseRecyclerView);
        marketAdapter = new MarketAdapter(view.getContext() , list , "TopLoss");
        kitView = view.findViewById(R.id.spinKitView10);
    }

    public void getMarketData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        currencyList = new ArrayList<>();
        list = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_DATA, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONObject("data").getJSONArray("cryptoCurrencyList");
                    for (int i = 0 ; i <jsonArray.length() ; i++){
                        CryptoCurrency currency = new CryptoCurrency();
                        JSONObject object = jsonArray.getJSONObject(i);
                        //
                        currency.setId(object.getInt("id"));
                        currency.setName(object.getString("name"));
                        currency.setSymbol(object.getString("symbol"));
                        currency.setPrice(object.getJSONArray("quotes").getJSONObject(0).getDouble("price"));
                        currency.setPercentChange24h(object.getJSONArray("quotes").getJSONObject(0).getDouble("percentChange24h"));
                        currencyList.add(currency);

                    }
                    Collections.sort(currencyList);
                    Log.i("DILIDILI" , currencyList + "");
                    int key = getArguments().getInt("position");

                    if (key == 4) {
                        for (int k = 0 ; k < 10 ; k++){
                            list.add(currencyList.get(k));
                        }
                    }
                    if (key == 3){
                        int size = currencyList.size();
                        for (int j = size-1 ; j > size-11  ; j--){
                            list.add(currencyList.get(j));
                            Log.i("JANElIST" , list + "");
                        }
                    }
                    recyclerView.setAdapter(marketAdapter);
                    //Toast.makeText(getContext(), "وصلی", Toast.LENGTH_SHORT).show();
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(request);
    }



}