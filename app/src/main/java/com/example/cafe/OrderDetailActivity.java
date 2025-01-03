package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderDetailActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "userName";
    private static final String EXTRA_DRINK = "drink";
    private static final String EXTRA_ADDITIVES = "additives";
    private static final String EXTRA_DRINK_TYPE = "drinkType";

    private TextView textViewName;
    private TextView textViewDrink;
    private TextView textViewAdditives;
    private TextView textViewDrinkTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initViews();
        setTextToViews();
    }

    private void setTextToViews(){
        String name = getIntent().getStringExtra(EXTRA_USER_NAME);
        String drink = getIntent().getStringExtra(EXTRA_DRINK);
        String additives = getIntent().getStringExtra(EXTRA_ADDITIVES);
        String drinkType = getIntent().getStringExtra(EXTRA_DRINK_TYPE);

        textViewName.setText(name);
        textViewDrink.setText(drink);
        textViewAdditives.setText(additives);
        textViewDrinkTypes.setText(drinkType);
    }

    private void initViews(){
        textViewName = findViewById(R.id.TextViewName);
        textViewDrink = findViewById(R.id.TextViewDrink);
        textViewAdditives = findViewById(R.id.TextViewAdditives);
        textViewDrinkTypes = findViewById(R.id.TextViewDrinkType);
    }

    public static Intent newIntent(
            Context context,
            String userName,
            String drink,
            String additives,
            String drinkType
    ){
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        intent.putExtra(EXTRA_DRINK, drink);
        intent.putExtra(EXTRA_ADDITIVES, additives);
        intent.putExtra(EXTRA_DRINK_TYPE, drinkType);
        return intent;
    }
}