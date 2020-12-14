package com.example.rookie_wrapped_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rookie_wrapped_up.entity.Address;
import com.example.rookie_wrapped_up.entity.Site;

import java.util.ArrayList;

public class shop_pick_up extends AppCompatActivity {
    MyHelper myHelper;
    private TextView  tName,tAddress,tName2,tAddress2,tName3,tAddress3;
    SQLiteDatabase db;
    ArrayList<Site> sites = new ArrayList();
    ArrayList<Address> addressArrayList = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_up);

        myHelper = new MyHelper(shop_pick_up.this);
        init();
        pickUpMailPage();
        goMailPage();


        //取件页面 点击去往  裹裹购页面
        LinearLayout shopButton=findViewById(R.id.pick_up_shop);
        shopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(shop_pick_up.this,Shopping.class);
                //启动
                startActivity(intent);
            }
        });

        //取件页面 点击去往  主页面
        LinearLayout homePage=findViewById(R.id.pick_up_home);
        homePage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(shop_pick_up.this,HomePage.class);
                //启动
                startActivity(intent);
            }
        });
    }

//    取件页面点击去寄件页面
    private void pickUpMailPage(){
        LinearLayout mailPageButton = findViewById(R.id.pick_up_mailPage);
        mailPageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(shop_pick_up.this,NavigationList_01.class);
                //启动
                startActivity(intent);
            }
        });
    }

//    点击去往我的页面
private void goMailPage(){
    LinearLayout mailPageButton = findViewById(R.id.pick_up_my);
    mailPageButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent=new Intent(shop_pick_up.this,mypage.class);
            //启动
            startActivity(intent);
        }
    });
}

    //连接数据库SQLite
    private void init(){
        tName =findViewById(R.id.site_name);
        tAddress = findViewById(R.id.site_address);
        tName2 =findViewById(R.id.site_name2);
        tAddress2 = findViewById(R.id.site_address2);
        tName3 =findViewById(R.id.site_name3);
        tAddress3 = findViewById(R.id.site_address3);
        db = myHelper.getWritableDatabase();


//        db.execSQL("insert into  test2 (name,address) values(?,?)",new Object[]{name,address});
//        db.execSQL("delete from test2 where name = '重庆理工大学'");

        //       查询数据
//        查询站点信息
        Cursor cursor = db.rawQuery("select * from site",new String[]{});
        if(cursor.getCount() == 0){
            Log.v("shop_pick_up"," 没有数据 ......");
            tName.setText("没有数据");
            Toast.makeText(shop_pick_up.this,"没有数据",Toast.LENGTH_SHORT).show();
        } else {
            cursor.moveToFirst();
            Site site = new Site();
            site.setName(cursor.getString(1));
            site.setAddress(cursor.getInt(2));
            sites.add(site);
//            tName.setText(cursor.getString(1));
        }
        while (cursor.moveToNext()){
            Site site = new Site();
            site.setName(cursor.getString(1));
            site.setAddress(cursor.getInt(2));
            sites.add(site);
//           tName2.setText(cursor.getString(1));
        }

//      查询地址信息
        Cursor cursor2 = db.rawQuery("select * from address",new String[]{});
        if(cursor2.getCount() == 0){
            Log.v("shop_pick_up"," 没有数据 ......");
            tName.setText("没有数据");
            Toast.makeText(shop_pick_up.this,"没有数据",Toast.LENGTH_SHORT).show();
        }

        else {
            cursor2.moveToFirst();
            Address address = new Address();
            address.setDetailedAddress(cursor2.getString(3));
            addressArrayList.add(address);
//            tAddress.setText(cursor2.getString(3));
        }
        while (cursor2.moveToNext()){
            Address address = new Address();
            address.setDetailedAddress(cursor2.getString(3));
            addressArrayList.add(address);
//            tAddress2.setText(cursor2.getString(3));
        }

//        if(cursor2 != null&&cursor2.moveToFirst()) {
//        for(int j=0; j<address.length;j++) {
//            for (int i = 0; i < cursor2.getCount(); i++) {
//                cursor2.move(i);
//                if (address[j] == cursor2.getInt(0)) {
//                    detailedAddress[j] = cursor2.getString(3);
//                }
//            }
//        }
//        }

        tName.setText(sites.get(0).getName());
        tName2.setText(sites.get(1).getName());
        tName3.setText(sites.get(2).getName());

        tAddress.setText(addressArrayList.get(0).getDetailedAddress());
        tAddress2.setText(addressArrayList.get(1).getDetailedAddress());
        tAddress3.setText(addressArrayList.get(2).getDetailedAddress());


        cursor.close();
        db.close();







    }
}
