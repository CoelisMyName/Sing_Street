package com.alina.singstreet.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.*;

import java.util.UUID;

@Entity(indices = {@Index(value = {"phoneNumber"},unique = true)} )
public class User implements Parcelable {
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

    protected User(Parcel in) {
        userUID = in.readString();
        icon = in.readInt();
        phoneNumber = in.readString();
        nickname = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userUID);
        dest.writeInt(icon);
        dest.writeString(phoneNumber);
        dest.writeString(nickname);
        dest.writeString(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userUID='" + userUID + '\'' +
                ", icon=" + icon +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
