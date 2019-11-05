package com.gimbernat.UntitledSocialGame.scenes.register;

import android.se.omapi.Session;

import com.gimbernat.UntitledSocialGame.DataSources.SessionDataSource;
import com.gimbernat.UntitledSocialGame.Helpers.Callback;
import com.gimbernat.UntitledSocialGame.scenes.register.interfaces.IRegisterUserActivity;
import com.gimbernat.UntitledSocialGame.scenes.register.interfaces.IRegisterUserPresenter;

public class RegisterUserPresenter implements IRegisterUserPresenter {
    IRegisterUserActivity view;

    public RegisterUserPresenter(IRegisterUserActivity view) {
        this.view = view;
    }

    @Override
    public void sendPasswordRecoveryRequest() {
        //TODO Call to the SessionDataSource.shared. function to be defined

        this.view.showLoading();
        String emailValue = "";
        SessionDataSource.shared.resetWithEmail(emailValue, new Callback() {
            @Override
            public void onSuccess(Object responseObject) {

            }

            @Override
            public void onError() {

            }
        });




    }
}
