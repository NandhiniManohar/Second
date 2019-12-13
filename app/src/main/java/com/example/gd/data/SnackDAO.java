package com.example.gd.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SnackDAO {
    @Query("SELECT * from Snack where option =(:options)")
    LiveData<List<Snack>> getSnack(int options);

    @Insert
    void insertAll(Snack... snack);

    @Query("DELETE  from Snack ")
    void deleteAll();
}
