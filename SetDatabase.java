package com.lee.oneweekonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SplashActivity extends AppCompatActivity {
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        database = openOrCreateDatabase("book.db", MODE_PRIVATE, null);


        setDB();
    }

    public void setDB(){

        String sqlTable = "SELECT name FROM sqlite_master WHERE type='table'";
        Cursor cursor = database.rawQuery(sqlTable, null);

        //처음 어플 실행 후 디비를 만들면 android_metadata 테이블 하나 자동 생성
        if(cursor.getCount() == 1 && database != null){
            setTableInit();
        }
    }

    //디비, 테이블 생성
    public void setTableInit(){

        //처음 어플 실행시 만들 디비 및 테이블
        if(database != null){

            String sqlCreateWant = "create table " + "want" + "(_id integer PRIMARY KEY autoincrement, title text, writer text, pub text, pic_cover text)";
            database.execSQL(sqlCreateWant);

            String sqlCreateRead = "create table " + "read" + "(_id integer PRIMARY KEY autoincrement, title text, writer text, pub text, pic_cover text, st_date date, fi_date date, fi_review text, fi_memo text, done integer default 0, save integer default 0)";
            database.execSQL(sqlCreateRead);

            String sqlCreateDone = "create table " + "book" +"(_id integer PRIMARY KEY autoincrement, sub_title text, page integer, contents text, review text, read_id integer, foreign key(read_id) references read(_id))";
            database.execSQL(sqlCreateDone);
        }
    }
}
