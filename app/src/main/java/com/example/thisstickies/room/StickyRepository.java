package com.example.thisstickies.room;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import static android.content.ContentValues.TAG;

public class StickyRepository {
    private static StickyDao mStickyDao;


    private static volatile StickyRepository INSTANCE;

    private StickyRepository() {
    }

    public static StickyRepository getInstance(Application application) {
        StickyRoomDatabase db = StickyRoomDatabase.getDatabase(application);
        mStickyDao = db.stickyDao();
        if (INSTANCE == null) {
            synchronized (StickyRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new StickyRepository();
                }
            }
        }
        return INSTANCE;
    }


    public void insert(Sticky sticky) {
        new insertAsyncTask(mStickyDao).execute(sticky);
    }

    public void update(Sticky sticky) {
        Log.e(TAG, "update: aaabbbbb");
        new UpdateAsyncTask(mStickyDao).execute(sticky);
    }


    public LiveData<List<Sticky>> getAllTopics() {
        Log.e(TAG, "getAllTopics: yyyyyy");
        return mStickyDao.getAllStickies();
    }

    public LiveData<List<Sticky>> getAllTrashes() {
        return mStickyDao.getAllTrashes();
    }


    private static class insertAsyncTask extends AsyncTask<Sticky, Void, Void> {
        private StickyDao mAsyncTaskDao;

        insertAsyncTask(StickyDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Sticky... stickies) {
            mAsyncTaskDao.insert(stickies[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Sticky, Void, Void> {
        private StickyDao mAsyncTaskDao;

        UpdateAsyncTask(StickyDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Sticky... stickies) {
            mAsyncTaskDao.update(stickies[0]);
            Log.e(TAG, "doInBackground: " );
            return null;
        }
    }
}

