package ru.myitschool.vsu.g5.moskvina_y_m.archimedia.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.OlympiadsList;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.R;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment  {


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button mathbutton = (Button) root.findViewById(R.id.math);
        Button physbutton = (Button) root.findViewById(R.id.phys);
        Button itbutton = (Button) root.findViewById(R.id.it);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeFragment.this.getActivity(), OlympiadsList.class);


                switch(view.getId()){
                    case R.id.math:
                        intent.putExtra("subject_id", 1);

                        startActivity(intent);
                        break;
                    case R.id.phys:
                        intent.putExtra("subject_id", 2);

                        startActivity(intent);
                        break;
                    case R.id.it:
                        intent.putExtra("subject_id", 3);

                        startActivity(intent);
                        break;
                }

            }
        };
        mathbutton.setOnClickListener(onClickListener);
        physbutton.setOnClickListener(onClickListener);
        itbutton.setOnClickListener(onClickListener);

            return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}