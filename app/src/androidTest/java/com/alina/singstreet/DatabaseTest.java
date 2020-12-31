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
import com.alina.singstreet.data.DataFactory;
import com.alina.singstreet.data.Database;
import com.alina.singstreet.model.ProfileModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    UserDao userDao;
    ProfileDao profileDao;
    FollowDao followDao;
    CommentDao commentDao;
    PostDao postDao;
    Database dataBase;
    DataFactory dataFactory;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        dataBase = Room.inMemoryDatabaseBuilder(context, Database.class).build();
        userDao = dataBase.getUserDao();
        profileDao = dataBase.getProfileDao();
        followDao = dataBase.getFollowDao();
        commentDao = dataBase.getCommentDao();
        postDao = dataBase.getPostDao();
        dataFactory = DataFactory.getInstance();

        userDao.insert(dataFactory.getUsers());
        followDao.insert(dataFactory.getFollows());
        postDao.insert(dataFactory.getPosts());
        commentDao.insert(dataFactory.getComments());
    }

    @After
    public void closeDb() throws IOException {
        dataBase.close();
    }

    @Test
    public void userTest() throws Exception {
        System.out.println(userDao.getUsersTest());
        List<ProfileModel> profileModels = profileDao.getProfileTest();
        for (int i = 0; i < profileModels.size(); i++) {
            System.out.println(profileModels.get(i));
        }
    }

    @Test
    public void postTest() throws Exception {
        System.out.println(postDao.getPostsTest());
        System.out.println(postDao.getSingCardsTest());
    }

    @Test
    public void followTest() throws Exception {
        String userUID = dataFactory.getUsers()[5].getUserUID();
        String follower = dataFactory.getUsers()[1].getUserUID();
        System.out.println(userUID + follower + " " + followDao.isFollowedTest(follower, userUID));
    }

    @Test
    public void commentTest() throws Exception {
        //System.out.println(commentDao.getCommentTest());
        //System.out.println(commentDao.getCommentModelTest());
        assert (commentDao.getCommentTest().size() == commentDao.getCommentModelTest().size());
        System.out.println(commentDao.getCommentsTest(dataFactory.getPosts()[0].getPostUID()));
    }
}
