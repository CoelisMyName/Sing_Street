package com.alina.singstreet.model;

import androidx.annotation.Nullable;
import androidx.room.DatabaseView;

/*
@DatabaseView(value = "SELECT Post.song AS song, Post.path AS path, Post.timestamp AS timestamp" +
        ", Post.description AS description, User.nickname AS nickname, User.icon AS icon" +
        ", avg(comment.rate) AS rate,Post.userUID AS userUID, Post.postUID AS postUID " +
        "FROM ((User JOIN Post) join Comment) GROUP BY Comment.postUID")
 */
@DatabaseView(value = "SELECT Post.song AS song, Post.title AS title, Post.path AS path, Post.timestamp AS timestamp, " +
        "Post.description AS description, User.nickname AS nickname, User.icon AS icon, " +
        "avg(Comment.rate) AS rate,User.userUID AS userUID, Post.postUID AS postUID " +
        "FROM (Post JOIN User ON Post.userUID = User.userUID) LEFT JOIN Comment ON Comment.postUID = Post.postUID GROUP BY Post.postUID")
public class SingCardModel {
    public String postUID;
    public String userUID;
    public int icon;
    public String nickname;
    public String song;
    public String timestamp;
    public String description;
    public String path;
    public String title;
    public float rate;

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof SingCardModel) {
            SingCardModel temp = (SingCardModel) obj;
            return postUID.equals(temp.postUID) && userUID.equals(temp.userUID) && icon == temp.icon && nickname.equals(temp.nickname)
                    && song.equals(temp.song) && timestamp.equals(temp.timestamp) && description.equals(temp.description)
                    && path.equals(temp.path) && title.equals(temp.title) && rate == temp.rate;
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public String toString() {
        return "SingCardModel{" +
                "postUID='" + postUID + '\'' +
                ", userUID='" + userUID + '\'' +
                ", icon=" + icon +
                ", nickname='" + nickname + '\'' +
                ", song='" + song + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", title='" + title + '\'' +
                ", rate=" + rate +
                '}';
    }
}
