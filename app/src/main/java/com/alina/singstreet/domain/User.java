package com.alina.singstreet.domain;

import androidx.annotation.NonNull;
import androidx.room.*;

import java.util.UUID;

@Entity(indices = {@Index(value = {"phoneNumber"},unique = true)} )
public class User {
    @NonNull
    @PrimaryKey
    String userUID;

    int icon;
    String phoneNumber;
    String nickname;
    String password;

    public User() {
        userUID = UUID.randomUUID().toString();
    }

    @NonNull
    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(@NonNull String userUID) {
        this.userUID = userUID;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
