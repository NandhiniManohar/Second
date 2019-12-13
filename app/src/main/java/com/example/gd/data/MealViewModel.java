package com.example.gd.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

public class MealViewModel extends AndroidViewModel {

    private MealRepository mRepository;

    private LiveData<List<BreakFast>> mAllWords;


    private List<BreakFast> mAllWordsAsync;
    private LiveData<List<Lunch>> mLunch;
    private LiveData<List<Dinner>> mDinner;
    private LiveData<List<Snack>> mSnack;

    private int option;

    public MealViewModel(Application application) {
        super(application);
        Log.v("MealViewModel", "CONSTRUCTOR");
        mRepository = new MealRepository(application);
        mAllWords = mRepository.getBreakfast(option);
        mLunch = mRepository.getLunch(option);
        mDinner = mRepository.getDinner(option);
        mSnack = mRepository.getSnack(option);
    }

    public LiveData<List<BreakFast>> getBreakfast(int option) {
        mAllWords = mRepository.getBreakfast(option);
        return mAllWords;
    }


    public LiveData<List<BreakFast>> getCarbBreakfast(int option) {
        mAllWords = mRepository.getCarbBreakfast(option);
        return mAllWords;
    }

    public LiveData<List<Lunch>> getCarbLunch(int option) {
        mLunch = mRepository.getCarbLunch(option);
        return mLunch;
    }

    public LiveData<List<Dinner>> getCarbDinner(int option) {
        mDinner = mRepository.getCarbDinner(option);
        return mDinner;
    }

    public LiveData<List<Lunch>> getLunch(int option) {
        mLunch = mRepository.getLunch(option);
        return mLunch;
    }

    public LiveData<List<Dinner>> getDinner(int option) {
        mDinner = mRepository.getDinner(option);
        return mDinner;
    }

    public LiveData<List<Snack>> getSnack(int option) {
        mSnack = mRepository.getSnack(option);
        return mSnack;
    }

}