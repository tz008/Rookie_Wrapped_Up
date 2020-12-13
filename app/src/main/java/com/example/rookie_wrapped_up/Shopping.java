package com.example.rookie_wrapped_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Shopping extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping);

        //裹裹购页面 返回 取件页面
        TextView shopButton=findViewById(R.id.shop_pick_up);
        shopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(Shopping.this,shop_pick_up.class);
                //启动
                startActivity(intent);

            }
        });
    }
}
