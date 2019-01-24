package com.example.yechanlee.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    String[] items = {"소녀시대", "걸스데이", "레드벨벳", "여자친구"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. textView 아이디 찾는다, 다른 메소드에서도 사용하므로 전역변수로 선언한다.
        textView = (TextView)findViewById(R.id.textView);
        //2. 스피너 아이디 찾는다
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        //3. 스피너 사용하기 위해서 어댑터(문자열)객체를 생성한다. / 전역변수로 초기화된 배열 items를 사용한다.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items
        );
        //4. 어탭터를 통해 스피너 뷰를 기본으로 설정한다.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //5. 스피너에 어탭터를 적용한다.
        spinner.setAdapter(adapter);

        //6. 스피너에 아이탬이 클릭되었을때 이벤트를 선언한다.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //6-1. 스피너뷰에 있는 아이템이 클릭되었을때, 텍스트뷰에 선택된 아이템을 출력한다.
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(items[position]);
            }

            //6-2. 아무 클릭이 없을때, 선언된 문자열을 출력한다.
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textView.setText("선택 : ");

            }
        });
    }
}
