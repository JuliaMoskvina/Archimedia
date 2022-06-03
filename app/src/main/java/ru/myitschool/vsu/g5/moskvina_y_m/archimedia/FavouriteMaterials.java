package ru.myitschool.vsu.g5.moskvina_y_m.archimedia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.DataBase;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.MaterialsDB;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.OlympiadsDB;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.ui.gallery.GalleryFragment;

public class FavouriteMaterials extends AppCompatActivity {
    ListView lv;
    String name;
    public long i;
    FavouriteMaterialsAdapter fmadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_materials);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        name = extras.getString("materials_name");
        i = extras.getLong(OlympiadsList.OLYMPIAD_ID);
        lv = findViewById(R.id.materials);
        fmadapter = new FavouriteMaterialsAdapter(this);
        lv.setAdapter(fmadapter);


        new FavouriteMaterials.LoadAllMaterialsTask(this, i, name)
                .execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MaterialsDB mdb = fmadapter.getMaterialsDB(i);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mdb.getContent()));
                startActivity(intent);
            }
        });


}


    private static class LoadAllMaterialsTask extends AsyncTask<Void, Void, List<MaterialsDB>> {

       FavouriteMaterials activity;
        long i;
        String name;


        public LoadAllMaterialsTask(FavouriteMaterials favouriteMaterials, long i, String name) {
            this.activity = favouriteMaterials;
            this.i = i;
            this.name = name;

        }

        @Override
        protected List<MaterialsDB> doInBackground(Void... voids) {
            DataBase db = new DataBase(activity);
            List<MaterialsDB> list = db.selectAllMaterials(i, name);
            return list;
        }

        @Override
        protected void onPostExecute(List<MaterialsDB> materialsDBS) {
            activity.fmadapter.refresh(materialsDBS);


        }
    }
}


