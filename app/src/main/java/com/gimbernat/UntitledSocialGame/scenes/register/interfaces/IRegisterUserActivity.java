package com.gimbernat.UntitledSocialGame.scenes.register.interfaces;

public interface IRegisterUserActivity {
    void onButtonPressed();
    void onError(String errorMessage);
    void navigateToLoginScene();
    void showLoading();
    void hideLoading();
}
