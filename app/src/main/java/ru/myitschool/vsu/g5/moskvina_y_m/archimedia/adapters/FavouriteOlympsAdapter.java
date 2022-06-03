package ru.myitschool.vsu.g5.moskvina_y_m.archimedia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.MaterialsDB;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB.OlympiadsDB;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.R;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.entities.Olympiads;

public class FavouriteOlympsAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<OlympiadsDB> objects;

    public FavouriteOlympsAdapter(Context context) {
        ctx = context;
        objects = new ArrayList<>();
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void refresh(List<OlympiadsDB> folympiads){
        if(folympiads!=null){
            objects.clear();
            objects.addAll(folympiads);
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
            view = lInflater.inflate(R.layout.fo_item, parent, false);
        }

        OlympiadsDB odb = getOlympiadsDB(position);

        TextView foname = view.findViewById(R.id.olympiad_name);
        //TextView universityTV = view.findViewById(R.id.university);

        foname.setText(odb.getName());
        //universityTV.setText(p.getUniversity());

        return view;
    }


    public OlympiadsDB getOlympiadsDB(int position) {
        return (OlympiadsDB) getItem(position);
    }


}
