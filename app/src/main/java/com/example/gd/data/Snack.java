package com.example.gd.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Snack")
public class Snack {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOption() {
        return option;
    }

    @NonNull
    public String getNutrient_type() {
        return nutrient_type;
    }

    public float getCarb_count() {
        return carb_count;
    }

    @NonNull
    public String getFood() {
        return food;
    }

    @NonNull
    private int option;

    @NonNull
    private String food;

    @NonNull
    private String nutrient_type;
    private float carb_count;

    public Snack(int option, @NonNull String food, @NonNull String nutrient_type, float carb_count) {
        this.option = option;
        this.food = food;
        this.nutrient_type = nutrient_type;
        this.carb_count = carb_count;

    }
}
