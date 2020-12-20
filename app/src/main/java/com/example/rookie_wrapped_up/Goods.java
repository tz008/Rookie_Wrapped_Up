package com.example.rookie_wrapped_up;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rookie_wrapped_up.entity.Parcel;

import java.util.ArrayList;
import java.util.List;

public class Goods extends AppCompatActivity {
    MyHelper myHelper;
    SQLiteDatabase db;
    TextView tMessage,tExpress,tTime;
    List<Parcel> parcelList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail);
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        Data();
        printData(Integer.parseInt(key)-1);
        goodsHome();
    }

    //    拿数据
    private void Data(){
        myHelper = new MyHelper(Goods.this);
        db = myHelper.getWritableDatabase();
        tTime = findViewById(R.id.goods_time);
        tMessage = findViewById(R.id.goods_message);
        tExpress =findViewById(R.id.goods_express);


        Cursor cursor = db.rawQuery("select * from parcel",new String[]{});
        if(cursor.getCount() == 0){
            Log.v("Goods"," 没有数据 ......");
            Toast.makeText(Goods.this,"没有数据",Toast.LENGTH_SHORT).show();
        } else {
            Log.v("Goods","有");
            cursor.moveToFirst();
            Parcel parcel = new Parcel();
            parcel.setTime(cursor.getString(2));
            parcel.setMessage(cursor.getString(3));
            parcel.setExpress(cursor.getString(4));
            parcelList.add(parcel);
        } while (cursor.moveToNext()){
            Parcel parcel = new Parcel();
            parcel.setTime(cursor.getString(2));
            parcel.setMessage(cursor.getString(3));
            parcel.setExpress(cursor.getString(4));
            parcelList.add(parcel);
//           tName2.setText(cursor.getString(1));
        }





        cursor.close();
        db.close();

    }

    protected void printData(int key){
            tTime.setText(parcelList.get(key).getTime());
            tMessage.setText(parcelList.get(key).getMessage());
            tExpress.setText(parcelList.get(key).getExpress());

    }

    //    包裹详情页面去主页
    private void goodsHome(){
        ImageView mailPageButton = findViewById(R.id.goods_home);
        mailPageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(Goods.this,HomePage.class);
                //启动
                startActivity(intent);
            }
        });
    }


}
