package com.android.minifood;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class TabHostActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

        Intent intent = getIntent();
        String str = intent.getStringExtra("title");
        Intent tab1 = new Intent(intent);
        tab1.setClass(this, DishesListActivity.class);
        Intent tab2 = new Intent(intent);
        tab2.setClass(this, ShoppingCarActivity.class);

        TabHost tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(str).setContent(tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("购物车").setContent(tab2));
//        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String tabId) {
//                switch (tabId){
//                    case "tab1":
//                        tabHost.setCurrentTab(0);
//                        break;
//                    case "tab2":
//                        tabHost.setCurrentTab(1);
//                        //
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
    }
}