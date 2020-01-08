package com.gimbernat.UntitledSocialGame.scenes.events.interfaces;

public interface ICreateEventActivity {
    String getTextNameEvent();
    String getTextDescriptionEvent();
    double getLatEvent();
    void setLatEvent(String newValue);
    double getLongEvent();
    void setLongEvent(String newValue);

    void onError(String errorMessage);
    void navigateToPrivate();
    void showLoading();
    void hideLoading();
}
