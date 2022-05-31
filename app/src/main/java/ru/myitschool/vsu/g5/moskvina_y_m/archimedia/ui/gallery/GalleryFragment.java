package ru.myitschool.vsu.g5.moskvina_y_m.archimedia.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.List;

import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.DataBase;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.MaterialsDB;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.OlympiadsDB;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {
    ListView lv;
    FavouriteOlympsAdapter foAdapter;





    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {





        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        lv.setAdapter(foAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OlympiadsDB odb = foAdapter.getOlympiadsDB(i);
                Intent intent = new Intent();

            }


        });


        return root;
    }
    private static class LoadAllOlympiadsTask extends AsyncTask<Void, Void, List<MaterialsDB>> {

        FragmentActivity fragmentActivity;

        @Override
        protected List<MaterialsDB> doInBackground(Void... voids) {
            DataBase db = new DataBase(fragmentActivity);
            List<MaterialsDB> list = db.selectAllMaterials(1);
            return null;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}