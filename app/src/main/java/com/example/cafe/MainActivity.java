package com.example.cafe;

import static com.example.cafe.OrderActivity.newIntent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button enterButton;
    private EditText editTextName;
    private EditText editTextPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        enterButton.setOnClickListener(view -> {
            String userName = editTextName.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            if (userName.isEmpty() || password.isEmpty()){
                Toast.makeText(
                        MainActivity.this,
                        R.string.error_fields_empty,
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                launchNextScreen(userName);
            }
        });
    }

    private void launchNextScreen(String userName){
        Intent intent = newIntent(this, userName);
        startActivity(intent);
    }

    private void initViews(){
        enterButton = findViewById(R.id.ButtonEnter);
        editTextName = findViewById(R.id.EditTextName);
        editTextPassword = findViewById(R.id.EditTextPassword);
    }
}