package com.example.vegetableplantingtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmptyActivity extends AppCompatActivity {

    Button emptyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        emptyButton = findViewById(R.id.empty_button);
        emptyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlantListActivity();
            }
        });

    }

    private void openPlantListActivity() {

        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }
}