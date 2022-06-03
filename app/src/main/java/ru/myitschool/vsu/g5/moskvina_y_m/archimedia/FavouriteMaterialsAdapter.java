package ru.myitschool.vsu.g5.moskvina_y_m.archimedia;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.MaterialsDB;


public class FavouriteMaterialsAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<MaterialsDB> objects;

    public FavouriteMaterialsAdapter(Context context) {
        ctx = context;
        objects = new ArrayList<>();
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void refresh(List<MaterialsDB> fmaterials){
        if(fmaterials!=null){
            objects.clear();
            objects.addAll(fmaterials);
            notifyDataSetChanged();
        }
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.fm_item, parent, false);
        }

        MaterialsDB mdb = getMaterialsDB(position);

        TextView material = view.findViewById(R.id.fmaterial);
        material.setText(mdb.getU_name());




        return view;
    }


        public MaterialsDB getMaterialsDB(int position){
            return ((MaterialsDB) getItem(position));
        }

}






