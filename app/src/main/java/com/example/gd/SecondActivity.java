package com.example.gd;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String whichButton = intent.getExtras().getString("button");
        Bundle bundle = new Bundle();
        bundle.putString("whichButton",whichButton);
        MealFragment mealFragment = new MealFragment();
        mealFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mealFragment,"BreakFast")
                .commit();
    }

    public void transaction(){
        // Reload current fragment
        Fragment frg = null;
        frg = getSupportFragmentManager().findFragmentByTag("BreakFast");
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
    }
}
