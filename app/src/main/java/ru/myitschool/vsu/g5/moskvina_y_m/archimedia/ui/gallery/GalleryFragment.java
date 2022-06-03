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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.List;

import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.DataBase;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.MaterialsDB;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.OlympiadsDB;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.MaterialsActivity;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.OlympiadsList;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.R;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.databinding.FragmentGalleryBinding;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.entities.Olympiads;

public class GalleryFragment extends Fragment {
    ListView lv;
    FavouriteOlympsAdapter foAdapter;
    GalleryFragment galleryFragment;


    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        foAdapter = new FavouriteOlympsAdapter(this.getContext());

        lv = root.findViewById(R.id.fo_list);

        lv.setAdapter(foAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OlympiadsDB odb = foAdapter.getOlympiadsDB(i);
                Intent intent = new Intent(GalleryFragment.this.getActivity(), MaterialsActivity.class);
                intent.putExtra(OlympiadsList.OLYMPIAD_ID, odb.getId());
                startActivity(intent);

            }

        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                foAdapter.remove(i);


                return true;
            }

        });





        return root;
    }

    private void remove(int indexOfChild) {
        DataBase db = new DataBase(galleryFragment.getContext());
        db.deleteOlympiads(indexOfChild);
        List<OlympiadsDB> listo = db.selectAllOlympiads();
        foAdapter.refresh(listo);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new LoadAllOlympiadsTask(this)
                .execute();
    }

    private static class LoadAllOlympiadsTask extends AsyncTask<Void, Void, List<OlympiadsDB>> {

        GalleryFragment galleryFragment;


        public LoadAllOlympiadsTask(GalleryFragment galleryFragment) {
            this.galleryFragment = galleryFragment;

        }

        @Override
        protected List<OlympiadsDB> doInBackground(Void... voids) {
            DataBase db = new DataBase(galleryFragment.getContext());
            List<OlympiadsDB> list = db.selectAllOlympiads();
            return list;
        }

        @Override
        protected void onPostExecute(List<OlympiadsDB> olympiadsDBS) {
            galleryFragment.foAdapter.refresh(olympiadsDBS);


        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}