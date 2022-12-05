package com.example.cryptocurrency.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cryptocurrency.R;
import com.example.cryptocurrency.adapter.TopLossGainPagerAdapter;
import com.example.cryptocurrency.adapter.TopMarketAdapter;
import com.example.cryptocurrency.api.ApiUtilities;
import com.example.cryptocurrency.models.CryptoCurrency;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private static final String URL_DATA = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing?start=1&limit=500";
    List<CryptoCurrency> currencyList;
    RecyclerView recyclerView;
    TopMarketAdapter adapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_home, container, false);
        //
        getData();
        CastView(view);
        setUpTabLayout();
        //
        return view;
    }



    public void CastView(View view){
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.contentViewPager);
        recyclerView = view.findViewById(R.id.topCurrencyRecyclerView);
        adapter = new TopMarketAdapter(view.getContext(),currencyList);
    }

    private void setUpTabLayout() {
        TopLossGainPagerAdapter pagerAdapter = new TopLossGainPagerAdapter(getChildFragmentManager());
        TopLossGainFragment lossGainFragment = new TopLossGainFragment();
        TopLossGainFragment lossGainFragment2 = new TopLossGainFragment();
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        bundle.putInt("position" , 3);
        bundle2.putInt("position" , 4);
        lossGainFragment.setArguments(bundle);
        lossGainFragment2.setArguments(bundle2);
        pagerAdapter.addFragment(lossGainFragment, "Top Gain");
        pagerAdapter.addFragment(lossGainFragment2 , "Top Loss");
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void getData(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        currencyList = new ArrayList<CryptoCurrency>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_DATA, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONObject("data").getJSONArray("cryptoCurrencyList");
                    for (int i = 0 ; i < jsonArray.length() ; i++){
                        CryptoCurrency currency = new CryptoCurrency();
                        JSONObject object = jsonArray.getJSONObject(i);
                        currency.setId(object.getInt("id"));
                        currency.setName(object.getString("name"));
                        currency.setPercentChange24h(object.getJSONArray("quotes").getJSONObject(0).getDouble("percentChange24h"));
                        currencyList.add(currency);

                    }

                    recyclerView.setAdapter(adapter);
                }
                catch (JSONException e) {
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