package com.alina.singstreet.model;

import androidx.room.DatabaseView;

import java.util.List;

@DatabaseView(value = "SELECT userUID, icon, phoneNumber, nickname from User")
public class ProfileModel {
    public String userUID;
    public int icon;
    public String phoneNumber;
    public String nickname;

    @Override
    public String toString() {
        return "ProfileModel{" +
                "userUID='" + userUID + '\'' +
                ", icon=" + icon +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
