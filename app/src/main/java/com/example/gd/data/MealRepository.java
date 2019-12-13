package com.example.gd.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;
/*singleton pattern*/

public class MealRepository {

    private  static MealRepository instance;

    private MealDAO mMealDao;
    private LunchDAO lunchDAO;
    private SnackDAO snackDAO;
    private DinnerDAO dinnerDAO;
    private LiveData<List<BreakFast>> mBreakfast;
    private LiveData<List<BreakFast>> mBreakfastAsync;
    private LiveData<List<Lunch>> mLunch;
    private LiveData<List<Dinner>> mDinner;
    private LiveData<List<Snack>> mSnack;
    private int options;

    MealRepository(Application application) {
        MealDatabase db = MealDatabase.getDatabase(application);
        mMealDao = db.breakFastDAO();
        lunchDAO=db.lunchDAO();
        dinnerDAO=db.dinnerDao();
        snackDAO=db.snackDao();
       // mBreakfast = mMealDao.getBreakfast(options);

    }

    public LiveData<List<BreakFast>> getBreakfast(int options) {
        mBreakfast = mMealDao.getBreakfast(options);
        return mBreakfast;
    }


    public LiveData<List<BreakFast>> getCarbBreakfast(int options) {
        mBreakfast = mMealDao.getCarbBreakfast(options);
        return mBreakfast;
    }

    public LiveData<List<Lunch>> getLunch(int options) {
        mLunch = lunchDAO.getLunch(options);
        return mLunch;
    }

    public LiveData<List<Lunch>> getCarbLunch(int options) {
        mLunch = lunchDAO.getCarbLunch(options);
        return mLunch;
    }
    public LiveData<List<Dinner>> getDinner(int options) {
        mDinner = dinnerDAO.getDinner(options);
        return mDinner;
    }
    public LiveData<List<Dinner>> getCarbDinner(int options) {
        mDinner = dinnerDAO.getCarbDinner(options);
        return mDinner;
    }
    public LiveData<List<Snack>> getSnack(int options) {
        mSnack = snackDAO.getSnack(options);
        return mSnack;
    }
}