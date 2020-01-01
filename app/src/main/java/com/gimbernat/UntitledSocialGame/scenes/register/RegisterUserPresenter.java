package com.gimbernat.UntitledSocialGame.scenes.register;

import android.se.omapi.Session;
import android.widget.Toast;

import com.gimbernat.UntitledSocialGame.DataSources.SessionDataSource;
import com.gimbernat.UntitledSocialGame.Entities.UserEntity;
import com.gimbernat.UntitledSocialGame.Helpers.Callback;
import com.gimbernat.UntitledSocialGame.Helpers.GetUserCallback;
import com.gimbernat.UntitledSocialGame.Helpers.RegisterCallback;
import com.gimbernat.UntitledSocialGame.scenes.register.interfaces.IRegisterInteractor;
import com.gimbernat.UntitledSocialGame.scenes.register.interfaces.IRegisterUserActivity;
import com.gimbernat.UntitledSocialGame.scenes.register.interfaces.IRegisterUserPresenter;
import com.google.firebase.auth.FirebaseUser;

public class RegisterUserPresenter implements IRegisterUserPresenter {
    IRegisterUserActivity view;
    IRegisterInteractor interactor;

    public RegisterUserPresenter(IRegisterUserActivity view) {
        this.view = view;
        this.interactor = new RegisterInteractor();
    }


    @Override
    public void onRegisterUser() {
        this.view.showLoading();

        String emailUser = this.view.getTextEmail();
        String nicknameUser = this.view.getTextNicknameUser();
        String passwordUser = this.view.getTextPasswordUser();

        if(!this.interactor.isEmailValid(emailUser)){
            this.view.hideLoading();
            this.view.onError("Email not valid");
            return;
        }

        if(!this.interactor.isPasswordStrong(passwordUser)){
            this.view.hideLoading();
            this.view.onError("Password not valid");
            return;
        }

        if(!this.interactor.isNicknameValid(nicknameUser)){
            this.view.onError("Nickname not valid");
            this.view.hideLoading();
            return;
        }

        this.interactor.registerUser(emailUser, nicknameUser, passwordUser, new GetUserCallback() {
            @Override
            public void onSuccess(UserEntity o) {
                RegisterUserPresenter.this.view.hideLoading();
                RegisterUserPresenter.this.view.navigateToPrivate();
            }

            @Override
            public void onError(String error) {
                RegisterUserPresenter.this.view.onError(error);
            }
        });

    }


    public void sendPasswordRecoveryRequest()
    {
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

    @Override
    public void goToLogin() {
        RegisterUserPresenter.this.view.goToLogin();
    }
}
