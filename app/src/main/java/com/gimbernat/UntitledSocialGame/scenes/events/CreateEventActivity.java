package com.gimbernat.UntitledSocialGame.scenes.events;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gimbernat.UntitledSocialGame.R;
import com.gimbernat.UntitledSocialGame.scenes.boot.MainActivity;
import com.gimbernat.UntitledSocialGame.scenes.boot.ui.map.GMapFragment;
import com.gimbernat.UntitledSocialGame.scenes.events.interfaces.ICreateEventActivity;
import com.gimbernat.UntitledSocialGame.scenes.register.RegisterUserActivity;

public class CreateEventActivity extends AppCompatActivity implements ICreateEventActivity {

    private CreateEventPresenter presenter;

    Button createEventButton;       //boton crear evento
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        this.presenter = new CreateEventPresenter(this);

        this.createEventButton = this.findViewById(R.id.createEvent);

        this.createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateEventActivity.this.presenter.onCreateEvent();
            }
        });
    }


    public String getTextNameEvent()
    {
        final EditText textField = CreateEventActivity.this.findViewById(R.id.nameEvent);
        return textField.getText().toString();
    }

    public String getTextDescriptionEvent()
    {
        final EditText textField = CreateEventActivity.this.findViewById(R.id.descriptionEvent);
        return textField.getText().toString();
    }

    public String getLatEvent()
    {
        final EditText textField = CreateEventActivity.this.findViewById(R.id.ubicationLatitude);
        return textField.getText().toString();
    }

    public void setLatEvent(String newValue)
    {
        final EditText textField = CreateEventActivity.this.findViewById(R.id.ubicationLatitude);
        textField.setText(newValue);
    }

    public String getLongEvent()
    {
        final EditText textField = CreateEventActivity.this.findViewById(R.id.ubicationLongitude);
        return textField.getText().toString();
    }

    public void setLongEvent(String newValue)
    {
        final EditText textField = CreateEventActivity.this.findViewById(R.id.ubicationLongitude);
        textField.setText(newValue);
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(CreateEventActivity.this, errorMessage, Toast.LENGTH_LONG).show();

    }

    @Override
    public void navigateToPrivate() {
        //TODO code the navigation to the login Activity using an Intent
        Intent view = new Intent( CreateEventActivity.this, MainActivity.class);
        view.setAction(Intent.ACTION_VIEW);
        startActivity(view);
    }

    public void showLoading() {

    }

    public void hideLoading() {

    }
}
