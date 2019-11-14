package com.gimbernat.UntitledSocialGame.scenes.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.gimbernat.UntitledSocialGame.DataSources.SessionDataSource;
import com.gimbernat.UntitledSocialGame.Helpers.Callback;
import com.gimbernat.UntitledSocialGame.R;
import com.gimbernat.UntitledSocialGame.scenes.boot.BootActivity;
import com.gimbernat.UntitledSocialGame.scenes.login.interfaces.ILoginActivity;
import com.gimbernat.UntitledSocialGame.scenes.map.MainMapActivity;
import com.gimbernat.UntitledSocialGame.scenes.register.interfaces.IRegisterUserActivity;

public class LoginActivity extends AppCompatActivity implements ILoginActivity {
    // Presenter
    private LoginPresenter presenter;

    private Button buttonTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Set UI
        this.buttonTest = this.findViewById(R.id.loginButton);
        //Set Presenter
        this.presenter = new LoginPresenter(this);

        //Set Events
        this.buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.presenter.onButtonPressed();
            }
        });
        this.hideLoading();
    }


    @Override
    public void onError(String errorMessage) {

    }

    @Override
    public void navigateToBootActivity() {

        Intent view = new Intent( LoginActivity.this, BootActivity.class);
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
