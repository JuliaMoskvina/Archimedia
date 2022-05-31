package ru.myitschool.vsu.g5.moskvina_y_m.archimedia;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.entities.Materials;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.entities.Olympiads;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.ui.home.HomeFragment;

public class OlympiadsList extends AppCompatActivity {
    private final String baseUrl = "https://raw.githubusercontent.com/JuliaMoskvina/files_for_archimedia/main/";
    private ListView listView;
    private int subject_id;
    private OlympsAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olympiads_list);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        subject_id = extras.getInt("subject_id");
        listView = (ListView) findViewById(R.id. listview);
        adapter = new OlympsAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Olympiads olympiads = adapter.getOlympiads(i);
                Intent intent = new Intent(OlympiadsList.this, FullOlympiadInformation.class);
                String university = olympiads.getUniversity();
                String name = olympiads.getName();
                //Log.i("Name:", name );
                String url= olympiads.getUrl();
                //.i("subject", url);
                Date date = olympiads.getDate();
                //Log.i("date", String.valueOf(date));
                List<Materials> materials = olympiads.getMaterials();
                if(materials==null){
                    materials = new ArrayList<>();
                }
                String[] mat = new String[materials.size()*2];
                for(int j=0; j<mat.length; j+=2 ){
                    mat[j]=materials.get(j/2).getName();
                    mat[j+1] = materials.get(j/2).getContent();
                }
                intent.putExtra("materials", mat);

                String[] data = new String [] {name, university, url};
                intent.putExtra("data", data);
                startActivity(intent);



            }
        });
        getOlympiads();




    }
    public void getOlympiads(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OlympiadsService olympiadsService = retrofit.create(OlympiadsService.class);
        Call<List<Olympiads>> call = olympiadsService.getOlympiads(subject_id);
        call.enqueue(new Callback<List<Olympiads>>() {
            @Override
            public void onResponse(Call<List<Olympiads>> call, Response<List<Olympiads>> response) {
                if(response.isSuccessful()){
                    adapter.refresh(response.body());
                }
                else{
                    Log.e("OlympiadsListActitivty", "Fail load olympiads!");
                    Toast.makeText(OlympiadsList.this, "Не получилось загрузить...", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Olympiads>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(OlympiadsList.this, "Ошибка в процессе загрузки.", Toast.LENGTH_SHORT).show();


            }
        });

    }
}