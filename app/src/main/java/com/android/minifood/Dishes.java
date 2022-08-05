package com.android.minifood;

import java.util.ArrayList;

public class Dishes {
    public ArrayList<Dish> mDishes;

    Dishes() {
        mDishes = new ArrayList<Dish>();
    }

    public void initDishes() {
        Dish dish = new Dish();
        dish.mId = 0;
        dish.mName = "地三鲜";
        dish.mImage = R.drawable.food_disanxian;
        dish.mPrice = 1;
        dish.mDescribe = "咸口";
        dish.mNumber = 0;
        mDishes.add(dish);

        dish = new Dish();
        dish.mId = 1;
        dish.mName = "锅包肉";
        dish.mImage = R.drawable.food_guobaorou;
        dish.mPrice = 2;
        dish.mDescribe = "酸甜口";
        dish.mNumber = 0;
        mDishes.add(dish);

        dish = new Dish();
        dish.mId = 2;
        dish.mName = "麻辣香锅";
        dish.mImage = R.drawable.food_malaxiangguo;
        dish.mPrice = 3;
        dish.mDescribe = "微辣/麻辣/变态辣";
        dish.mNumber = 0;
        mDishes.add(dish);

        dish = new Dish();
        dish.mId = 3;
        dish.mName = "水煮肉片";
        dish.mImage = R.drawable.food_shuizhuroupian;
        dish.mPrice = 4;
        dish.mDescribe = "微辣/麻辣/变态辣";
        dish.mNumber = 0;
        mDishes.add(dish);

        dish = new Dish();
        dish.mId = 4;
        dish.mName = "鱼香肉丝";
        dish.mImage = R.drawable.food_yuxiangrousi;
        dish.mPrice = 5;
        dish.mDescribe = "酸甜口";
        dish.mNumber = 0;
        mDishes.add(dish);
    }
}
