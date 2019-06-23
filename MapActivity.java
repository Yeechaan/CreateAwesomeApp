package com.lee.holatayo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.Utmk;

public class MapActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MapViewFragment fragment = new MapViewFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
            }
        });

        textView = (TextView) findViewById(R.id.textView3);

        LatLng latLng = new LatLng(37.3898584, 126.9509936);
        Utmk utmk = Utmk.valueOf(latLng);

        textView.setText("tmX: " + utmk.x + ", tmY: " + utmk.y);

        Toast.makeText(getApplicationContext(),
                "tmX: " + utmk.x + ", tmY: " + utmk.y,
                Toast.LENGTH_SHORT).show();

    }
}
