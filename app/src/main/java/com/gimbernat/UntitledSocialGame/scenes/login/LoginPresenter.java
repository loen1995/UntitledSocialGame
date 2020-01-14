package com.gimbernat.UntitledSocialGame.scenes.login;

import android.content.Intent;
import android.widget.Toast;

import com.gimbernat.UntitledSocialGame.DataSources.SessionDataSource;
import com.gimbernat.UntitledSocialGame.Helpers.Callback;
import com.gimbernat.UntitledSocialGame.scenes.boot.BootActivity;
import com.gimbernat.UntitledSocialGame.scenes.login.interfaces.ILoginPresenter;
import com.gimbernat.UntitledSocialGame.scenes.login.interfaces.ILoginActivity;
import com.gimbernat.UntitledSocialGame.scenes.register.RegisterUserActivity;

public class LoginPresenter implements ILoginPresenter {

    private ILoginActivity view;

    public LoginPresenter(ILoginActivity view) { this.view = view;}

    public void onButtonPressed(){

        this.view.showLoading();

        String pass = LoginPresenter.this.view.getPass();
        String mail = LoginPresenter.this.view.getTextEmail();
        if (pass.isEmpty()|| mail.isEmpty()){
            LoginPresenter.this.view.onError("Obligatorio rellenar todos los campos.");
            LoginPresenter.this.view.hideLoading();
        }else {
            SessionDataSource.shared.signInEmailAndPassword(mail, pass, new Callback() {
                @Override
                public void onSuccess(Object responseObject) {
                    LoginPresenter.this.view.hideLoading();

                    LoginPresenter.this.view.navigateToBootActivity();
                }

                @Override
                public void onError() {
                    LoginPresenter.this.view.onError("Error en el Login");
                    LoginPresenter.this.view.hideLoading();
                }
            });
        }
    }

    public void setGoToRegistry() {
        LoginPresenter.this.view.navigateToRegister();
    }
}
