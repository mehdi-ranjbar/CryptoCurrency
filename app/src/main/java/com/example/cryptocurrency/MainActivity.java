package com.example.cryptocurrency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.example.cryptocurrency.api.ApiUtilities;
import com.example.cryptocurrency.fragment.DetailsFragment;
import com.example.cryptocurrency.fragment.HomeFragment;
import com.example.cryptocurrency.fragment.MarketFragment;
import com.example.cryptocurrency.fragment.WatchListFragment;
import com.example.cryptocurrency.models.CryptoCurrency;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayDeque;
import java.util.Deque;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bnv;
    Deque<Integer> currencyDeque;
    boolean flag = true;
    HomeFragment homeFragment;
    MarketFragment marketFragment;
    WatchListFragment watchListFragment;
    //
    private final String SIMPLE_FRAGMENT_TAG = "myfragmenttag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //
        initViews();
        currencyDeque.push(R.id.homeFragment);
        loadFragment(new HomeFragment());
        bnv.setSelectedItemId(R.id.homeFragment);



    }

    public void initViews(){
        bnv = findViewById(R.id.bottomNavigationView);
        homeFragment = new HomeFragment();
        marketFragment = new MarketFragment();
        watchListFragment = new WatchListFragment();
        currencyDeque = new ArrayDeque<>(3);
        bnv.setOnNavigationItemSelectedListener(this);
    }

    public boolean loadFragment (Fragment fragment){

        if (fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment ).commit();
        }
        return true;
    }


    private Fragment getFragment(int itemId) {
        switch (itemId){

            case R.id.homeFragment:
                bnv.getMenu().getItem(0).setChecked(true);
                return homeFragment;

            case R.id.marketFragment:
                bnv.getMenu().getItem(1).setChecked(true);
                return marketFragment;

            case R.id.watchListFragment:
                bnv.getMenu().getItem(2).setChecked(true);
                return watchListFragment;
        }
        bnv.getMenu().getItem(0).setChecked(true);
        return new HomeFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (currencyDeque.contains(id)) {
            if (id == R.id.homeFragment){
                if (currencyDeque.size()!=1){
                    if (flag){
                        currencyDeque.addFirst(R.id.homeFragment);
                        flag = false;
                    }
                }
            }
            currencyDeque.remove(id);
        }
        currencyDeque.push(id);
        loadFragment(getFragment(item.getItemId()));
        return true;
    }



    @Override
    public void onBackPressed() {

            currencyDeque.pop();
            if (!currencyDeque.isEmpty()){
                loadFragment(getFragment(currencyDeque.peek()));
            }
            else {
                finish();
            }


        }
}