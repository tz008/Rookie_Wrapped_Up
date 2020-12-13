package com.example.rookie_wrapped_up;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rookie_wrapped_up.entity.Address;
import com.example.rookie_wrapped_up.entity.People;
import com.example.rookie_wrapped_up.entity.Site;

import java.util.ArrayList;
import java.util.List;

public class NavigationList_01 extends Activity {
    MyHelper myHelper;
    SQLiteDatabase db;
    private TextView  tName,tPhone,tAddress01,tAddress02;
    People people = new People();
    Address address = new Address();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mailpage_01);
        Data();
        pickUpMailPageRight();
        pickUpMailPage();
        pickUpAddBook();

    }

    //    取件页面点击去寄件页面
    private void pickUpMailPageRight(){
        Button pickBtn = findViewById(R.id.btn_qfwdj);
        pickBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(NavigationList_01.this,NavigationList_02.class);
                //启动
                startActivity(intent);
            }
        });
    }

    //    取件页面点击去寄件页面
    private void pickUpAddBook(){
        TextView pickText01 = findViewById(R.id.jump_dzp01);
        TextView pickText02 = findViewById(R.id.jump_dzp02);
        TextView mailPageOut1 = findViewById(R.id.mail_page_out1);
        pickText01.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(NavigationList_01.this,my_address_book.class);
                //启动
                startActivity(intent);
            }
        });
        pickText02.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(NavigationList_01.this,my_address_book.class);
                //启动
                startActivity(intent);
            }
        });
        mailPageOut1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(NavigationList_01.this,OutPut.class);
                //启动
                startActivity(intent);
            }
        });
    }


//    展示数据
    private void Data(){
        myHelper = new MyHelper(NavigationList_01.this);
        db = myHelper.getWritableDatabase();
        tName = findViewById(R.id.mailpage_name_01);
        tAddress01 = findViewById(R.id.mailpage_address01_01);
        tAddress02 =findViewById(R.id.mailpage_address02_01);
        tPhone = findViewById(R.id.mailpage_phone_01);

        Cursor cursor = db.rawQuery("select * from people",new String[]{});
        if(cursor.getCount() == 0){
            Log.v("NavigationList_01"," 没有数据 ......");
            tName.setText("没有数据");
            Toast.makeText(NavigationList_01.this,"没有数据",Toast.LENGTH_SHORT).show();
        } else {
            Log.v("NavigationList_01","有");
            cursor.moveToFirst();

            people.setName(cursor.getString(1));
            people.setPhone(cursor.getString(3));
        }


        Cursor cursor2 = db.rawQuery("select * from address",new String[]{});
        if(cursor2.getCount() == 0){
            Log.v("NavigationList_01"," 没有数据 ......");
            tName.setText("没有数据");
            Toast.makeText(NavigationList_01.this,"没有数据",Toast.LENGTH_SHORT).show();
        } else {
            Log.v("NavigationList_01","有");
            cursor2.moveToFirst();

           address.setProvince(cursor2.getString(1));
           address.setCity(cursor2.getString(2));
           address.setDetailedAddress(cursor2.getString(3));
        }


        Log.v("NavigationList_01",people.getName());
        tName.setText(people.getName());
        tPhone.setText(people.getPhone()+"");
        tAddress01.setText(address.getProvince()+address.getCity());
        tAddress02.setText(address.getDetailedAddress());

        cursor.close();
        db.close();

    }

    //    寄件页面点击去我的页面
    private void pickUpMailPage(){
        ImageView mailPageButton = findViewById(R.id.mail_page_return);
        mailPageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(NavigationList_01.this,mypage.class);
                //启动
                startActivity(intent);
            }
        });
    }
}
