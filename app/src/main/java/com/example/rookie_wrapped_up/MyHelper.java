package com.example.rookie_wrapped_up;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
//    站点
    String siteName,sitePhone;
    String siteName2,sitePhone2;
    String siteName3,sitePhone3;
    int siteAddress,siteAddress2,siteAddress3;
//    地址
    String province,city,detailedAddress,detailedAddress2,detailedAddress3;
//    个人
    String peopleName,userName,peoplePhone,peopleParcel,peopleImg;
    int peopleAddress;
//    包裹
    String state,time,message,express,img;
    String state2,time2,message2,express2,img2;
    String state3,time3,message3,img3;

    public MyHelper(Context context){
        super(context,"rookie_wrapped_up.db",null,1);
    }

    //        个人（
//        id，姓名，用户名,电话，地址，包裹，身份码图片
//        FOREIGN KEY（‘地址’）REFERENCES ‘地址’（‘id’）
//        FOREIGN KEY（‘包裹’）REFERENCES ‘包裹’（‘id’）
//）
//        包裹（
//        单号，签收状态，签收时间，信息，图片
//）
//        地址（
//        id，省，市，详细地址
//）
//        站点（
//        id，名字，地址，联系方式
//        FOREIGN KEY（‘地址’）REFERENCES ‘地址’（‘id’）
//）

//     onCreate只在数据库第一次创建时才执行
    @Override
    public void onCreate(SQLiteDatabase db){
//        建表
        db.execSQL("CREATE TABLE people(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VACHER(20),userName VACHER(20), phone VACHER(20), address INTEGER, parcel INTEGER, img VACHER(20))");
        db.execSQL("CREATE TABLE parcel(_id INTEGER PRIMARY KEY AUTOINCREMENT, state VACHER(20), time VACHER(20), message VACHER(20),express VACHER(20),img VACHER(20))");
        db.execSQL("CREATE TABLE address(_id INTEGER PRIMARY KEY AUTOINCREMENT, province VACHER(20), city VACHER(20), detailed_address VACHER(20))");
        db.execSQL("CREATE TABLE site(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VACHER(20), address INTEGER, phone VACHER(20))");

//        添加初始数据
//        站点
        siteName = "重庆理工大学两江校区西门菜鸟驿站"; siteAddress = 1; sitePhone = "1111111";
        siteName2 = "理工大学两江校区菜鸟驿站"; siteAddress2 = 2; sitePhone2 = "2222222";
        siteName3 = "两江新区佳美布艺经营部"; siteAddress3 = 3; sitePhone3 = "3333333";

//        地址
        province = "重庆"; city = "重庆"; detailedAddress = "怡园支路65号";detailedAddress2 = "普福大道459号重庆理工大学两江校区";detailedAddress3 ="重庆市重庆市渝北区龙兴镇怡园支路95号";

//        个人
        peopleName = "唐圳"; userName = "忆往昔丶渺渺兮予怀"; peoplePhone = "123"; peopleParcel = "1,2,3"; peopleImg = "D:img/img.jpg";
        peopleAddress = 1;

//        包裹
        state = "已签收"; time = "2020.12.13"; message = "淘宝 | 舒克舒克电动牙刷防水声波来来来";express = "中通快递| 您已在理工大学菜鸟驿站签收"; img = "D:/img/parcel.png";
        state2 = "已签收"; time2 = "2020.12.14"; message2 = "淘宝 | 棉拖鞋女冬季室内防滑底可爱";express2 = "百世快递| 您已在理工大学西门菜鸟驿站签收";


        db.execSQL("insert into  site(name,address,phone) values(?,?,?)",new Object[]{siteName,siteAddress,sitePhone});
        db.execSQL("insert into  site(name,address,phone) values(?,?,?)",new Object[]{siteName2,siteAddress2,sitePhone2});
        db.execSQL("insert into  site(name,address,phone) values(?,?,?)",new Object[]{siteName3,siteAddress3,sitePhone3});

        db.execSQL("insert into  address(province,city,detailed_address) values(?,?,?)",new Object[]{province,city,detailedAddress});
        db.execSQL("insert into  address(province,city,detailed_address) values(?,?,?)",new Object[]{province,city,detailedAddress2});
        db.execSQL("insert into  address(province,city,detailed_address) values(?,?,?)",new Object[]{province,city,detailedAddress3});

        db.execSQL("insert into  people(name,userName,phone,address,parcel,img) values(?,?,?,?,?,?)",new Object[]{peopleName,userName,peoplePhone,peopleAddress,peopleParcel,peopleImg});

        db.execSQL("insert into  parcel(state,time,message,express,img) values(?,?,?,?,?)",new Object[]{state,time,message,express,img});
        db.execSQL("insert into  parcel(state,time,message,express,img) values(?,?,?,?,?)",new Object[]{state2,time2,message2,express2,img});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){}
}
