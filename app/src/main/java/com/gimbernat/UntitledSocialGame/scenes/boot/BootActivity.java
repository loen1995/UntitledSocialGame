package com.gimbernat.UntitledSocialGame.scenes.boot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gimbernat.UntitledSocialGame.DataSources.SessionDataSource;
import com.gimbernat.UntitledSocialGame.R;
import com.gimbernat.UntitledSocialGame.scenes.login.LoginActivity;
import com.gimbernat.UntitledSocialGame.scenes.main.MainActivity;
import com.gimbernat.UntitledSocialGame.scenes.map.MainMapActivity;


interface IBootActivity {
    void navigateToPublic();
    void navigateToPrivate();
}

public class BootActivity extends AppCompatActivity implements IBootActivity {
    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);

       // this.navigateToPublic();


        if ( SessionDataSource.shared.isUserLogedIn()) {
            this.navigateToPrivate();
        } else {
            this.navigateToPublic();
        }

    }

    @Override
    public void navigateToPublic() {
        //go to Login
        Intent view = new Intent( BootActivity.this, LoginActivity.class);
        view.setAction(Intent.ACTION_VIEW);
        startActivity(view);
    }

    @Override
    public void navigateToPrivate() {
        // go to Map
        Intent view = new Intent( BootActivity.this, MainActivity.class);
        view.setAction(Intent.ACTION_VIEW);
        startActivity(view);
    }
}
