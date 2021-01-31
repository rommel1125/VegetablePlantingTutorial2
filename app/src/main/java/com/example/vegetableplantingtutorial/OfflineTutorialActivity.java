package com.example.vegetableplantingtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OfflineTutorialActivity extends AppCompatActivity {

    TextView vegetableName, vegetableDescription;
    ImageView vegetableImage;

    private String vegetableId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_tutorial);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vegetableName = findViewById(R.id.vegetableName);
        vegetableDescription = findViewById(R.id.vegetableDescription);
        vegetableImage = findViewById(R.id.vegetableImage);

        Intent intent = getIntent();
        vegetableId = intent.getStringExtra(ListdataActivity.EXTRA_VEGETABLE_ID);

//        DatabaseHelper db = new DatabaseHelper(this);
//        VegetableModel vegetable = db.fetchVegetableById(vegetableId);
//
//        vegetableName.setText(vegetable.getName());
//        vegetableDescription.setText(vegetable.getDescription());
//        vegetableImage.setImageBitmap(vegetable.getImage());
    }
}