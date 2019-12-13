package com.example.gd;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gd.data.BreakFast;
import com.example.gd.data.Dinner;
import com.example.gd.data.Lunch;
import com.example.gd.data.MealViewModel;
import com.example.gd.data.Snack;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MealFragment extends Fragment {

    private MealViewModel mealViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.meal_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String whichBtn = getArguments().getString("whichButton");
        final TextView reeultTextView = getView().findViewById(R.id.result);
        final PieChart pieChart = (PieChart) getView().findViewById(R.id.pieChart);
        final List<PieEntry> pieEntries = new ArrayList<>();
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(true);
        pieChart.setDrawEntryLabels(false);
        pieChart.setExtraOffsets(0, 10, 0, 10);
        pieChart.setDrawCenterText(true);
        pieChart.setCenterText("Carb Count");
        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setWordWrapEnabled(true);
        legend.setDrawInside(false);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(13f);
        pieChart.animateXY(1000, 1000);
        Button anotherOptionBtn = getView().findViewById(R.id.anotherOptionBtn);
        final Random r = new Random();
        int randomNum = r.nextInt(11 - 1) + 1;//max val exclusive,min val inclusive
        final StringBuilder builder = new StringBuilder();
        LiveData<List<BreakFast>> carbBreakFastMeal ;
        LiveData<List<Lunch>> lunchMeal ;
        LiveData<List<Lunch>> carbLunchtMeal ;
        LiveData<List<Dinner>> dinnerMeal ;
        LiveData<List<Dinner>> carbDinnerMeal ;
        LiveData<List<Snack>> snackMeal;
        mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);

        if (whichBtn.equalsIgnoreCase("breakFast")) {
            carbBreakFastMeal = mealViewModel.getCarbBreakfast(randomNum);
            carbBreakFastMeal.observe(this, new Observer<List<BreakFast>>() {
                @Override
                public void onChanged(@Nullable final List<BreakFast> breakFasts) {
                    for (int i = 0; i < breakFasts.size(); i++) {
                        // mp charts
                        if (pieChart.getData() == null) {
                            pieEntries.add(new PieEntry(breakFasts.get(i).getCarb_count(), breakFasts.get(i).getFood()));
                        }
                    }
                    PieDataSet dataSet = new PieDataSet(pieEntries, "");
                    dataSet.setColors(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorGrey), getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPurple));
                    dataSet.setValueTextSize(15f);
                    dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
                    dataSet.setValueLinePart1OffsetPercentage(20.f);
                    dataSet.setValueLinePart1Length(0.83f);
                    dataSet.setValueLinePart2Length(.1f);
                    dataSet.setValueTextColor(Color.BLACK);
                    dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
                    dataSet.setSelectionShift(10f);
                    PieData pieData = new PieData(dataSet);
                    pieChart.setData(pieData);
                    pieChart.invalidate();


                }
            });
            mealViewModel.getBreakfast(randomNum).removeObservers(getViewLifecycleOwner());
            mealViewModel.getBreakfast(randomNum).observe(getViewLifecycleOwner(), new Observer<List<BreakFast>>() {
                @Override
                public void onChanged(@Nullable List<BreakFast> breakFasts) {
                    // Update the cached copy of the words in the adapter.
                    for (int i = 0; i < breakFasts.size(); i++) {
                        String food = breakFasts.get(i).getFood();
                        if (food.contains("||")) {
                            food = food.replace("||", "\n ");
                        }
                        builder.append(" " + food + "\n");
                    }
                    if (reeultTextView.getText().toString() == "") {
                        reeultTextView.setText(builder.toString());
                    }

                }
            });

        } else if (whichBtn.equalsIgnoreCase("lunch")) {
            lunchMeal = mealViewModel.getLunch(randomNum);
            lunchMeal.observe(this, new Observer<List<Lunch>>() {
                @Override
                public void onChanged(@Nullable final List<Lunch> lunches) {
                    // Update the cached copy of the words in the adapter.
                    for (int i = 0; i < lunches.size(); i++) {
                        String food = lunches.get(i).getFood();
                        if (food.contains("||")) {
                            food = food.replace("||", "\n ");
                        }
                        builder.append(" " + food + "\n");
                    }
                    if (reeultTextView.getText().toString() == "") {
                        reeultTextView.setText(builder.toString());
                    }
                }
            });
            carbLunchtMeal = mealViewModel.getCarbLunch(randomNum);
            carbLunchtMeal.observe(this, new Observer<List<Lunch>>() {
                @Override
                public void onChanged(@Nullable List<Lunch> lunches) {
                    for (int i = 0; i < lunches.size(); i++) {
                        //trying mp charts
                        if (pieChart.getData().equals(null)) {
                            pieEntries.add(new PieEntry(lunches.get(i).getCarb_count(), lunches.get(i).getFood()));
                        }
                    }
                    PieDataSet dataSet = new PieDataSet(pieEntries, "");
                    dataSet.setColors(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorGrey), getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPurple));
                    dataSet.setValueTextSize(15f);
                    dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
                    dataSet.setValueLinePart1OffsetPercentage(20.f);
                    dataSet.setValueLinePart1Length(0.83f);
                    dataSet.setValueLinePart2Length(.1f);
                    dataSet.setValueTextColor(Color.BLACK);
                    dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
                    dataSet.setSelectionShift(10f);
                    PieData pieData = new PieData(dataSet);
                    pieChart.setData(pieData);
                    pieChart.invalidate();
                }
            });

        } else if (whichBtn.equalsIgnoreCase("dinner")) {
            dinnerMeal = mealViewModel.getDinner(randomNum);
            dinnerMeal.observe(this, new Observer<List<Dinner>>() {
                @Override
                public void onChanged(@Nullable final List<Dinner> dinners) {
                    for (int i = 0; i < dinners.size(); i++) {
                        String food = dinners.get(i).getFood();
                        if (food.contains("||")) {
                            food = food.replace("||", "\n ");
                        }
                        builder.append(" " + food + "\n");
                    }
                    if (reeultTextView.getText().toString() == "") {
                        reeultTextView.setText(builder.toString());
                    }
                }
            });
            carbDinnerMeal = mealViewModel.getCarbDinner(randomNum);
            carbDinnerMeal.observe(this, new Observer<List<Dinner>>() {
                @Override
                public void onChanged(@Nullable List<Dinner> dinners) {
                    for (int i = 0; i < dinners.size(); i++) {
                        //trying mp charts
                        if (pieChart.getData() == null) {
                            pieEntries.add(new PieEntry(dinners.get(i).getCarb_count(), dinners.get(i).getFood()));
                        }
                    }
                    PieDataSet dataSet = new PieDataSet(pieEntries, "");
                    dataSet.setColors(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorGrey), getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPurple));
                    dataSet.setValueTextSize(15f);
                    dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
                    dataSet.setValueLinePart1OffsetPercentage(20.f);
                    dataSet.setValueLinePart1Length(0.83f);
                    dataSet.setValueLinePart2Length(.1f);
                    dataSet.setValueTextColor(Color.BLACK);
                    dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
                    dataSet.setSelectionShift(10f);
                    PieData pieData = new PieData(dataSet);
                    pieChart.setData(pieData);
                    pieChart.invalidate();
                }
            });

        } else if (whichBtn.equalsIgnoreCase("snack")) {
            pieChart.setVisibility(View.INVISIBLE);
            snackMeal = mealViewModel.getSnack(randomNum);
            snackMeal.observe(this, new Observer<List<Snack>>() {
                @Override
                public void onChanged(@Nullable final List<Snack> snacks) {
                    // Update the cached copy of the words in the adapter.
                    for (int i = 0; i < snacks.size(); i++) {
                        String food = snacks.get(i).getFood();
                        if (food.contains("||")) {
                            food = food.replace("||", "-->");
                        }
                        builder.append("--> " + food + "\n");
                    }
                    if (reeultTextView.getText().toString() == "") {
                        reeultTextView.setText(builder.toString());
                    }

                }
            });


        }
        anotherOptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Reload current fragment
                ((SecondActivity) getActivity()).transaction();
            }


        });
    }

}
