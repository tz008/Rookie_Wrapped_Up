package com.example.rookie_wrapped_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class settingActivity extends AppCompatActivity {


//    前往设置页面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        goLogin();
    }


    //    点击去往我的页面
    private void goLogin(){
        LinearLayout mailPageButton = findViewById(R.id.login_out);
        ImageView imageView = findViewById(R.id.setting_return);
        mailPageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(settingActivity.this,MainActivity.class);
                //启动
                startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(settingActivity.this,mypage.class);
                //启动
                startActivity(intent);
            }
        });
    }
}