package com.android.minifood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DishesListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_list);
        getSupportActionBar().hide();

        listView = (ListView)findViewById(R.id.listView_DishesList);

        MiniFoodApplication app = (MiniFoodApplication)getApplication();

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        ArrayList<Dish> dishes;
        if (title.equals("点餐专区")){
            dishes = app.g_dishes.mDishes;
        } else if (title.equals("外卖专区")){
            dishes = app.g_dishes.mDishes;
        } else {
            dishes = app.g_dishes.mDishes;
        }

        DishesListViewAdapter dishesListViewAdapter = new DishesListViewAdapter(dishes, this);
        listView.setAdapter(dishesListViewAdapter);
        return;
    }
}