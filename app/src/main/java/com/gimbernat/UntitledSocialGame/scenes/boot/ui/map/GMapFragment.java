package com.gimbernat.UntitledSocialGame.scenes.boot.ui.map;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gimbernat.UntitledSocialGame.Helpers.Callback;
import com.gimbernat.UntitledSocialGame.R;
import com.gimbernat.UntitledSocialGame.scenes.events.CreateEventActivity;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class GMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    FloatingActionButton goToCreateEvent;

    private GoogleMap mMap;
    private Marker marcador;
    private FusedLocationProviderClient fusedLocationClient;
    double lat=30;
    double lng=30;
    SupportMapFragment mapFragment;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View rootView = super.onCreateView(inflater, container, savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_gmap, container, false);
        //getMapAsync(this);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.gmap);
        if(mapFragment == null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.gmap, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        //Toast.makeText(getActivity() , this.getId(), Toast.LENGTH_SHORT).show();


        this.goToCreateEvent = (FloatingActionButton) view.findViewById(R.id.fab);
        //evento que escucha cuando se hace click en el boton de ir a crear evento
        this.goToCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GMapFragment.this.goToCreateEvent();
            }
        });

        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);

        agregarMarcador(lat,lng);
    }

    //Botón centrar
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getActivity() , "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    //Indicador Ubicación
    @Override
    public void onMyLocationClick(@NonNull Location location) {
        //Toast.makeText(getActivity(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
        //getLastLocation("null");
    }

    //Función para coger la última ubicación
    private class MyPosition {
        double lat;
        double lng;
        public MyPosition(double lat, double lng){
            this.lat = lat;
            this.lng = lng;
        }
    }
    private void getLastLocation(final Callback callback){
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        //A veces tira null, pongo esto para control de errores
                        if (location != null) {
                            Toast.makeText(getActivity(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
                            GMapFragment.this.lat = location.getLatitude();
                            GMapFragment.this.lng = location.getLongitude();
                            //agregarMarcador(lat, lng);
                            callback.onSuccess(new MyPosition(lat,lng));
                        }
                    }
                });
    }


    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16); //Situo la cámara en las coordenadas
        if (marcador != null) marcador.remove();
        //Creación del marcador
        marcador = mMap.addMarker(new MarkerOptions()
                        .position(coordenadas)
                        .title("Mi Posición Actual")
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)) --> Esto no me acaba de ir pero es para poner un icono al marcador
        );
        //Animación para enfocar donde está la cámara
        mMap.animateCamera(miUbicacion);
    }


    public void goToCreateEvent(){
        //go to Register User


        this.getLastLocation(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                Intent view = new Intent(GMapFragment.this.getActivity(), CreateEventActivity.class);
                view.setAction(Intent.ACTION_VIEW);
                MyPosition position = (MyPosition) responseObject;
                view.putExtra(CreateEventActivity.K_PARAM_LAT, position.lat);
                view.putExtra(CreateEventActivity.K_PARAM_LNG, position.lng);

                startActivity(view);
            }

            @Override
            public void onError() {

            }
        });
    }
}
