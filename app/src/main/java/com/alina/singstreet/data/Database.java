package com.alina.singstreet.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.alina.singstreet.dao.CommentDao;
import com.alina.singstreet.dao.FollowDao;
import com.alina.singstreet.dao.PostDao;
import com.alina.singstreet.dao.ProfileDao;
import com.alina.singstreet.dao.UserDao;
import com.alina.singstreet.domain.Comment;
import com.alina.singstreet.domain.Follow;
import com.alina.singstreet.domain.Post;
import com.alina.singstreet.domain.User;
import com.alina.singstreet.model.CommentModel;
import com.alina.singstreet.model.ProfileModel;
import com.alina.singstreet.model.SingCardModel;

@androidx.room.Database(
        entities = {User.class, Post.class, Comment.class, Follow.class},
        views = {SingCardModel.class, ProfileModel.class, CommentModel.class},
        version = 1,
        exportSchema = false
)
public abstract class Database extends RoomDatabase {
    static Database dataBase;

    static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    public abstract UserDao getUserDao();
    public abstract PostDao getPostDao();
    public abstract FollowDao getFollowDao();
    public abstract CommentDao getCommentDao();
    public abstract ProfileDao getProfileDao();

    public static synchronized Database getInstance(Context context) {
        if (dataBase == null) {
            RoomDatabase.Builder builder = Room.databaseBuilder(context.getApplicationContext(), Database.class, "sing_street.db");
            builder.addCallback(callback);
            dataBase = (Database) builder.build();
            dataBase.getOpenHelper().getWritableDatabase();
        }
        return dataBase;
    }
}