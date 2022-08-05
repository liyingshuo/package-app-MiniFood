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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText et_name;
    EditText et_password;
    Button bt_login;
    TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        et_name = (EditText)this.findViewById(R.id.login_Name);
        et_password = (EditText)this.findViewById(R.id.login_Password);
        bt_login = (Button) this.findViewById(R.id.login_Login);
        tv_register = (TextView)findViewById(R.id.login_Register);

        bt_login.setOnClickListener(new ButtonListener());
        tv_register.setOnClickListener(new ButtonListener());

        MiniFoodApplication app = (MiniFoodApplication)getApplication();
        et_name.setText(app.g_user.mUsername);
        et_password.setText(app.g_user.mPassword);
    }

    public class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            getClass();
            switch (v.getId()) {
                case R.id.login_Login:
                    //登陆按钮
                    Toast.makeText(LoginActivity.this,"点击了登陆按钮!!", Toast.LENGTH_SHORT).show();
                    Login();
                    break;
                case R.id.login_Register:
                    //注册按钮
                    Toast.makeText(LoginActivity.this,"点击了注册按钮!!", Toast.LENGTH_SHORT).show();
                    Register();
                    break;
                default:
                    String str = Integer.toString(v.getId()) + "该按钮函数未实现!!";
                    Toast.makeText(LoginActivity.this, str, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void Login(){
        String name = et_name.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        SharedPreferences sp = getSharedPreferences("MiniFood", MODE_PRIVATE);
        if(name.equals(sp.getString("name",null)) &&
                password.equals(sp.getString("password",null))){
            MiniFoodApplication app = (MiniFoodApplication)getApplication();
            app.g_user.mIsLogin = true;
            setResult(RESULT_OK, new Intent(this, MainActivity.class).putExtra("login_success","true"));
            finish();
        }else if(!name.equals(sp.getString("name",null))){
            Toast.makeText(this, "用户名不存在,请注册!", Toast.LENGTH_LONG).show();
        }else if(!password.equals(sp.getString("password",null))){
            String prompt = sp.getString("prompt",null);
            Toast.makeText(this, "密码错误! 提示词为:"+prompt, Toast.LENGTH_LONG).show();
        }
    }


    ActivityResultLauncher launcher_register = registerForActivityResult(new LoginActivity.ResultContract(), new ActivityResultCallback<String>() {
        @Override
        public void onActivityResult(String result) {
            if(result.equals("true")){
                Toast.makeText(LoginActivity.this, "注册成功!!!", Toast.LENGTH_SHORT).show();
                MiniFoodApplication app = (MiniFoodApplication)getApplication();
                et_name.setText(app.g_user.mUsername);
                et_password.setText(app.g_user.mPassword);
            }
            else {
                Toast.makeText(LoginActivity.this, "注册失败!!!", Toast.LENGTH_SHORT).show();
            }
        }
    });

    class ResultContract extends ActivityResultContract<Boolean, String> {
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Boolean input) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            return intent;
        }

        @Override
        public String parseResult(int resultCode, @Nullable Intent intent) {
            return intent.getStringExtra("register_success");
        }
    }

    private void Register(){
        launcher_register.launch(true);
    }


}