package com.example.rookie_wrapped_up;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rookie_wrapped_up.entity.Address;
import com.example.rookie_wrapped_up.entity.People;

public class my_address_book extends AppCompatActivity {
    MyHelper myHelper = new MyHelper(my_address_book.this);
    SQLiteDatabase db;
    Button button=null;
    private TextView tName,tAddress,tPhone;
    People people=new People();
    Address address=new Address();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_address_book);
        initUI();
        init();
    }
    private void initUI(){
        button=findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(my_address_book.this, mypage.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        tName =findViewById(R.id.my_address_name);
        tPhone=findViewById(R.id.my_address_phone);
        tAddress=findViewById(R.id.my_address);
        db = myHelper.getWritableDatabase();


//        db.execSQL("insert into  test2 (name,address) values(?,?)",new Object[]{name,address});
//        db.execSQL("delete from test2 where name = '重庆理工大学'");

        //       查询数据
//
//      查询用户信息
        Cursor cursor = db.rawQuery("select * from people",new String[]{});
        if(cursor.getCount() == 0){
            Log.v("NavigationList_01"," 没有数据 ......");
            tName.setText("没有数据");
            Toast.makeText(my_address_book.this,"没有数据",Toast.LENGTH_SHORT).show();
        } else {
            Log.v("NavigationList_01","有");
            cursor.moveToFirst();

            people.setName(cursor.getString(1));
            people.setPhone(cursor.getString(3));
        }
//        while (cursor2.moveToNext()){
//            Address address = new Address();
//            address.setDetailedAddress(cursor2.getString(3));
////            tAddress2.setText(cursor2.getString(3));
//        }

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
        Cursor cursor2 = db.rawQuery("select * from address",new String[]{});
        if(cursor2.getCount() == 0){
            Log.v("NavigationList_01"," 没有数据 ......");
            tName.setText("没有数据");
            Toast.makeText(my_address_book.this,"没有数据",Toast.LENGTH_SHORT).show();
        } else {
            Log.v("NavigationList_01","有");
            cursor2.moveToFirst();

            address.setProvince(cursor2.getString(1));
            address.setCity(cursor2.getString(2));
            address.setDetailedAddress(cursor2.getString(3));
        }

        tName.setText(people.getName());
        tPhone.setText(people.getPhone()+"");
        tAddress.setText(address.getProvince()+address.getCity()+address.getDetailedAddress());
        cursor.close();
        db.close();
    }
}
