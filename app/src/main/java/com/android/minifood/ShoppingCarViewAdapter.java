package com.android.minifood;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ShoppingCarViewAdapter extends BaseAdapter {

    public ArrayList<Dish> m_list;
    private Activity activity;
    private TextView textView_total;

    ShoppingCarViewAdapter(ArrayList<Dish> list, Activity context){
        m_list = list;
        this.activity = context;

        textView_total = (TextView)activity.findViewById(R.id.listView_total);
        RefreshData();
    }

    public class ShoppingCarViewHolder{
        TextView name;
        TextView price;
        TextView describe;
        TextView number;
        ImageView img;

        public ShoppingCarViewHolder(@NonNull View view) {
            name = (TextView)view.findViewById(R.id.textViewName_shop);
            price = (TextView)view.findViewById(R.id.textViewPrice_shop);
            describe = (TextView)view.findViewById(R.id.textViewDescribe_shop);
            number = (TextView)view.findViewById(R.id.textViewNumber_shop);
            img = (ImageView)view.findViewById(R.id.imageViewImage_shop);
        }
    }

    @Override
    public int getCount() {
        return m_list == null ? 0 : m_list.size();
    }

    @Override
    public Dish getItem(int position) {
        return m_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.m_list.get(position).mId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RefreshData();

        View view = LayoutInflater.from(activity).inflate(R.layout.list_view_shop,null);
        ShoppingCarViewAdapter.ShoppingCarViewHolder shoppingCarViewHolder = new ShoppingCarViewAdapter.ShoppingCarViewHolder(view);
        Dish dish = getItem(position);

        shoppingCarViewHolder.name.setText(dish.mName);
        shoppingCarViewHolder.price.setText(Integer.toString(dish.mPrice));
        shoppingCarViewHolder.describe.setText(dish.mDescribe);
        shoppingCarViewHolder.number.setVisibility(View.VISIBLE);
        shoppingCarViewHolder.number.setText("x" + Integer.toString(dish.mNumber));
        if(dish.mImage > 0){
            shoppingCarViewHolder.img.setImageResource(dish.mImage);
        }
        Intent intent = activity.getIntent();
        view.setTag(getItemId(position));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = (Long)v.getTag();
                String string = "点击了菜品 编号为: " + Long.toString(id);
                Toast.makeText(activity, string, Toast.LENGTH_SHORT).show();
                AddDishToList(id, intent);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                long id = (Long)v.getTag();
                String string = "长按了菜品 编号为: " + Long.toString(id);
                Toast.makeText(activity, string, Toast.LENGTH_SHORT).show();
                ReduceDishFromList(id, intent);
                return true;
            }
        });
        return view;
    }

    private void AddDishToList(long id, Intent intent) {
        MiniFoodApplication app = (MiniFoodApplication)activity.getApplication();

        for (int i=0; i<app.g_shopping.mDishes.size(); i++){
            Dish dish = app.g_shopping.mDishes.get(i);
            if (dish.mId == id){
                dish.mNumber += 1;
                refreshList(intent);
                return;
            }
        }
    }

    private void ReduceDishFromList(long id, Intent intent) {
        MiniFoodApplication app = (MiniFoodApplication)activity.getApplication();

        for (int i=0; i<app.g_shopping.mDishes.size(); i++){
            Dish dish = app.g_shopping.mDishes.get(i);
            if (dish.mId == id){
                if (dish.mNumber >= 2){
                    dish.mNumber -= 1;
                } else {
                    app.g_shopping.mDishes.remove(dish);
                }
                refreshList(intent);
                return;
            }
        }
    }

    void refreshList(Intent intent){
        notifyDataSetChanged();
        RefreshData();
    }

    public void RefreshData() {
        MiniFoodApplication app = (MiniFoodApplication)activity.getApplication();
        int total = 0;
        for (int i=0; i<app.g_shopping.mDishes.size(); i++){
            Dish dish = app.g_shopping.mDishes.get(i);
            total += dish.mPrice*dish.mNumber;
        }
        textView_total.setText(Integer.toString(total));
    }
}
