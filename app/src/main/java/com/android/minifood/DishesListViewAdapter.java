package com.android.minifood;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DishesListViewAdapter extends BaseAdapter {

    public ArrayList<Dish> m_list;
    private Activity activity;

    DishesListViewAdapter(ArrayList<Dish> list, Activity context){
        m_list = list;
        this.activity = context;
    }

    public class DishesListViewHolder{
        TextView name;
        TextView price;
        TextView describe;
        ImageView img;

        public DishesListViewHolder(@NonNull View view) {
            name = (TextView)view.findViewById(R.id.textViewName_dish);
            price = (TextView)view.findViewById(R.id.textViewPrice_dish);
            describe = (TextView)view.findViewById(R.id.textViewDescribe_dish);
            img = (ImageView)view.findViewById(R.id.imageViewImage_dish);
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
        View view = LayoutInflater.from(activity).inflate(R.layout.list_view_dish,null);
        DishesListViewAdapter.DishesListViewHolder dishesListViewHolder = new DishesListViewAdapter.DishesListViewHolder(view);
        Dish dish = getItem(position);

        dishesListViewHolder.name.setText(dish.mName);
        dishesListViewHolder.price.setText(Integer.toString(dish.mPrice));
        dishesListViewHolder.describe.setText(dish.mDescribe);
        if(dish.mImage > 0){
            dishesListViewHolder.img.setImageResource(dish.mImage);
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

        Dishes dishes;
        String title = intent.getStringExtra("title");
        if (title.equals("点餐专区")){
            dishes = app.g_dishes;
        } else if (title.equals("外卖专区")){
            dishes = app.g_dishes;
        } else {
            dishes = app.g_dishes;
        }
        Dish dish = new Dish();
        for (int i=0; i<dishes.mDishes.size(); i++){
            if (dishes.mDishes.get(i).mId == id){
                dish = dishes.mDishes.get(i);
            }
        }
        dish.mNumber = 1;
        app.g_shopping.mDishes.add(dish);
        refreshList(intent);
    }

    private void ReduceDishFromList(long id, Intent intent) {
        MiniFoodApplication app = (MiniFoodApplication)activity.getApplication();

        for (int i=0; i<app.g_shopping.mDishes.size(); i++){
            Dish dish = app.g_shopping.mDishes.get(i);
            if (dish.mId == id){
                app.g_shopping.mDishes.remove(dish);
                refreshList(intent);
                return;
            }
        }
    }

    void refreshList(Intent intent){
        notifyDataSetChanged();
    }
}
