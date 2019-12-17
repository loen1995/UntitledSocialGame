package com.gimbernat.UntitledSocialGame.Entities;

public class UserEntity {
    private String userID;
    private String nickname;

    public UserEntity(String userID, String nickname) {
        this.userID = userID;
        this.nickname = nickname;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
