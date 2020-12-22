package com.alina.singstreet.domain;

import androidx.room.Entity;

@Entity(primaryKeys = {"userUID","followerUID"})
public class Follow {
    String userUID;
    String followerUID;

    public void setFollowerUID(String followerUID) {
        this.followerUID = followerUID;
    }

    public String getFollowerUID() {
        return followerUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getUserUID() {
        return userUID;
    }
}
