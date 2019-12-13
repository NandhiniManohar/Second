package com.example.gd.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.util.Log;

import java.util.List;

@Dao
public interface MealDAO {

    @Query("SELECT * from BreakFast where option =(:options)")
    LiveData<List<BreakFast>> getBreakfast(int options);


    @Query("SELECT * from BreakFast where option =(:options) and nutrient_type='c' ")
    LiveData<List<BreakFast>> getCarbBreakfast(int options);

    @Query("SELECT * from Lunch where option =(:options)")
    LiveData<List<Lunch>> getLunch(int options);


    @Query("SELECT * from BreakFast where option =(:options)")
    LiveData<List<BreakFast>> getDinner(int options);

    @Query("SELECT * from BreakFast where option =(:options)")
    LiveData<List<BreakFast>> getSnack(int options);

    @Insert
    void insertAll(BreakFast... breakFast);

    @Query("DELETE  from BreakFast ")
    void deleteAll();


}
