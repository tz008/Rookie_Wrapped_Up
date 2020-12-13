package com.example.rookie_wrapped_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class package_notification_settings extends AppCompatActivity {
    Button button=null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.package_notification_settings);
        initUI();
    }
    private void initUI(){
        button=findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(package_notification_settings.this, mypage.class);
                startActivity(intent);
            }
        });
    }
}
