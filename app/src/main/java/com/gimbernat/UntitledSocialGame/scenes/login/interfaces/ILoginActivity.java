package com.gimbernat.UntitledSocialGame.scenes.login.interfaces;

public interface ILoginActivity {

    void onError(String errorMessage);
    void navigateToBootActivity();
    void showLoading();
    void hideLoading();
    String getTextEmail();
    String getPass();
    void navigateToRegister();
}

