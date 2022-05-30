package ru.myitschool.vsu.g5.moskvina_y_m.archimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Date;

public class FullOlympiadInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_olympiad_information);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String[] data = extras.getStringArray("data");
        String name = data[0];
        String university = data[1];
        String url = data[2];
        TextView nameTV = (TextView) findViewById(R.id.name);
        nameTV.setText(name);


    }
}