package com.lee.oneweekonebook;

import android.Manifest;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//change file name to "MainActivity" 

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterRead adapter;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = openOrCreateDatabase("book.db", MODE_PRIVATE, null);

/*
    //activity_main.xml
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:background="@drawable/shape_box" />
*/

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterRead(getApplicationContext());

        //recycleView 초기화
        initReadList();

        //읽는 중인 책이 선택되었을때 이벤트 처리
        adapter.setOnItemClickListener(new AdapterRead.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterRead.ViewHolder holder, View view, int position) {
                ItemRead item = adapter.getItem(position);
                int id = item.getId();

                Toast.makeText(getApplicationContext(), "선택된 번호" + id, Toast.LENGTH_SHORT).show();
            }
        });

    }

    //읽는 중인 책 리싸이클러뷰에 초기화
    public void initReadList(){
        String sql = "select _id, title, writer, pub, pic_cover, st_date from " + "read where done=0";

        if(database != null){
            Cursor cursor = database.rawQuery(sql, null);

            if(adapter.getItemCount() == 0){
                for(int i=0 ; i<cursor.getCount() ; i++){
                    cursor.moveToNext();
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String writer = cursor.getString(2);
                    String pub = cursor.getString(3);
                    String pic_cover = cursor.getString(4);
                    String st_date = cursor.getString(5);

                    adapter.addItem(new ItemRead(id, title, writer, pub, pic_cover, st_date));
                }
            } else{
                //즐겨찾기 디비에 정류장 추가되었을때 어뎁터 하나 추가
                if(adapter.getItemCount() < cursor.getCount()){
                    adapter.addItem(new ItemRead(0, "", "", "", "", ""));
                }
                //즐겨찾기 디비에 정류장 삭제되었을때 어뎁터 하나 제거
                else if(adapter.getItemCount() > cursor.getCount()){
                    adapter.subItem(adapter.getItemCount()-1);
                }
                for(int i=0 ; i<cursor.getCount() ; i++){
                    cursor.moveToNext();
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String writer = cursor.getString(2);
                    String pub = cursor.getString(3);
                    String pic_cover = cursor.getString(4);
                    String st_date = cursor.getString(5);

                    adapter.getItem(i).setId(id);
                    adapter.getItem(i).setTitle(title);
                    adapter.getItem(i).setWriter(writer);
                    adapter.getItem(i).setPub(pub);
                    adapter.getItem(i).setPic_cover(pic_cover);
                    adapter.getItem(i).setSt_date(st_date);
                }
            }
        }
        recyclerView.setAdapter(adapter);
    }

}
