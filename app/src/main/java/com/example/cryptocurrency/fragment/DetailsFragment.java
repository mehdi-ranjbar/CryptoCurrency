package com.example.cryptocurrency.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cryptocurrency.MainActivity;
import com.example.cryptocurrency.R;
import com.example.cryptocurrency.models.WatchListModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class DetailsFragment extends Fragment  {

    TextView symbol , price , change_percent;
    ImageView image_currency , image_change , image_back , image_watchList;
    WebView chart;
    Button btn_1month, btn_1week, btn_1day, btn_4hour, btn_1hour, btn_15min;
    // watch list
    public static final String PREF_NAME = "watch_list_pref";
    List<Integer> watchList = new ArrayList<>();
    boolean watchListIsChecked = false;
    //
    int id;


    public DetailsFragment() {

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details , container , false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        //
        //

        //id
        id  = getArguments().getInt("id");
        //symbol
        String symbol_text = getArguments().getString("symbol");
        symbol = view.findViewById(R.id.detailSymbolTextView);
        symbol.setText(symbol_text);

        //price
        price = view.findViewById(R.id.detailPriceTextView);
        price.setText(String.format("%.04f" , getArguments().getDouble("price")) + "$");

        //change percent in 24 h
        image_change = view.findViewById(R.id.detailChangeImageView);
        change_percent = view.findViewById(R.id.detailChangeTextView);
        Double percent = getArguments().getDouble("percentChange");
        if (percent > 0){
            change_percent.setTextColor(getResources().getColor(R.color.gain_Color));
            change_percent.setText("+ " + String.format("%.02f" , percent) +" %");
            image_change.setImageResource(R.drawable.ic_caret_up);
        }
        else if (percent < 0){
            change_percent.setTextColor(getResources().getColor(R.color.loss_Color));
            change_percent.setText(String.format("%.02f" , percent)+ "%");
            image_change.setImageResource(R.drawable.ic_caret_down);
        }

        //image currency
        image_currency = view.findViewById(R.id.detailImageView);
        Glide.with(view).load("https://s2.coinmarketcap.com/static/img/coins/64x64/"+id+".png").
                thumbnail(Glide.with(view) .load(R.drawable.spinner)).
                into(image_currency);

        //image back
        image_back = view.findViewById(R.id.backStackButton);
        String wich = getArguments().getString("wich");

        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                if (wich == "TopLoss"){
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView , new HomeFragment()).commit();

                }
                else if (wich == "Market"){
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView , new MarketFragment()).commit();
                }
            }
        });
        //image watchList
//        image_watchList = view.findViewById(R.id.addWatchlistButton);
//        image_watchList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        //Chart
        chart = view.findViewById(R.id.detailChartWebView);
        chart.getSettings().setJavaScriptEnabled(true);
        chart.setLayerType(View.LAYER_TYPE_SOFTWARE , null);

        //button
        btn_1month = view.findViewById(R.id.button);
        btn_1week = view.findViewById(R.id.button1);
        btn_1day = view.findViewById(R.id.button2);
        btn_4hour = view.findViewById(R.id.button3);
        btn_1hour = view.findViewById(R.id.button4);
        btn_15min = view.findViewById(R.id.button5);
        setDefaultButton(symbol_text);




        btn_1month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chart.loadUrl("https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol="+ symbol_text.toString()+
                        "USD&interval=M&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1" +
                        "&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&" +
                        "overrides={}&enabled_features=[]& disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget" +
                        "&utm_campaign=chart&utm_term=BTCUSDT");
                btn_1month.setBackground(getResources().getDrawable(R.drawable.active_button));
                btn_1week.setBackground(null);
                btn_1day.setBackground(null);
                btn_4hour.setBackground(null);
                btn_1hour.setBackground(null);
                btn_15min.setBackground(null);

            }
        });
        btn_1week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chart.loadUrl("https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol="+ symbol_text.toString()+
                        "USD&interval=W&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1" +
                        "&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&" +
                        "overrides={}&enabled_features=[]& disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget" +
                        "&utm_campaign=chart&utm_term=BTCUSDT");
                btn_1month.setBackground(null);
                btn_1week.setBackground(getResources().getDrawable(R.drawable.active_button));
                btn_1day.setBackground(null);
                btn_4hour.setBackground(null);
                btn_1hour.setBackground(null);
                btn_15min.setBackground(null);
            }
        });
        btn_1day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chart.loadUrl("https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol="+ symbol_text.toString()+
                        "USD&interval=D&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1" +
                        "&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&" +
                        "overrides={}&enabled_features=[]& disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget" +
                        "&utm_campaign=chart&utm_term=BTCUSDT");
                btn_1month.setBackground(null);
                btn_1week.setBackground(null);
                btn_1day.setBackground(getResources().getDrawable(R.drawable.active_button));
                btn_4hour.setBackground(null);
                btn_1hour.setBackground(null);
                btn_15min.setBackground(null);

            }
        });
        btn_4hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chart.loadUrl("https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol="+ symbol_text.toString()+
                        "USD&interval=4H&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1" +
                        "&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&" +
                        "overrides={}&enabled_features=[]& disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget" +
                        "&utm_campaign=chart&utm_term=BTCUSDT");
                btn_1month.setBackground(null);
                btn_1week.setBackground(null);
                btn_1day.setBackground(null);
                btn_4hour.setBackground(getResources().getDrawable(R.drawable.active_button));
                btn_1hour.setBackground(null);
                btn_15min.setBackground(null);
            }
        });
        btn_1hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chart.loadUrl("https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol="+ symbol_text.toString()+
                        "USD&interval=1H&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1" +
                        "&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&" +
                        "overrides={}&enabled_features=[]& disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget" +
                        "&utm_campaign=chart&utm_term=BTCUSDT");
                btn_1month.setBackground(null);
                btn_1week.setBackground(null);
                btn_1day.setBackground(null);
                btn_4hour.setBackground(null);
                btn_1hour.setBackground(getResources().getDrawable(R.drawable.active_button));
                btn_15min.setBackground(null);
            }
        });
        btn_15min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chart.loadUrl("https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol="+ symbol_text.toString()+
                        "USD&interval=15&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1" +
                        "&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&" +
                        "overrides={}&enabled_features=[]& disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget" +
                        "&utm_campaign=chart&utm_term=BTCUSDT");
                btn_1month.setBackground(null);
                btn_1week.setBackground(null);
                btn_1day.setBackground(null);
                btn_4hour.setBackground(null);
                btn_1hour.setBackground(null);
                btn_15min.setBackground(getResources().getDrawable(R.drawable.active_button));
            }
        });

    }

//    public List<Integer> readData(){
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences(PREF_NAME , Context.MODE_PRIVATE);
//        watchList.add(sharedPreferences.getInt("id", -1));
//        return watchList;
//    }
//    public void storeData(){
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences(PREF_NAME , Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.put
//    }
    private void setDefaultButton(String symbol) {
        chart.loadUrl("https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol="+ symbol.toString()+
                "USD&interval=D&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1" +
                "&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&" +
                "overrides={}&enabled_features=[]& disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget" +
                "&utm_campaign=chart&utm_term=BTCUSDT");
        btn_1month.setBackground(null);
        btn_1week.setBackground(null);
        btn_1day.setBackground(getResources().getDrawable(R.drawable.active_button));
        btn_4hour.setBackground(null);
        btn_1hour.setBackground(null);
        btn_15min.setBackground(null);

    }

}