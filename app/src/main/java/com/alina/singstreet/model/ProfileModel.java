package com.alina.singstreet.model;

import androidx.annotation.Nullable;
import androidx.room.DatabaseView;

@DatabaseView(
        value = "SELECT User.userUID AS userUID, icon, phoneNumber, nickname, count(DISTINCT F1.followerUID) AS follower, count(DISTINCT F2.userUID) AS following " +
                "FROM (User LEFT JOIN Follow AS F1 ON User.userUID = F1.userUID) LEFT JOIN Follow AS F2 ON User.userUID = F2.followerUID GROUP BY User.userUID"
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

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ProfileModel) {
            ProfileModel temp = (ProfileModel) obj;
            return userUID.equals(temp.userUID) && icon == temp.icon && phoneNumber.equals(temp.phoneNumber)
                    && nickname.equals(temp.nickname) && follower == temp.follower && following == temp.following;
        }
        return super.equals(obj);
    }
}
