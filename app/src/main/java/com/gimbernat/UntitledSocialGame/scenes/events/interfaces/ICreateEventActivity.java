package com.gimbernat.UntitledSocialGame.scenes.events.interfaces;

public interface ICreateEventActivity {
    String getTextNameEvent();
    String getTextDescriptionEvent();
    double getLatEvent();
    double getLongEvent();
    void onError(String errorMessage);
    void navigateToPrivate();
    void showLoading();

}
