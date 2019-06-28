package com.example.thisstickies.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Sticky.class}, version = 6, exportSchema = false)
public abstract class StickyRoomDatabase extends RoomDatabase {
    public abstract StickyDao stickyDao();

    private static volatile StickyRoomDatabase INSTANCE;

    static StickyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StickyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StickyRoomDatabase.class, "sticky_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback).build();// Create database here
                }
            }
        }
        return INSTANCE;
    }



    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final StickyDao mDao;

        PopulateDbAsync(StickyRoomDatabase db) {
            mDao = db.stickyDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            return null;
        }
    }
}
