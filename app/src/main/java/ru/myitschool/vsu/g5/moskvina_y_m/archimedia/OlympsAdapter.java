package ru.myitschool.vsu.g5.moskvina_y_m.archimedia;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.entities.Olympiads;

public class OlympsAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Olympiads> objects;

    OlympsAdapter(Context context) {
        ctx = context;
        objects = new ArrayList<>();
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void refresh(List<Olympiads> olympiads){
        if(olympiads!=null){
            objects.clear();
            objects.addAll(olympiads);
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
            view = lInflater.inflate(R.layout.list_item, parent, false);
        }

        Olympiads p = getOlympiads(position);

        TextView nameTV = view.findViewById(R.id.name);
        TextView universityTV = view.findViewById(R.id.university);

        nameTV.setText(p.getName());
        universityTV.setText(p.getUniversity());



        return view;
    }


    Olympiads getOlympiads(int position) {

        return ((Olympiads) getItem(position));
    }






}
