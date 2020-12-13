package com.example.rookie_wrapped_up;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rookie_wrapped_up.entity.People;

public class mypage extends AppCompatActivity implements View.OnClickListener{
    MyHelper myHelper = new MyHelper(mypage.this);
    SQLiteDatabase db;
    private TextView tName;
    People people = new People();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my);
        init();
        initUI();
    }
    private void initUI(){
        findViewById(R.id.ll_3).setOnClickListener(this);
        findViewById(R.id.ll_5).setOnClickListener(this);
        findViewById(R.id.my_pick_up).setOnClickListener(this);
        findViewById(R.id.my_mail_page).setOnClickListener(this);
        findViewById(R.id.my_shopping).setOnClickListener(this);
        findViewById(R.id.my_setting).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.ll_3:
                intent.setClass(mypage.this, package_notification_settings.class);
                break;
            case R.id.ll_5:
                intent.setClass(mypage.this, my_address_book.class);
                break;
            case R.id.my_pick_up:
                intent.setClass(mypage.this,shop_pick_up.class);
                break;
            case R.id.my_mail_page:
                intent.setClass(mypage.this,NavigationList_01.class);
                break;
            case R.id.my_shopping:
                intent.setClass(mypage.this,MainActivity.class);
                break;
            case R.id.my_setting:
                intent.setClass(mypage.this,settingActivity.class);
                break;
        }
        startActivity(intent);
    }

    private void init(){
        tName =findViewById(R.id.mypage_name);
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
            Toast.makeText(mypage.this,"没有数据",Toast.LENGTH_SHORT).show();
        } else {
            Log.v("NavigationList_01","有");
            cursor.moveToFirst();

            people.setUserName(cursor.getString(2));
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

        tName.setText(people.getUserName());
        cursor.close();
        db.close();
    }


}

