package com.example.thisstickies.room;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import static android.content.ContentValues.TAG;

public class StickyViewModel extends AndroidViewModel {

    private StickyRepository mRepository;

    private LiveData<List<Sticky>> mAllStickies;
    private LiveData<List<Sticky>> mAllTrashes;

    public StickyViewModel(Application application) {
        super(application);
        mRepository = StickyRepository.getInstance(application);
        mAllStickies = mRepository.getAllTopics();
        mAllTrashes = mRepository.getAllTrashes();
    }


    public LiveData<List<Sticky>> getAllTopics(boolean isTrash) {
        if(isTrash == false){
            return mAllStickies;
        }
        else{
            return mAllTrashes;
        }
    }

    public void insert(Sticky sticky) {
        mRepository.insert(sticky);
    }

    public void update(Sticky sticky){
        Log.e(TAG, "update");
        mRepository.update(sticky);
    }
}
