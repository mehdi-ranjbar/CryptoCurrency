package com.example.cryptocurrency.adapter;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatCallback;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.cryptocurrency.MainActivity;
import com.example.cryptocurrency.R;
import com.example.cryptocurrency.fragment.DetailsFragment;
import com.example.cryptocurrency.fragment.HomeFragment;
import com.example.cryptocurrency.models.CryptoCurrency;

import java.util.ArrayList;
import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {

    Context context;
    List<CryptoCurrency> currencyList;
    String wich;


    public MarketAdapter(Context context ,List<CryptoCurrency> currencyList , String wich ){
        this.context = context;
        this.currencyList = currencyList;
        this.wich = wich;
    }

    @NonNull
    @Override
    public MarketAdapter.MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.currency_item_layout, parent , false);
        return new MarketViewHolder(view);
    }

    public void updateData(List<CryptoCurrency> list){
        this.currencyList = list;
        notifyDataSetChanged();

    }

    public void clearList(){
        this.currencyList.clear();
        notifyDataSetChanged();

    }    @Override
    public void onBindViewHolder(@NonNull MarketAdapter.MarketViewHolder holder, int position) {
        CryptoCurrency currency = currencyList.get(position);
        int     id = currency.getId();
        String name = currency.getName() ;
        String symbol = currency.getSymbol();
        Double price = currency.getPrice();
        Double percentChange = currency.getPercentChange24h();



        holder.currencyName.setText(name);
        holder.currencySymbol.setText(symbol);
        holder.currencyPrice.setText( "$"+ String.format("%.02f" ,(price)));

        if (currency.getPercentChange24h()>0){
            holder.changeText.setTextColor(context.getResources().getColor(R.color.gain_Color));
            holder.changeText.setText("+ " + String.format("%.02f" , percentChange) +" %");
        }
        else {
            holder.changeText.setTextColor(context.getResources().getColor(R.color.loss_Color));
            holder.changeText.setText(" "+String.format("%.02f" ,percentChange )+" %");
        }

        Glide.with(context).load("https://s2.coinmarketcap.com/static/img/coins/64x64/"+currency.getId()+".png").
                thumbnail(Glide.with(context) .load(R.drawable.spinner)).
                into(holder.currencyImage);

        Glide.with(context).load("https://s3.coinmarketcap.com/generated/sparklines/web/7d/usd/"+currency.getId()+".png").
                thumbnail(Glide.with(context) .load(R.drawable.spinner)).
                into(holder.chartImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Bundle bundle = new Bundle();
                bundle.putInt("id" , id);
                bundle.putString("name" , name);
                bundle.putString("symbol" , symbol);
                bundle.putDouble("price" , price);
                bundle.putDouble("percentChange" , percentChange);
                bundle.putString("wich" , wich);
                DetailsFragment detailsFragment = new DetailsFragment();
                detailsFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerView, detailsFragment).addToBackStack("Detail").commit();
            }
        });



    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }


    public class MarketViewHolder extends RecyclerView.ViewHolder{

        ImageView currencyImage , chartImage , changeImage;
        TextView currencyName , currencySymbol , currencyPrice , changeText;

        public MarketViewHolder(@NonNull View itemView) {
            super(itemView);
            currencyImage = itemView.findViewById(R.id.currencyImageView);
            currencyName = itemView.findViewById(R.id.currencyNameTextView);
            currencySymbol = itemView.findViewById(R.id.currencySymbolTextView);
            chartImage = itemView.findViewById(R.id.currencyChartImageView);
            currencyPrice = itemView.findViewById(R.id.currencyPriceTextView);
            changeImage = itemView.findViewById(R.id.currencyChangeImageView);
            changeText = itemView.findViewById(R.id.currencyChangeTextView);


        }
    }


}
