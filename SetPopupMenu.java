package com.lee.oneweekonebook;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class WantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want);

        Button btn_add = (Button) findViewById(R.id.btn_popupMenu);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //처리할 일들 팝업으로 선택
                PopupMenu popupMenuType = new PopupMenu(getApplicationContext(), v);
                popupReadSelected(popupMenuType);
            }
        });

    }

    public void popupReadSelected(PopupMenu popupMenu){
        //option_type.xml 팝업메뉴 연동하기
        getMenuInflater().inflate(R.menu.option_type, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.m1:
		//추가하기 처리할 일들을 작성
                        Toast.makeText(getApplicationContext(), "추가하기", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.m2:
                        //수정하기 처리할 일들을 작성
                        Toast.makeText(getApplicationContext(), "수정하기", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.m3:
                        //삭제하기 처리할 일들을 작성
                        Toast.makeText(getApplicationContext(), "삭제하기", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

}
