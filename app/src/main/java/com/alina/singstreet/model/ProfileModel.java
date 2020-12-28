package com.alina.singstreet.model;

import androidx.room.DatabaseView;

import java.util.List;

@DatabaseView(
        value = "SELECT User.userUID AS userUID, icon, phoneNumber, nickname, count(DISTINCT F1.followerUID) AS follower, count(DISTINCT F2.userUID) AS following " +
        "FROM User, Follow AS F1, Follow AS F2 " +
        "WHERE User.userUID = F1.userUID AND User.userUID = F2.followerUID"
)
public class ProfileModel {
    public String userUID;
    public int icon;
    public String phoneNumber;
    public String nickname;
    public int follower;
    public int following;

    @Override
    public String toString() {
        return "ProfileModel{" +
                "userUID='" + userUID + '\'' +
                ", icon=" + icon +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nickname='" + nickname + '\'' +
                ", follower=" + follower +
                ", following=" + following +
                '}';
    }
}
