package com.example.yechanlee.callintent;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String receiver = editText.getText().toString();
                //인텐트 객체를 만든다.
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + receiver));
                //인텐트 객체를 시스템으로 보낸다.
                startActivity(intent);
            }
        });
    }
}
