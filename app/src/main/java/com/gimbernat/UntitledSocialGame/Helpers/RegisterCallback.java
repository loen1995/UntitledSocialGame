package com.gimbernat.UntitledSocialGame.Helpers;

public interface RegisterCallback<FirebaseUser> {

    void onSuccess(FirebaseUser user);
    void onError(String error);

}
