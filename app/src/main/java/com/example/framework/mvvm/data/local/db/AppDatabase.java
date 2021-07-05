package com.example.framework.mvvm.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.framework.mvvm.data.local.db.dao.OptionDao;
import com.example.framework.mvvm.data.local.db.dao.QuestionDao;
import com.example.framework.mvvm.data.local.db.dao.UserDao;
import com.example.framework.mvvm.data.model.db.Option;
import com.example.framework.mvvm.data.model.db.Question;
import com.example.framework.mvvm.data.model.db.User;

@Database(entities = {User.class, Question.class, Option.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract OptionDao optionDao();

    public abstract QuestionDao questionDao();

    public abstract UserDao userDao();
}
