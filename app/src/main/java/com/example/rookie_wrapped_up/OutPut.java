package com.example.rookie_wrapped_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class OutPut extends AppCompatActivity {


//    前往退货页面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_put);
        goMailPage();
    }



    //    点击去往我的页面
    private void goMailPage(){
        ImageView mailPageButton = findViewById(R.id.out_put_my);
        mailPageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(OutPut.this,NavigationList_01.class);
                //启动
                startActivity(intent);
            }
        });
    }
}