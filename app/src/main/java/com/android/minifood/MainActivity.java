package com.android.minifood;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton ib_yidongdiancan;
    ImageButton ib_diancan;
    ImageButton ib_waimai;
    ImageButton ib_gerenzhongxin;
    ImageButton ib_denglu;
    ImageButton ib_zhuxiao;
    ImageButton ib_wodedingdan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        ib_yidongdiancan = (ImageButton)findViewById(R.id.main_yidongdiancan);
        ib_diancan = (ImageButton)findViewById(R.id.main_diancan);
        ib_waimai = (ImageButton)findViewById(R.id.main_waimai);
        ib_gerenzhongxin = (ImageButton)findViewById(R.id.main_gerenzhongxin);
        ib_denglu = (ImageButton)findViewById(R.id.main_denglu);
        ib_zhuxiao = (ImageButton)findViewById(R.id.main_zhuxiao);
        ib_wodedingdan = (ImageButton)findViewById(R.id.main_wodedingdan);

        ib_yidongdiancan.setOnClickListener(new ButtonListener());
        ib_diancan.setOnClickListener(new ButtonListener());
        ib_waimai.setOnClickListener(new ButtonListener());
        ib_gerenzhongxin.setOnClickListener(new ButtonListener());
        ib_denglu.setOnClickListener(new ButtonListener());
        ib_zhuxiao.setOnClickListener(new ButtonListener());
        ib_wodedingdan.setOnClickListener(new ButtonListener());

        SharedPreferences sp = getSharedPreferences("MiniFood", Context.MODE_PRIVATE);
        MiniFoodApplication app = (MiniFoodApplication)getApplication();
        app.g_user.mUsername = sp.getString("name", null);
        app.g_user.mPassword = sp.getString("password", null);
        app.g_user.mPhone = sp.getString("telephone", null);
        app.g_user.mAddress = sp.getString("address", null);
        app.g_user.mIsLogin = false;
    }

    public class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.main_yidongdiancan:
                    //??????????????????
                    Toast.makeText(MainActivity.this,"???????????????????????????!!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.main_diancan:
                    //????????????
                    Toast.makeText(MainActivity.this,"?????????????????????!!", Toast.LENGTH_SHORT).show();
                    showDishesList("????????????");
                    break;
                case R.id.main_waimai:
                    //????????????
                    Toast.makeText(MainActivity.this,"?????????????????????!!", Toast.LENGTH_SHORT).show();
                    showDishesList("????????????");
                    break;
                case R.id.main_gerenzhongxin:
                    //??????????????????
                    Toast.makeText(MainActivity.this,"???????????????????????????!!", Toast.LENGTH_SHORT).show();
                    UserCenter();
                    break;
                case R.id.main_denglu:
                    //????????????
                    Toast.makeText(MainActivity.this,"?????????????????????!!", Toast.LENGTH_SHORT).show();
                    login();
                    break;
                case R.id.main_zhuxiao:
                    //????????????
                    Toast.makeText(MainActivity.this,"?????????????????????!!", Toast.LENGTH_SHORT).show();
                    ib_zhuxiao.setVisibility(Button.GONE);
                    ib_denglu.setVisibility(Button.VISIBLE);
                    MiniFoodApplication app = (MiniFoodApplication)getApplication();
                    app.g_user.mIsLogin = false;
                    break;
                case R.id.main_wodedingdan:
                    //??????????????????
                    Toast.makeText(MainActivity.this,"???????????????????????????!!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    String str = Integer.toString(v.getId()) + "????????????????????????!!";
                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    ActivityResultLauncher launcher_login = registerForActivityResult(new ResultContract(), new ActivityResultCallback<String>() {
        @Override
        public void onActivityResult(String result) {
            if(result.equals("true")){
                Toast.makeText(MainActivity.this, "????????????!!!", Toast.LENGTH_SHORT).show();
                ib_denglu.setVisibility(Button.GONE);
                ib_zhuxiao.setVisibility(Button.VISIBLE);
            }
            else {
                Toast.makeText(MainActivity.this, "????????????!!!", Toast.LENGTH_SHORT).show();
            }
        }
    });

    class ResultContract extends ActivityResultContract<Boolean, String> {
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Boolean input) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            return intent;
        }

        @Override
        public String parseResult(int resultCode, @Nullable Intent intent) {
            return intent.getStringExtra("login_success");
        }
    }

    private void login() {
        launcher_login.launch(true);
    }

    void showDishesList(String title){
        Intent intent = new Intent(MainActivity.this, TabHostActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    void UserCenter(){
        Intent intent = new Intent(MainActivity.this, UserCenterActivity.class);
        startActivity(intent);
    }
}