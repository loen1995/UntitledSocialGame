package com.gimbernat.UntitledSocialGame.scenes.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gimbernat.UntitledSocialGame.R;
import com.gimbernat.UntitledSocialGame.scenes.boot.MainActivity;
import com.gimbernat.UntitledSocialGame.scenes.login.LoginActivity;
import com.gimbernat.UntitledSocialGame.scenes.register.interfaces.IRegisterUserActivity;

public class RegisterUserActivity extends AppCompatActivity implements IRegisterUserActivity {

    private RegisterUserPresenter presenter;

    Button forgotPasswordButton; //boton recuperar password
    Button registerButton;       //boton registrarse usuario
    Button goToLogin;// botón ir a login

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Set Presenter
        this.presenter = new RegisterUserPresenter(this);

        //recuperar password
        //set button recoverPassword
        /*this.forgotPasswordButton = this.findViewById(R.id.recoverPassword);

        this.forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUserActivity.this.onButtonPressed();
            }
        });
        */
        //registrar usuario
        //set button registerUser
        this.registerButton = this.findViewById(R.id.registerUser);

        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUserActivity.this.presenter.onRegisterUser();
            }
        });

        // set button go to Lrogin
        this.goToLogin = this.findViewById(R.id.goToLogin);

        this.goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUserActivity.this.presenter.goToLogin();
            }
        });
    }

    private String getTextField()
    {
        final EditText textField = RegisterUserActivity.this.findViewById(R.id.emailUser);
        return textField.getText().toString();
    }

    public String getTextEmail()
    {
        final EditText textField = RegisterUserActivity.this.findViewById(R.id.emailUser);
        return textField.getText().toString();
    }

    public String getTextNicknameUser()
    {
        final EditText textField = RegisterUserActivity.this.findViewById(R.id.nicknameUser);
        return textField.getText().toString();
    }

    public String getTextPasswordUser()
    {
        final EditText textField = RegisterUserActivity.this.findViewById(R.id.passwordUser);
        return textField.getText().toString();
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
    public void navigateToPrivate() {
//        this.onError("Success");
        //TODO code the navigation to the login Activity using an Intent
        Intent view = new Intent( RegisterUserActivity.this, MainActivity.class);
        view.setAction(Intent.ACTION_VIEW);
        startActivity(view);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
    @Override
    public void goToLogin(){
        //go to Register User
        Intent view = new Intent(RegisterUserActivity.this, LoginActivity.class);
        view.setAction(Intent.ACTION_VIEW);
        startActivity(view);
    }
}
