package com.gimbernat.UntitledSocialGame.scenes.register.interfaces;

import com.gimbernat.UntitledSocialGame.Helpers.GetUserCallback;
import com.gimbernat.UntitledSocialGame.Helpers.RegisterCallback;

public interface IRegisterInteractor {
    Boolean isEmailValid(String email);
    Boolean isPasswordStrong(String password);
    Boolean isNicknameValid (String nickname);
    void registerUser(String email, String nickname, String password, GetUserCallback callback);
}
