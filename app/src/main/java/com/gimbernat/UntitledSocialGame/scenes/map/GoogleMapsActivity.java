package com.gimbernat.UntitledSocialGame.scenes.map;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gimbernat.UntitledSocialGame.R;
//Imports de localización
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;


/*
 *
 *      CÓDIGO OBSOLETO --> LA PARTE DE MAPAS AHORA SE HACE EN EL FRAGMENT DENTRO DE SCENES/BOOT/MAP/GMAPFRAGMENT
 *
 */


public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    //Variables
    private GoogleMap mMap;
    private Marker marcador;
    private int GPS_PERMISSION_CODE = 1;
    double lat = 0.0;
    double lng = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        //Obtiene el SupportMapFragment y recibe cuando el mapa esta preparado para usarse.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    //Función que se activa cuando el mapa está preparado
    @Override
    public void onMapReady(GoogleMap googleMap) {

        //Comprobar los Permisos de GPS
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //mMap.setMyLocationEnabled(true);
            Toast.makeText(this, "Ya has aceptado los permisos", Toast.LENGTH_SHORT).show();
        } else {
            // Show rationale and request permission.
            Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
            requestGPSPermission();
        }
        mMap = googleMap;
        miUbicacion();
    }

    private void requestGPSPermission() {
        //Este metodo comprueba si ya le hemos pedido permisos al usuario
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
            new AlertDialog.Builder(this)
                    .setTitle("Autorización de Permisos")
                    .setMessage("Este permiso se necesita para acceder a la ubicación de tu dispositivo")
                    //Botón de aceptar
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(GoogleMapsActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_PERMISSION_CODE);
                        }
                    })
                    //Boton denegar
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == GPS_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permisos CONCEDIDOS", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Permisos DENEGADOS", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void agregarMarcador(double lat, double lng) {
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

    private void actualizarUbicacion(Location location) {
        //Si no hay ubicación, la pido
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }

    //Listener de localización
    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void miUbicacion() {
        //Permisos obligatorios
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //Llamo a la función para coger la ubicación más reciente
        actualizarUbicacion(location);
        //Pido actualización de la ubcación cada 15 seg
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,locListener);
    }
}
