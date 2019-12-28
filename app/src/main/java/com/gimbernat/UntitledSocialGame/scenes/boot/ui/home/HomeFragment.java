package com.gimbernat.UntitledSocialGame.scenes.boot.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gimbernat.UntitledSocialGame.R;
import com.gimbernat.UntitledSocialGame.scenes.boot.BootActivity;
import com.gimbernat.UntitledSocialGame.scenes.boot.MainActivity;
import com.gimbernat.UntitledSocialGame.scenes.map.GoogleMapsActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private GoogleMapsActivity map;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        //Esto es lo que carga el layout del menu, pero no se inicializa correctamente el activity del GoogleMapsActivity
        View root = inflater.inflate(R.layout.activity_google_maps, container, false);

        //Esto es lo que te abre el mapam y te pisa el menu
//        Intent intent = new Intent(getActivity(), GoogleMapsActivity.class);
//        startActivity(intent);



        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}