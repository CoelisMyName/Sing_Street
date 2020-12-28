package com.alina.singstreet.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(primaryKeys = {"userUID","followerUID"})
public class Follow {
    @NonNull
    String userUID;
    @NonNull
    String followerUID;

    public Follow(){
        userUID = "";
        followerUID = "";
    }

    @Ignore
    public Follow(@NonNull String userUID, @NonNull String followerUID){
        this.userUID = userUID;
        this.followerUID = followerUID;
    }

    public void setFollowerUID(@NonNull String followerUID) {
        this.followerUID = followerUID;
    }

    @NonNull
    public String getFollowerUID() {
        return followerUID;
    }

    public void setUserUID(@NonNull String userUID) {
        this.userUID = userUID;
    }

    @NonNull
    public String getUserUID() {
        return userUID;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "userUID='" + userUID + '\'' +
                ", followerUID='" + followerUID + '\'' +
                '}';
    }
}
