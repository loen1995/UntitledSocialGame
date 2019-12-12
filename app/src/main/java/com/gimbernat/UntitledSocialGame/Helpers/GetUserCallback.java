package com.gimbernat.UntitledSocialGame.Helpers;

public interface GetUserCallback {
    void onSuccess(com.gimbernat.UntitledSocialGame.Entities.UserEntity user);
    void onError(String error);
}