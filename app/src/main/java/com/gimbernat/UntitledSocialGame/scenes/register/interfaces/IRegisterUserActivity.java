package com.gimbernat.UntitledSocialGame.scenes.register.interfaces;

public interface IRegisterUserActivity {
    void onButtonPressed();
    void onError(String errorMessage);
    void navigateToPrivate();
    void showLoading();
    void hideLoading();
    void goToLogin();

    String getTextEmail();
    String getTextNicknameUser();
    String getTextPasswordUser();
}
