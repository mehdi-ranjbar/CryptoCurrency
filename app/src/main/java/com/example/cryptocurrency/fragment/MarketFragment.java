package com.example.cryptocurrency.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.RecoverySystem;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class MarketFragment extends Fragment {

    RecyclerView recyclerView;
    List<CryptoCurrency> currencyList;
    MarketAdapter adapter;
    SpinKitView kitView;
    //EditText search;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_market , container , false);
        getMarketData();
        castViews(view);
        //searchCurrency();
        return view;
    }

    private void castViews(View view) {
        recyclerView = view.findViewById(R.id.currencyRecyclerView);
        adapter = new MarketAdapter(getContext() , currencyList , "Market");
        kitView = view.findViewById(R.id.spinKitView);
        //search = view.findViewById(R.id.searchView);
        searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<CryptoCurrency> currencies = new ArrayList<>();
                for (CryptoCurrency currency1: currencyList){
                    String name = currency1.getName().toLowerCase();
                    String sym = currency1.getSymbol().toLowerCase();
                    //newText.toLowerCase() == name || newText.toLowerCase() == sym
                    //currency1.getName().toLowerCase().contains(newText.toLowerCase()) || currency1.getSymbol().toLowerCase().contains(newText.toLowerCase())
                    if (name.contains(newText.toLowerCase()) ||
                            sym.contains(newText.toLowerCase())){
                        currencies.add(currency1);
                    }
                }
                if (currencies.isEmpty()){
                    adapter.clearList();
                }
                else {
                    adapter.updateData(currencies);
                }
                return true;
            }
        });
        kitView.setVisibility(View.GONE);

    }

    public void getMarketData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        currencyList = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, TopLossGainFragment.URL_DATA, null, new Response.Listener<JSONObject>() {
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

                    recyclerView.setAdapter(adapter);
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

//    public void searchCurrency(){
//
//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                String searchText = editable.toString().toLowerCase();
//                ArrayList<CryptoCurrency> list = new ArrayList<>();
//                for (CryptoCurrency currency : currencyList){
//                    String name =  currency.getName().toLowerCase();
//                    String sym = currency.getSymbol().toLowerCase();
//                    if (name == searchText || sym == searchText){
//                        list.add(currency);
//                    }
//                }
//                Log.i("SEARCHLIST" , list + "");
//                adapter.updateData(list);
//                //adapter.updateData(list);
//            }
//        });

//    }
}