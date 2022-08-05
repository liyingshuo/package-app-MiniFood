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

public class RegisterActivity extends AppCompatActivity {
    private EditText et_Name;
    private EditText et_Password;
    private EditText et_ConfirmPassword;
    private EditText et_PasswordMessage;
    private EditText et_TelephoneNumber;
    private EditText et_Address;
    private Button bt_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        et_Name = (EditText)findViewById(R.id.register_Name);
        et_Password = (EditText)findViewById(R.id.register_Password);
        et_ConfirmPassword = (EditText)findViewById(R.id.register_ConfirmPassword);
        et_PasswordMessage = (EditText)findViewById(R.id.register_PasswordMessage);
        et_TelephoneNumber = (EditText)findViewById(R.id.register_TelephoneNumber);
        et_Address = (EditText)findViewById(R.id.register_Address);
        bt_Register = (Button)findViewById(R.id.register_Register);

        et_Name.setText("lys");
        et_Password.setText("123456");
        et_ConfirmPassword.setText("123456");
        et_PasswordMessage.setText("123456");
        et_TelephoneNumber.setText("18903604030");
        et_Address.setText("小米移动互联网产业园C-9-088");

        bt_Register.setOnClickListener(new ButtonListener());
        et_Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(et_Name.getText().toString().trim().length() > 10){
                    Toast.makeText(RegisterActivity.this, "用户名过长!", Toast.LENGTH_LONG).show();
                }
            }
        });
        et_ConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(et_ConfirmPassword.getText().toString().trim().length() > et_Password.getText().toString().trim().length()){
                    Toast.makeText(RegisterActivity.this, "两次输入的密码不同!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            getClass();
            switch (v.getId()) {
                case R.id.register_Register:
                    //注册按钮
                    Toast.makeText(RegisterActivity.this, "点击了注册按钮!!", Toast.LENGTH_SHORT).show();
                    register();
                    break;
                default:
                    String str = Integer.toString(v.getId()) + "该按钮函数未实现!!";
                    Toast.makeText(RegisterActivity.this, str, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void register() {
        String name = et_Name.getText().toString().trim();
        String password = et_ConfirmPassword.getText().toString().trim();
        String confirm = et_ConfirmPassword.getText().toString().trim();
        String prompt = et_PasswordMessage.getText().toString().trim();
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

        SharedPreferences sp = getSharedPreferences("MiniFood", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",name);
        editor.putString("password",password);
        editor.putString("prompt",prompt);
        editor.putString("telephone",telephone);
        editor.putString("address",address);
        editor.apply();

        MiniFoodApplication app = (MiniFoodApplication)getApplication();
        app.g_user.mUsername = name;
        app.g_user.mPassword = password;
        app.g_user.mPhone = telephone;
        app.g_user.mAddress = address;
        app.g_user.mIsLogin = true;

        setResult(RESULT_OK, new Intent(this, LoginActivity.class).putExtra("register_success","true"));
        finish();
    }
}