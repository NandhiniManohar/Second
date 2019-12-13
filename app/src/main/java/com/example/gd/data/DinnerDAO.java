package com.example.gd.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DinnerDAO {
    @Query("SELECT * from Dinner where option =(:options)")
    LiveData<List<Dinner>> getDinner(int options);

    @Query("SELECT * from Dinner where option =(:options) and nutrient_type='c' ")
    LiveData<List<Dinner>> getCarbDinner(int options);

    @Insert
    void insertAll(Dinner... dinners);

    @Query("DELETE  from Dinner ")
    void deleteAll();
}
