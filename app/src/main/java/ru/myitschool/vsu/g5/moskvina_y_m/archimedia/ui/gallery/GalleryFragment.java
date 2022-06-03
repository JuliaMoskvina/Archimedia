package ru.myitschool.vsu.g5.moskvina_y_m.archimedia.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.DataBase;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.MaterialsDB;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.OlympiadsDB;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.FavouriteMaterialsAdapter;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.MaterialsActivity;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.OlympiadsList;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.R;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {
    ListView lv;
    FavouriteOlympsAdapter foAdapter;

    FavouriteMaterialsAdapter fmadapter;


    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        foAdapter = new FavouriteOlympsAdapter(this.getContext());
        fmadapter = new FavouriteMaterialsAdapter(this.getContext());



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


              remove(i);



                return true;
            }
            private void remove(int i) {

                DataBase db = new DataBase(ccontext );
                db.deleteOlympiads(i);
                db.deleteMaterials(i);




                foAdapter.notifyDataSetChanged();
                foAdapter.notifyDataSetInvalidated();




                //DataBase db = new DataBase(galleryFragment.getContext());
                //db.deleteOlympiads(i);
                //List<OlympiadsDB> listo = db.selectAllOlympiads();
                //foAdapter.refresh(listo);
            }


        });






        return root;
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
    private Context ccontext;
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
       ccontext = context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}