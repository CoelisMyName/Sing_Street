package com.alina.singstreet.model;

import androidx.room.DatabaseView;

@DatabaseView(value = "SELECT Comment.commentUID AS commentUID, User.userUID AS userUID, Post.postUID AS postUID, " +
        "Comment.comment AS comment, Comment.timestamp AS timestamp, Comment.rate AS rate, User.nickname AS nickname, " +
        "User.icon AS icon " +
        "FROM User, Post, Comment " +
        "WHERE Post.postUID = Comment.postUID AND Post.userUID = User.userUID")
public class CommentModel {
    public String commentUID;
    public String userUID;
    public String postUID;
    public String comment;
    public String timestamp;
    public float rate;
    public String nickname;
    public int icon;
}