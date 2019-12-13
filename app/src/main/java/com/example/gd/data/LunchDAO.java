package com.example.gd.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface LunchDAO {
    @Query("SELECT * from Lunch where option =(:options)")
    LiveData<List<Lunch>> getLunch(int options);

    @Query("SELECT * from Lunch where option =(:options) and nutrient_type='c' ")
    LiveData<List<Lunch>> getCarbLunch(int options);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Lunch... lunch);

    @Query("DELETE  from Lunch ")
    void deleteAll();
}
