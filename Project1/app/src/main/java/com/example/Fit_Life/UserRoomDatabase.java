package com.example.Fit_Life;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.os.HandlerCompat;
import androidx.room.*;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//You have to change the version number if you change the database!!!
@Database(entities = {UserTable.class}, version = 4, exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {
    private static volatile UserRoomDatabase mInstance;
    public abstract UserDao userDao();
    static final ExecutorService databaseExecutor =
            Executors.newFixedThreadPool(4);

    static synchronized UserRoomDatabase getDatabase(final Context context){
        if(mInstance==null) {
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    UserRoomDatabase.class, "user.db").addCallback(sRoomDatabaseCallback).fallbackToDestructiveMigration().build();
        }
        return mInstance;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseExecutor.execute(()->{
                UserDao dao = mInstance.userDao();
                dao.deleteAll();
//                UserTable weatherTable = new UserTableBuilder().setLocation("dummy_loc").setWeatherJson("dummy_data").createWeatherTable();
//                dao.insert(weatherTable);
            });
        }


    };

    private static RoomDatabase.Callback sRoomDatabaseCallback2 = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbTask(mInstance).execute();
        }
    };
    private static class PopulateDbTask{
        private final UserDao mDao;

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
        PopulateDbTask(UserRoomDatabase db){
            mDao = db.userDao();
        }

        public void execute(){
            executorService.execute(new Runnable(){
                @Override
                public void run(){
                    mDao.deleteAll();
//                    UserTable weatherTable = new UserTableBuilder().setLocation("dummy_loc").setWeatherJson("dummy_data").createUserTable();
//                    mDao.insert(weatherTable);
                }
            });
        }
    }
}
