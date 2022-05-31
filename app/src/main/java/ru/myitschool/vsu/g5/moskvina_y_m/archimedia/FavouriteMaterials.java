package ru.myitschool.vsu.g5.moskvina_y_m.archimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.DataBase;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.MaterialsDB;

public class FavouriteMaterials extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_materials);
    }
    private static class LoadAllMaterialsTask extends AsyncTask<Void, Void, List<MaterialsDB>> {
        private FavouriteMaterials activity;

        @Override
        protected List<MaterialsDB> doInBackground(Void... voids) {
            DataBase db = new DataBase(activity);
            List<MaterialsDB> list = db.selectAllMaterials(1);
            return null;
        }


        }
}