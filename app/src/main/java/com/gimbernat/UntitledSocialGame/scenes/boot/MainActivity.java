package com.gimbernat.UntitledSocialGame.scenes.boot;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.gimbernat.UntitledSocialGame.R;
import com.gimbernat.UntitledSocialGame.scenes.boot.ui.map.GMapFragment;
import com.gimbernat.UntitledSocialGame.scenes.events.CreateEventActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public FragmentManager fm = getSupportFragmentManager();
    public GMapFragment fragment;

    FloatingActionButton goToCreateEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = (GMapFragment) fm.findFragmentById(R.id.nav_host_fragment).getChildFragmentManager().findFragmentById(R.id.gmap);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.gmap, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //setear el boton de ir a crear evento
        this.goToCreateEvent = this.findViewById(R.id.fab);
        //evento que escucha cuando se hace click en el boton de ir a crear evento
        this.goToCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MainActivity.this.fragment == null){
                    Toast.makeText(MainActivity.this , "ES NULO", Toast.LENGTH_SHORT).show();
                }

                GMapFragment savedFragment = (GMapFragment) fm.findFragmentByTag("MAPA");

                if (savedFragment == null) {
                    GMapFragment gmap = new GMapFragment();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.add(R.id.gmap, gmap,"MAPA");

                    fragmentTransaction.commit();
                    fragment = (GMapFragment) getSupportFragmentManager().findFragmentByTag("MAPA");
                    fragment.hola();
                    //NO VAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                }

            MainActivity.this.goToCreateEvent();
            }
        });


    }

    public void goToCreateEvent(){
        //go to Register User
        Intent view = new Intent(MainActivity.this, CreateEventActivity.class);
        view.setAction(Intent.ACTION_VIEW);
        startActivity(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
