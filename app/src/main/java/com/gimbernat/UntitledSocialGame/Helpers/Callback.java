package com.gimbernat.UntitledSocialGame.Helpers;

public interface Callback<Object> {

    void onSuccess(Object responseObject);
    void onError();

}

