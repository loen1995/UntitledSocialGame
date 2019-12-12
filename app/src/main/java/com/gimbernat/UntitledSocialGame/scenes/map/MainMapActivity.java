package com.gimbernat.UntitledSocialGame.scenes.map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.gimbernat.UntitledSocialGame.DataSources.UserDataSource;
import com.gimbernat.UntitledSocialGame.Entities.UserEntity;
import com.gimbernat.UntitledSocialGame.Helpers.GetUserCallback;
import com.gimbernat.UntitledSocialGame.R;
import com.gimbernat.UntitledSocialGame.scenes.register.RegisterUserActivity;

public class MainMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);



        UserDataSource.shared.getUserData(new GetUserCallback() {
            @Override
            public void onSuccess(UserEntity user) {
                String nickname = user.getNickname();
                Toast.makeText(MainMapActivity.this, nickname, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
