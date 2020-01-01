package com.gimbernat.UntitledSocialGame.scenes.events.interfaces;

public interface ICreateEventActivity {
    String getTextNameEvent();
    String getTextDescriptionEvent();
    String getLatEvent();
    void setLatEvent(String newValue);
    String getLongEvent();
    void setLongEvent(String newValue);

    void onError(String errorMessage);
    void navigateToPrivate();
    void showLoading();
    void hideLoading();
}
