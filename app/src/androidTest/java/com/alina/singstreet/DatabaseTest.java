package com.alina.singstreet;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.alina.singstreet.dao.CommentDao;
import com.alina.singstreet.dao.FollowDao;
import com.alina.singstreet.dao.PostDao;
import com.alina.singstreet.dao.ProfileDao;
import com.alina.singstreet.dao.UserDao;
import com.alina.singstreet.data.Database;
import com.alina.singstreet.domain.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.UUID;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    UserDao userDao;
    ProfileDao profileDao;
    FollowDao followDao;
    CommentDao commentDao;
    PostDao postDao;
    Database dataBase;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        dataBase = Room.inMemoryDatabaseBuilder(context, Database.class).build();
        userDao = dataBase.getUserDao();
        profileDao = dataBase.getProfileDao();
        followDao = dataBase.getFollowDao();
        commentDao = dataBase.getCommentDao();
        postDao = dataBase.getPostDao();
    }

    @After
    public void closeDb() throws IOException {
        dataBase.close();
    }

    @Test
    public void insertUser() throws Exception {
        User user = new User();
        user.setUserUID(UUID.randomUUID().toString());
        user.setPhoneNumber("13777777777");
        user.setIcon(R.drawable.icon1);
        user.setNickname("John");
        user.setPassword("000000");
        long[] l = userDao.insert(user);
        System.out.println(l[0]);
        user.setPassword("111111");
        int m = userDao.update(user);
        System.out.println(m);
    }
}
