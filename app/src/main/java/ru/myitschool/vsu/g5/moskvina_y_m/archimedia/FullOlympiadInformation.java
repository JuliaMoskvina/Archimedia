package ru.myitschool.vsu.g5.moskvina_y_m.archimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.DataBase;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.entities.Materials;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.entities.Olympiads;

public class FullOlympiadInformation extends AppCompatActivity {
    List<Materials> materialsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_olympiad_information);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        long date = extras.getLong("date");
        int subj_id = extras.getInt("subj_id");
        int id = extras.getInt("id");
        String[] data = extras.getStringArray("data");
        String name = data[0];
        String university = data[1];
        String url = data[2];
        String[] mat = extras.getStringArray("materials");
        for(int i=0; i<mat.length; i+=3){
            Materials m = new Materials(mat[i], mat[i+1], mat [i+2]);
            materialsList.add(m);

        }

        TextView nameo = (TextView) findViewById(R.id.name_of_olympiad);
        nameo.setText(name);

        TextView ouniversity = (TextView) findViewById(R.id.uni);

        ouniversity.setText(university);


        Button like = (Button)findViewById(R.id.like);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Toast.makeText(getApplicationContext(), "Олимпиада в избранном!", Toast.LENGTH_SHORT).show();
              new AddToFavouriteTask(FullOlympiadInformation.this, new Olympiads(id, name, url, date, university, subj_id, materialsList))
                      .execute();

            }
        };
        like.setOnClickListener(onClickListener);


    }
    private void olympiadAdd(){
        Toast.makeText(this, "Олимпиада успешно добавлена!",Toast.LENGTH_SHORT).show();

    }
    private static class AddToFavouriteTask extends AsyncTask<Void, Void, Void>{
        private FullOlympiadInformation activity;
        private Olympiads olympiads;

        public AddToFavouriteTask(FullOlympiadInformation activity, Olympiads olympiads) {
            this.activity = activity;
            this.olympiads = olympiads;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            DataBase db = new DataBase(activity);
            long olymp_id =  db.insert_olympiad(olympiads.getName(), olympiads.getUniversity(), olympiads.getUrl(), olympiads.getSubj_id(), olympiads.getDate());
            for (Materials m : olympiads.getMaterials()){
                db.insert_material(m.getName(), m.getContent(), olymp_id, m.getU_name());
            }




            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            activity.olympiadAdd();
        }
    }
}