package com.example.rookie_wrapped_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rookie_wrapped_up.entity.People;

public class MainActivity extends AppCompatActivity {

    MyHelper myHelper;
    SQLiteDatabase db;
    private Button button;
    private EditText nameText,passwordText;
    private String name,password;
    People people = new People();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login();
    }


    //登录
    protected void login(){
        myHelper = new MyHelper(MainActivity.this);
        db = myHelper.getWritableDatabase();
        button= (Button) findViewById(R.id.login_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Login"," onClick OK!");

                nameText=findViewById(R.id.login_name);
                passwordText = findViewById(R.id.login_password);
                name=nameText.getText().toString().trim();
                password=passwordText.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(MainActivity.this,"账号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                Cursor cursor = db.rawQuery("select * from people",new String[]{});
                if(cursor.getCount() == 0){
                    Log.v("Login"," 没有数据 ......");
                    Toast.makeText(MainActivity.this,"没有数据",Toast.LENGTH_SHORT).show();
                } else {
                    Log.v("Login","有");
                    cursor.moveToFirst();
                    people.setName(cursor.getString(1));
                    people.setPhone(cursor.getString(3));
                }
                if(people.getPhone().equals(name)){
                    if(people.getPhone().equals(password)){
                        Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,mypage.class);
                        //启动
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this,"密码不正确",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this,"账号不正确",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }




}
