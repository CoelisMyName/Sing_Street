package com.alina.singstreet.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Comment {
    @NonNull
    @PrimaryKey
    String commentUID;
    String userUID;
    String postUID;
    String comment;
    String timestamp;
    float rate;

    public Comment() {
        commentUID = UUID.randomUUID().toString();
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @NonNull
    public String getCommentUID() {
        return commentUID;
    }

    public void setCommentUID(@NonNull String commentUID) {
        this.commentUID = commentUID;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getPostUID() {
        return postUID;
    }

    public void setPostUID(String postUID) {
        this.postUID = postUID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentUID='" + commentUID + '\'' +
                ", userUID='" + userUID + '\'' +
                ", postUID='" + postUID + '\'' +
                ", comment='" + comment + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", rate=" + rate +
                '}';
    }
}
