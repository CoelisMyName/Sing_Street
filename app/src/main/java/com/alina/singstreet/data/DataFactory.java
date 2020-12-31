package com.alina.singstreet.data;

import com.alina.singstreet.R;
import com.alina.singstreet.domain.Comment;
import com.alina.singstreet.domain.Follow;
import com.alina.singstreet.domain.Post;
import com.alina.singstreet.domain.User;

public class DataFactory {
    static DataFactory factory;
    Integer integer = 1;
    int[] icons = {R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4, R.drawable.icon5, R.drawable.icon6, R.drawable.icon7};
    String[] nicknames = {"Johnny", "黑皮", "疯曲大佐", "Alina", "木桥CD", "不会唱歌的DJ"};
    String[] phoneNumbers = {"111000000", "122000000", "133000000", "144000000", "155000000", "166000000"};
    String password = "000000";
    String path = "path/path/path";
    String[] title = {"Title?Title", "ABC", "GGG"};
    String description = "莎士比亚在不经意间这样说过，本来无望的事，大胆尝试，往往能成功。这句话语虽然很短, 但令我浮想联翩. ";
    String comment = "bal bla lba lab!!!!!!!!!!!!!!!!!!!!";
    float[] rate = {1, 1.5f, 2f, 2.5f, 3.5f, 5f};

    User[] users = new User[nicknames.length];
    Follow[] follows = new Follow[12];
    Post[] posts = new Post[users.length * 2];
    Comment[] comments = new Comment[users.length * posts.length];

    DataFactory() {
        for (int i = 0; i < users.length; ++i) {
            users[i] = new User();
            users[i].setNickname(nicknames[i]);
            users[i].setPhoneNumber(phoneNumbers[i]);
            users[i].setIcon(icons[i % icons.length]);
            users[i].setPassword(password);
        }

        for (int i = 0; i < follows.length; i++) {
            follows[i] = new Follow();
        }
        for (int i = 0, k = 0; i < 4 && k < follows.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (!users[i].getUserUID().equals(users[j].getUserUID())) {
                    follows[k].setUserUID(users[i].getUserUID());
                    follows[k].setFollowerUID(users[j].getUserUID());
                    ++k;
                }
            }
        }

        for (int i = 0; i < posts.length; i++) {
            posts[i] = new Post();
            posts[i].setPath(path);
            posts[i].setTimestamp(integer.toString());
            ++integer;
            posts[i].setTitle(title[i % title.length]);
            posts[i].setSong("JJJSong");
            posts[i].setUserUID(users[i / 2].getUserUID());
            posts[i].setDescription(description);
        }

        for (int i = 0; i < comments.length; i++) {
            comments[i] = new Comment();
            int u = i % users.length;
            int p = i / users.length;
            comments[i].setComment(comment);
            comments[i].setRate(rate[i % rate.length]);
            comments[i].setTimestamp(integer.toString());
            ++integer;
            comments[i].setPostUID(posts[p].getPostUID());
            comments[i].setUserUID(users[u].getUserUID());
        }
    }

    public static DataFactory getInstance() {
        if (factory == null) {
            factory = new DataFactory();
        }
        return factory;
    }

    public Comment[] getComments() {
        return comments;
    }

    public Follow[] getFollows() {
        return follows;
    }

    public Post[] getPosts() {
        return posts;
    }

    public User[] getUsers() {
        return users;
    }

}
