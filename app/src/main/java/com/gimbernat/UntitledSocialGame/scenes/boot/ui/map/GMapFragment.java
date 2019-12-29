package com.gimbernat.UntitledSocialGame.scenes.boot.ui.map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gimbernat.UntitledSocialGame.R;
import com.gimbernat.UntitledSocialGame.scenes.boot.MainActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

//Imports de localización

public class GMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    //Variables
    private GoogleMap mMap;
    private Marker marcador;
    double lat = 10.0;
    double lng = 20.0;


    //Función que se activa cuando el mapa está preparado
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
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

    public void actualizarUbicacion(Location location) {
        //Si no hay ubicación, la pido
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }


    //Fragment SIN ACTIVITY
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        getMapAsync(this);

        return rootView;
    }


}
