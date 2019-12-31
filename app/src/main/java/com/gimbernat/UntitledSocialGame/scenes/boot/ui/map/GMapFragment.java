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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gimbernat.UntitledSocialGame.R;
import com.gimbernat.UntitledSocialGame.scenes.boot.MainActivity;
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


public class GMapFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private GoogleMap mMap;
    private Marker marcador;
    private FusedLocationProviderClient fusedLocationClient;
    double lat;
    double lng;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
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
        getLastLocation();
    }

    //Función para coger la última ubicación
    public void getLastLocation(){
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        //A veces tira null, pongo esto para control de errores
                        if (location != null) {
                            Toast.makeText(getActivity(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
                            lat = location.getLatitude();
                            lng = location.getLongitude();
                            //agregarMarcador(lat, lng);
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

}
