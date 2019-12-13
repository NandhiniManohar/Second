package com.example.gd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Setting the click values
    private int bfClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView breakFastView = findViewById(R.id.breakfast);
        TextView lunchView = findViewById(R.id.lunch);
        TextView dinnerView = findViewById(R.id.dinner);
        TextView snackView = findViewById(R.id.snack);
        breakFastView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bfClick = bfClick + 1;
                if (bfClick == 11) {
                    bfClick = 1;

                }
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("button", "breakFast");
                startActivity(i);
            }
        });
        lunchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("button", "lunch");
                startActivity(i);
            }
        });
        dinnerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("button", "dinner");
                startActivity(i);
            }
        });
        snackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("button", "snack");
                startActivity(i);
            }
        });

    }
}

