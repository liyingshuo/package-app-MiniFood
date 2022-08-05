package com.android.minifood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserCenterActivity extends AppCompatActivity {

    private EditText et_Name;
    private EditText et_Password;
    private EditText et_ConfirmPassword;
    private EditText et_TelephoneNumber;
    private EditText et_Address;
    private Button bt_ModifyUserMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        getSupportActionBar().hide();

        et_Name = (EditText)findViewById(R.id.user_center_Name);
        et_Password = (EditText)findViewById(R.id.user_center_Password);
        et_ConfirmPassword = (EditText)findViewById(R.id.user_center_ConfirmPassword);
        et_TelephoneNumber = (EditText)findViewById(R.id.user_center_TelephoneNumber);
        et_Address = (EditText)findViewById(R.id.user_center_Address);
        bt_ModifyUserMsg = (Button)findViewById(R.id.user_center_modifyUserMsg);

        MiniFoodApplication app = (MiniFoodApplication)getApplication();

        if(app.g_user.mIsLogin){
            et_Name.setText(app.g_user.mUsername);
            et_Password.setText(app.g_user.mPassword);
            et_ConfirmPassword.setText(app.g_user.mPassword);
            et_TelephoneNumber.setText(app.g_user.mPhone);
            et_Address.setText(app.g_user.mAddress);
            bt_ModifyUserMsg.setVisibility(View.VISIBLE);
        } else {
            et_Name.setText("");
            et_Password.setText("");
            et_ConfirmPassword.setText("");
            et_TelephoneNumber.setText("");
            et_Address.setText("");
            bt_ModifyUserMsg.setVisibility(View.INVISIBLE);
            Toast.makeText(UserCenterActivity.this, "用户未登录,请先登录!!", Toast.LENGTH_SHORT).show();
        }


        bt_ModifyUserMsg.setOnClickListener(new UserCenterActivity.ButtonListener());
        et_Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(et_Name.getText().toString().trim().length() > 10){
                    Toast.makeText(UserCenterActivity.this, "用户名过长!", Toast.LENGTH_LONG).show();
                }
            }
        });
        et_ConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(et_ConfirmPassword.getText().toString().trim().length() > et_Password.getText().toString().trim().length()){
                    Toast.makeText(UserCenterActivity.this, "两次输入的密码不同!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            getClass();
            switch (v.getId()) {
                case R.id.user_center_modifyUserMsg:
                    //保存按钮
                    Toast.makeText(UserCenterActivity.this, "点击了修改个人信息按钮!!", Toast.LENGTH_SHORT).show();
                    save();
                    break;
                default:
                    String str = Integer.toString(v.getId()) + "该按钮函数未实现!!";
                    Toast.makeText(UserCenterActivity.this, str, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(UserCenterActivity.this, MainActivity.class);
//        startActivity(intent);
//    }

    private void save() {
        String name = et_Name.getText().toString().trim();
        String password = et_ConfirmPassword.getText().toString().trim();
        String confirm = et_ConfirmPassword.getText().toString().trim();
        String telephone = et_TelephoneNumber.getText().toString().trim();
        String address = et_Address.getText().toString().trim();

        if(name.length() < 1){
            Toast.makeText(this,"昵称不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if(!password.equals(confirm)){
            Toast.makeText(this,"两次输入的密码不同",Toast.LENGTH_LONG).show();
            return;
        }

        MiniFoodApplication app = (MiniFoodApplication)getApplication();
        app.g_user.mUsername = name;
        app.g_user.mPassword = password;
        app.g_user.mPhone = telephone;
        app.g_user.mAddress = address;
        Toast.makeText(UserCenterActivity.this, "修改个人信息成功!!", Toast.LENGTH_SHORT).show();
    }


}