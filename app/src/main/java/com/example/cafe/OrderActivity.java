package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class OrderActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "userName";

    private TextView textViewGreetings;
    private TextView textViewAdditives;

    private RadioGroup radioGroupDrinks;
    private RadioButton radioButtonTea;
    private RadioButton radioButtonCoffee;

    private CheckBox checkBoxSugar;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxLemon;

    private Spinner spinnerTea;
    private Spinner spinnerCoffee;

    private Button buttonPlaceOrder;

    private String userName;
    private String drink;
    private String drinkType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initViews();
        setUpUserName();

        radioGroupDrinks.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == radioButtonTea.getId()){
                onUserChoseTea();
            } else if (checkedId == radioButtonCoffee.getId()){
                onUserChoseCoffee();
            }
        });

        radioButtonTea.setChecked(true);

        buttonPlaceOrder.setOnClickListener(view -> {
            onUserMadeOrder();
        });

    }

    private void onUserMadeOrder(){
        ArrayList<String> additives = new ArrayList<>();

        if (checkBoxMilk.isChecked()){
            additives.add(checkBoxMilk.getText().toString());
        }
        if (checkBoxSugar.isChecked()){
            additives.add(checkBoxSugar.getText().toString());
        }
        if (radioButtonTea.isChecked() && checkBoxLemon.isChecked()){
            additives.add(checkBoxLemon.getText().toString());
        }

        if (radioButtonTea.isChecked()){
            drinkType = spinnerTea.getSelectedItem().toString();
        }else if (radioButtonCoffee.isChecked()){
            drinkType = spinnerCoffee.getSelectedItem().toString();
        }
        
        Intent intent = OrderDetailActivity.newIntent(
                this,
                userName,
                drink,
                additives.toString(),
                drinkType);
        startActivity(intent);
    }

    private void onUserChoseTea(){
        drink = getString(R.string.tea);
        setUpDrink();
        checkBoxLemon.setVisibility(View.VISIBLE);
        spinnerTea.setVisibility(View.VISIBLE);
        spinnerCoffee.setVisibility(View.INVISIBLE);
    }

    private void onUserChoseCoffee(){
        drink = getString(R.string.coffee);
        setUpDrink();
        checkBoxLemon.setVisibility(View.INVISIBLE);
        spinnerTea.setVisibility(View.INVISIBLE);
        spinnerCoffee.setVisibility(View.VISIBLE);
    }

    public static Intent newIntent(Context context, String userName){
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        return intent;
    }

    private void setUpDrink(){
        String result = getString(R.string.Additives, drink);
        textViewAdditives.setText(result);
    }

    private void setUpUserName(){
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String greetings = getString(R.string.greetings,userName);
        textViewGreetings.setText(greetings);
    }

    private void initViews(){
        textViewGreetings = findViewById(R.id.TextViewGreetings);
        textViewAdditives = findViewById(R.id.TextViewAdditives);

        radioGroupDrinks = findViewById(R.id.RadioGroupDrinks);
        radioButtonTea = findViewById(R.id.RadioButtonTea);
        radioButtonCoffee = findViewById(R.id.RadioButtonCoffee);

        checkBoxSugar = findViewById(R.id.CheckBoxSugar);
        checkBoxMilk = findViewById(R.id.CheckBoxMilk);
        checkBoxLemon = findViewById(R.id.CheckBoxLemon);

        spinnerTea = findViewById(R.id.SpinnerTea);
        spinnerCoffee = findViewById(R.id.SpinnerCoffee);
        buttonPlaceOrder = findViewById(R.id.ButtonPlaceOrder);

    }
}