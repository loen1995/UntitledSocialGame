package com.gimbernat.UntitledSocialGame.scenes.boot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.gimbernat.UntitledSocialGame.DataSources.SessionDataSource;
import com.gimbernat.UntitledSocialGame.R;
import com.gimbernat.UntitledSocialGame.scenes.login.LoginActivity;
import com.gimbernat.UntitledSocialGame.scenes.register.RegisterUserActivity;


interface IBootActivity {
    void navigateToPublic();
    void navigateToPrivate();
}

public class BootActivity extends AppCompatActivity implements IBootActivity {

    private int GPS_PERMISSION_CODE = 1;
    private boolean permiso;
    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        checkPermission();
    }

    public void navigateToRegister() {
        //go to Register User
        Intent view = new Intent(BootActivity.this, RegisterUserActivity.class);
        view.setAction(Intent.ACTION_VIEW);
        startActivity(view);
    }

    @Override
    public void navigateToPublic() {
        //go to Login
        Intent view = new Intent(BootActivity.this, LoginActivity.class);
        view.setAction(Intent.ACTION_VIEW);
        startActivity(view);
    }

    @Override
    public void navigateToPrivate() {
        // go to Map
        Intent view = new Intent(BootActivity.this, MainActivity.class);
        view.setAction(Intent.ACTION_VIEW);
        startActivity(view);
    }

    /*                           PARTE DE PERMISOS                         */
    public void checkPermission(){
        //Comprobar los Permisos de GPS
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            checkUser();
            //Toast.makeText(this, "Ya has aceptado los permisos", Toast.LENGTH_SHORT).show();

        } else {
            // Show rationale and request permission.
            //Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
            requestGPSPermission();
        }
    }

    public void requestGPSPermission(){
        //Este metodo comprueba si ya le hemos pedido permisos al usuario
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
            new AlertDialog.Builder(this)
                    .setTitle("Autorización de Permisos")
                    .setMessage("Este permiso se necesita para acceder a la ubicación de tu dispositivo")
                    //Botón de aceptar
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(BootActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_PERMISSION_CODE);
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
                //Toast.makeText(this, "Permisos CONCEDIDOS", Toast.LENGTH_SHORT).show();
                //Si los permisos son concedidos procedemos a navegar
                checkUser();
            }else{
                //Toast.makeText(this, "Permisos DENEGADOS", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void checkUser(){
        if (SessionDataSource.shared.isUserLogedIn()) {
            this.navigateToPrivate();
        } else {
            this.navigateToPublic();
        }
    }


}
