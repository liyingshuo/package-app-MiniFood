package com.android.minifood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingCarActivity extends AppCompatActivity {
    ListView listView;
    TextView textView_total;
    Button button_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        getSupportActionBar().hide();

        listView = (ListView)findViewById(R.id.listView_ShoppingCar);
        textView_total = (TextView)findViewById(R.id.listView_total);
        button_ok = (Button)findViewById(R.id.listView_OK);

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddOrder();
            }
        });

        MiniFoodApplication app = (MiniFoodApplication)getApplication();
        ArrayList<Dish> dishes = app.g_shopping.mDishes;

        ShoppingCarViewAdapter shoppingCarViewAdapter = new ShoppingCarViewAdapter(dishes, this);
        listView.setAdapter(shoppingCarViewAdapter);
        return;
    }

    @Override
    protected void onResume() {
        super.onResume();

        MiniFoodApplication app = (MiniFoodApplication)getApplication();
        ArrayList<Dish> dishes = app.g_shopping.mDishes;

        ShoppingCarViewAdapter shoppingCarViewAdapter = new ShoppingCarViewAdapter(dishes, this);
        listView.setAdapter(shoppingCarViewAdapter);
        return;
    }

    private void AddOrder() {
    }

}