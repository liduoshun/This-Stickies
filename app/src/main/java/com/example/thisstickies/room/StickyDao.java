package com.example.thisstickies.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.thisstickies.room.Sticky;

import java.util.List;

@Dao
public interface StickyDao {

    @Insert
    void insert(Sticky sticky);

    @Query("SELECT * from stickies_table WHERE isTrash = 0 ORDER BY sticky ASC")
    LiveData<List<Sticky>> getAllStickies();

    @Query("SELECT * from stickies_table WHERE isTrash = 1 ORDER BY sticky ASC")
    LiveData<List<Sticky>> getAllTrashes();

    @Update
    void update(Sticky sticky);
}
