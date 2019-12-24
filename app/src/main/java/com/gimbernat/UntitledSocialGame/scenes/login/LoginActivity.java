package com.gimbernat.UntitledSocialGame.scenes.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.gimbernat.UntitledSocialGame.DataSources.SessionDataSource;
import com.gimbernat.UntitledSocialGame.Helpers.Callback;
import com.gimbernat.UntitledSocialGame.R;
import com.gimbernat.UntitledSocialGame.scenes.boot.BootActivity;
import com.gimbernat.UntitledSocialGame.scenes.login.interfaces.ILoginActivity;
import com.gimbernat.UntitledSocialGame.scenes.map.MainMapActivity;
import com.gimbernat.UntitledSocialGame.scenes.register.RegisterUserActivity;
import com.gimbernat.UntitledSocialGame.scenes.register.interfaces.IRegisterUserActivity;

public class LoginActivity extends AppCompatActivity implements ILoginActivity {
    // Presenter
    private LoginPresenter presenter;

    private Button login;
    private Button goToRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Set UI
        this.login = this.findViewById(R.id.buttonLogin);
        this.goToRegistry = this.findViewById(R.id.goToRegister);
        //Set Presenter
        this.presenter = new LoginPresenter(this);

        //Set Events
        this.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.presenter.onButtonPressed();
            }
        });
        this.hideLoading();

        this.goToRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.presenter.setGoToRegistry();
            }
        });
        this.hideLoading();
    }


    public String getTextEmail()
    {
        final EditText textField = LoginActivity.this.findViewById(R.id.emailLogin);
        return textField.getText().toString();
    }

    public String getPass()
    {
        final EditText textField = LoginActivity.this.findViewById(R.id.password);
        return textField.getText().toString();
    }
    @Override
    public void onError(String errorMessage) {
        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToBootActivity() {

        Intent view = new Intent( LoginActivity.this, BootActivity.class);
        view.setAction(Intent.ACTION_VIEW);
        startActivity(view);
    }

    public void navigateToRegister(){
        //go to Register User
        Intent view = new Intent(LoginActivity.this, RegisterUserActivity.class);
        view.setAction(Intent.ACTION_VIEW);
        startActivity(view);
    }
    @Override
    public void showLoading() {

       LoginActivity.this.runOnUiThread(new Runnable() {
           @Override
           public void run() {
               LoginActivity.this.findViewById(R.id.spinner).setVisibility(View.VISIBLE);
           }
       });

    }

    @Override
    public void hideLoading() {
        LoginActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LoginActivity.this.findViewById(R.id.spinner).setVisibility(View.GONE);
            }
        });
    }

}
