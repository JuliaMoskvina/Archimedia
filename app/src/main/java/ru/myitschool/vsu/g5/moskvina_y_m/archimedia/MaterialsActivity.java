package ru.myitschool.vsu.g5.moskvina_y_m.archimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.ui.home.HomeFragment;

public class MaterialsActivity extends AppCompatActivity {
    long i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        i = extras.getLong(OlympiadsList.OLYMPIAD_ID);

        setContentView(R.layout.activity_materials);
        Button books =  findViewById(R.id.books);
        Button videos = findViewById(R.id.videos);
        Button tasks = findViewById(R.id.tasks);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MaterialsActivity.this, FavouriteMaterials.class);
                intent.putExtra(OlympiadsList.OLYMPIAD_ID, i);


                switch(view.getId()){
                    case R.id.books:
                        intent.putExtra("materials_name", "books");

                        startActivity(intent);
                        break;
                    case R.id.videos:
                        intent.putExtra("materials_name", "video");

                        startActivity(intent);
                        break;
                    case R.id.tasks:
                        intent.putExtra("materials_name", "tasks");

                        startActivity(intent);
                        break;
                }

            }
        };
        books.setOnClickListener(onClickListener);
        videos.setOnClickListener(onClickListener);
        tasks.setOnClickListener(onClickListener);


    }
}