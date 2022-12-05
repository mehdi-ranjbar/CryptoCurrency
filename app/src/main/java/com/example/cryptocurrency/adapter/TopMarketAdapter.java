package com.example.cryptocurrency.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.cryptocurrency.R;
import com.example.cryptocurrency.models.CryptoCurrency;

import java.util.ArrayList;
import java.util.List;

public class TopMarketAdapter extends RecyclerView.Adapter<TopMarketAdapter.ViewHolder> {

    Context context;
    List<CryptoCurrency> cryptoCurrencyList;

    public TopMarketAdapter(Context context, List<CryptoCurrency> cryptoCurrencyList) {
        this.context = context;
        this.cryptoCurrencyList = cryptoCurrencyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_currency_layout, parent , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CryptoCurrency currency = cryptoCurrencyList.get(position);
        holder.cryptoName.setText(currency.getName());

        Glide.with(context).load("https://s2.coinmarketcap.com/static/img/coins/64x64/"+currency.getId()+".png").
                thumbnail(Glide.with(context) .load(R.drawable.spinner)).
                into(holder.cryptoImage);
        //
        if (currency.getPercentChange24h()>0){
            holder.cryptoPrice.setTextColor(context.getResources().getColor(R.color.gain_Color));
            holder.cryptoPrice.setText("+" + String.format("%.02f" , currency.getPercentChange24h()) + " %");
        }
        else {
            holder.cryptoPrice.setTextColor(context.getResources().getColor(R.color.loss_Color));
            holder.cryptoPrice.setText(String.format("%.02f" , currency.getPercentChange24h()) + " %");
        }

    }

    @Override
    public int getItemCount() {
        return cryptoCurrencyList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cryptoImage;
        TextView cryptoName , cryptoPrice ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cryptoImage = itemView.findViewById(R.id.topCurrencyImageView);
            cryptoPrice = itemView.findViewById(R.id.topCurrencyChangeTextView);
            cryptoName = itemView.findViewById(R.id.topCurrencyNameTextView);
        }
    }
}
