package com.example.rookie_wrapped_up;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rookie_wrapped_up.entity.Parcel;
import com.example.rookie_wrapped_up.entity.People;

public class HomePage extends AppCompatActivity implements View.OnClickListener{
    MyHelper myHelper;
    SQLiteDatabase db;
    private TextView tState,tMessage,tExpress;
    Parcel parcel = new Parcel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        goPage();
        Data();
    }

//跳转
    private void goPage(){
        findViewById(R.id.home_pick_up).setOnClickListener(this);
        findViewById(R.id.home_mail_page).setOnClickListener(this);
        findViewById(R.id.home_shopping).setOnClickListener(this);
        findViewById(R.id.home_my).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.home_pick_up:
                intent.setClass(HomePage.this, shop_pick_up.class);
                break;
            case R.id.home_mail_page:
                intent.setClass(HomePage.this, NavigationList_01.class);
                break;
            case R.id.home_shopping:
                intent.setClass(HomePage.this,Shopping.class);
                break;
            case R.id.home_my:
                intent.setClass(HomePage.this,mypage.class);
                break;
        }
        startActivity(intent);
    }

    //    展示数据
    private void Data(){
        myHelper = new MyHelper(HomePage.this);
        db = myHelper.getWritableDatabase();
        tState = findViewById(R.id.home_state);
        tMessage = findViewById(R.id.home_message);
        tExpress =findViewById(R.id.home_express);

        Cursor cursor = db.rawQuery("select * from parcel",new String[]{});
        if(cursor.getCount() == 0){
            Log.v("HomePage"," 没有数据 ......");
            Toast.makeText(HomePage.this,"没有数据",Toast.LENGTH_SHORT).show();
        } else {
            Log.v("NavigationList_01","有");
            cursor.moveToFirst();

            parcel.setState(cursor.getString(1));
            parcel.setMessage(cursor.getString(3));
            parcel.setExpress(cursor.getString(4));
        }

        tState.setText(parcel.getState());
        tMessage.setText(parcel.getMessage());
        tExpress.setText(parcel.getExpress());





        cursor.close();
        db.close();

    }
}
