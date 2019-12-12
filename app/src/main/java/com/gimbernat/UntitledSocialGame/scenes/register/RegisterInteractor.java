package com.gimbernat.UntitledSocialGame.scenes.register;

import android.util.Patterns;

import com.gimbernat.UntitledSocialGame.DataSources.SessionDataSource;
import com.gimbernat.UntitledSocialGame.DataSources.UserDataSource;
import com.gimbernat.UntitledSocialGame.Entities.UserEntity;
import com.gimbernat.UntitledSocialGame.Helpers.GetUserCallback;
import com.gimbernat.UntitledSocialGame.Helpers.RegisterCallback;
import com.gimbernat.UntitledSocialGame.scenes.register.interfaces.IRegisterInteractor;
import com.google.firebase.auth.FirebaseUser;

public class RegisterInteractor implements IRegisterInteractor {
    @Override
    public Boolean isEmailValid(String email) {
       return email.length() > 1 && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public Boolean isPasswordStrong(String password) {
        return password.length() > 6;
    }

    @Override
    public Boolean isNicknameValid(String nickname) {
        return  nickname.length() > 6;
    }

    @Override
    public void registerUser(String email, final String nickname, String password, final GetUserCallback callback) {
        SessionDataSource.shared.RegisterUser(email, password, new RegisterCallback() {
            @Override
            public void onSuccess(Object o) {
                FirebaseUser user = (FirebaseUser) o;
                UserEntity newUser = new UserEntity(user.getUid(), nickname);
                UserDataSource.shared.createUserWith(newUser,callback);
            }

            @Override
            public void onError(String error) {
                callback.onError(error);
            }
        });
    }

}
