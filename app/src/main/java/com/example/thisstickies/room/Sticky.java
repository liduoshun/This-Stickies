package com.example.thisstickies.room;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.*;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "stickies_table")
public class Sticky implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private Long id;

    @NonNull
    @ColumnInfo(name = "sticky")
    private String mySticky;

    @NonNull
    @ColumnInfo(name = "topic")
    private String myTopic;

    @NonNull
    @ColumnInfo(name = "isTrash")
    private Integer isTrash = 0;

    public Sticky(String mySticky, String myTopic) {
        this.mySticky = mySticky;
        this.myTopic = myTopic;
    }

    public String getMySticky() {
        return this.mySticky;
    }

    public String getMyTopic() {
        return this.myTopic;
    }

    public void setMySticky(String sticky) {
        this.mySticky = sticky;
    }

    public void setMyTopic(String topic) {
        this.myTopic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsTrash() {
        return isTrash;
    }

    public void setIsTrash(Integer integer) {
        this.isTrash = integer;
    }

    public void setTrash() {
        this.isTrash = isTrash ^ 01;
    }
}


