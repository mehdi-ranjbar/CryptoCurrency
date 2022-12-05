package com.example.cryptocurrency.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cryptocurrency.R;
import com.example.cryptocurrency.models.WatchListModel;

import java.lang.reflect.Array;
import java.util.Map;


public class WatchListFragment extends Fragment {

    SharedPreferences sharedPreferences;
    int id[] = null;



    public WatchListFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watch_list , container , false);

        sharedPreferences= getActivity().getSharedPreferences(DetailsFragment.PREF_NAME , Context.MODE_PRIVATE);


        String s = sharedPreferences.getString("watchListArray","");

        Log.i("asasasasasasasas" , s + "");



        return view;
    }
}