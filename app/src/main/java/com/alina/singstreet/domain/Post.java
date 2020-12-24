package com.alina.singstreet.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Post {
    @NonNull
    @PrimaryKey
    String postUID;

    String userUID;

    String title;
    String song;
    String path;
    String timestamp;
    String description;

    public Post() {
        postUID = UUID.randomUUID().toString();
    }

    @NonNull
    public String getPostUID() {
        return postUID;
    }

    public void setPostUID(@NonNull String postUID) {
        this.postUID = postUID;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postUID='" + postUID + '\'' +
                ", userUID='" + userUID + '\'' +
                ", title='" + title + '\'' +
                ", song='" + song + '\'' +
                ", path='" + path + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
