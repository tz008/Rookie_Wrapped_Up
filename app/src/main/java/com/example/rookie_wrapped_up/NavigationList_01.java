package com.example.rookie_wrapped_up;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
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
    List<OPItem> opList;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mailpage_01);
        gridView = (GridView) findViewById(R.id.grid);
        setData();
        setGridView();
        Data();
    }
    /**设置数据*/
    private void setData() {
        opList = new ArrayList<OPItem>();
        OPItem item = new OPItem();
        item.setopName("我要寄件");

        opList.add(item);
        item = new OPItem();
        item.setopName("我要退货");

        opList.add(item);
        item = new OPItem();
        item.setopName("批量寄件");

        opList.add(item);
        item = new OPItem();
        item.setopName("国际港澳台");

        opList.add(item);
        item = new OPItem();
        item.setopName("公益寄件");

        opList.add(item);
        item = new OPItem();
        item.setopName("货运搬家");

        opList.add(item);
        opList.addAll(opList);
    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView() {
        int size = opList.size();
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length ) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(5); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数

        GridViewAdapter adapter = new GridViewAdapter(getApplicationContext(), opList);
        gridView.setAdapter(adapter);
    }

    /**GirdView 数据适配器*/
    public class GridViewAdapter extends BaseAdapter {
        Context context;
        List<OPItem> list;
        public GridViewAdapter(Context _context, List<OPItem> _list) {
            this.list = _list;
            this.context = _context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            TextView operation = (TextView) convertView.findViewById(R.id.operation);

            OPItem city = list.get(position);

            operation.setText(city.getopName());

            return convertView;
        }
    }

    public class OPItem {
        private String opName;

        public String getopName() {
            return opName;
        }

        public void setopName(String opName) {
            this.opName = opName;
        }

    }

    private void Data(){
        myHelper = new MyHelper(NavigationList_01.this);
        db = myHelper.getWritableDatabase();
        tName = findViewById(R.id.mailpage_name);
        tAddress01 = findViewById(R.id.mailpage_address01);
        tAddress02 =findViewById(R.id.mailpage_address02);
        tPhone = findViewById(R.id.mailpage_phone);

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
}
