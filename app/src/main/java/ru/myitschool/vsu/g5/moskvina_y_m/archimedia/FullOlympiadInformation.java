package ru.myitschool.vsu.g5.moskvina_y_m.archimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.entities.Materials;

public class FullOlympiadInformation extends AppCompatActivity {
    List<Materials> materialsList = new ArrayList<>();


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
        String[] mat = extras.getStringArray("materials");
        for(int i=0; i<mat.length; i+=2){
            Materials m = new Materials(mat[i], mat[i+1]);
            materialsList.add(m);

        }


        TextView nameTV = (TextView) findViewById(R.id.name);
        TextView universityV = (TextView) findViewById(R.id.id_university);






    }
}