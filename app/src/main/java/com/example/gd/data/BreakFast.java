/*
 * *
 *  * Created by Nandhini Manoharan on 12/13/19 10:22 AM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/9/19 4:26 PM
 *
 */

package com.example.gd.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "BreakFast")
public class BreakFast {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    private int option;

    @NonNull
    private String food;

    @NonNull
    private String nutrient_type;

    private float carb_count;


    public void setOption(int option) {
        this.option = option;
    }

    public float getCarb_count() {
        return carb_count;
    }

    public int getId() {
        return id;
    }

    public int getOption() {
        return option;
    }

    @NonNull
    public String getFood() {
        return food;
    }

    public String getNutrient_type() {
        return nutrient_type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BreakFast(int option, @NonNull String food, @NonNull String nutrient_type, float carb_count) {
        this.option = option;
        this.food = food;
        this.nutrient_type = nutrient_type;
        this.carb_count = carb_count;
    }

}
