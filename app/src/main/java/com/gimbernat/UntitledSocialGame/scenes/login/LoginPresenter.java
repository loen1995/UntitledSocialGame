package com.gimbernat.UntitledSocialGame.scenes.login;

import android.widget.Toast;

import com.gimbernat.UntitledSocialGame.DataSources.SessionDataSource;
import com.gimbernat.UntitledSocialGame.Helpers.Callback;
import com.gimbernat.UntitledSocialGame.scenes.login.interfaces.ILoginPresenter;
import com.gimbernat.UntitledSocialGame.scenes.login.interfaces.ILoginActivity;

public class LoginPresenter implements ILoginPresenter {

    private ILoginActivity view;

    public LoginPresenter(ILoginActivity view) { this.view = view;}

    public void onButtonPressed(){

        this.view.showLoading();

        SessionDataSource.shared.SignIn(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                LoginPresenter.this.view.hideLoading();
                LoginPresenter.this.view.navigateToBootActivity();

            }

            @Override
            public void onError() {
                LoginPresenter.this.view.hideLoading();
            }
        });
    }
}
