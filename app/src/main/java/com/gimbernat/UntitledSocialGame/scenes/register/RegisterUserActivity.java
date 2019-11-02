package com.gimbernat.UntitledSocialGame.scenes.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gimbernat.UntitledSocialGame.R;
import com.gimbernat.UntitledSocialGame.scenes.register.interfaces.IRegisterUserActivity;

public class RegisterUserActivity extends AppCompatActivity implements IRegisterUserActivity {

    Button forgotPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.forgotPasswordButton = this.findViewById(R.id.recoverPassword);

        this.forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUserActivity.this.onButtonPressed();
            }
        });

    }


    @Override
    public void onButtonPressed() {
        Toast.makeText(RegisterUserActivity.this, "Hello World", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(RegisterUserActivity.this, errorMessage, Toast.LENGTH_LONG).show();

    }

    @Override
    public void navigateToLoginScene() {
        //TODO code the navigation to the login Activity using an Intent
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
