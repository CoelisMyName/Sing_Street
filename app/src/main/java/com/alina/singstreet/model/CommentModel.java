package com.alina.singstreet.model;

import androidx.annotation.Nullable;
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

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof CommentModel){
            CommentModel temp = (CommentModel)obj;
            return commentUID.equals(temp.commentUID) && userUID.equals(temp.userUID) && postUID.equals(temp.postUID)
                    && comment.equals(temp.comment) && timestamp.equals(temp.timestamp) && rate == temp.rate && nickname.equals(temp.nickname)
                    && icon == temp.icon;
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "CommentModel{" +
                "commentUID='" + commentUID + '\'' +
                ", userUID='" + userUID + '\'' +
                ", postUID='" + postUID + '\'' +
                ", comment='" + comment + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", rate=" + rate +
                ", nickname='" + nickname + '\'' +
                ", icon=" + icon +
                '}';
    }
}
