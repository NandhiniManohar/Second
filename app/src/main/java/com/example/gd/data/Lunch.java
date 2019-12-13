package com.example.gd.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Lunch")
public class Lunch {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    private int option;

    @NonNull
    private String food;

    private float carb_count;

    @NonNull
    private String nutrient_type;

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

    public void setOption(int option) {
        this.option = option;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lunch(int option, @NonNull String food, @NonNull String nutrient_type, float carb_count) {
        this.option = option;
        this.food = food;
        this.nutrient_type = nutrient_type;
        this.carb_count = carb_count;
    }


}
