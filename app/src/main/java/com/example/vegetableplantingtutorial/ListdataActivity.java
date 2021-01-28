package com.example.vegetableplantingtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListdataActivity extends AppCompatActivity {

    public static final String EXTRA_URI = "com.example.vegetableplantingtutorial.EXTRA_URI";

    TextView name,des, txtid;
    ImageView image;
    Button tutorialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DatabaseHelper db = new DatabaseHelper(this);

        name = findViewById(R.id.listdata);
        image = findViewById(R.id.imageView);
        des = findViewById(R.id.textDescription);
        txtid = findViewById(R.id.txtid);
        tutorialButton = (Button) findViewById(R.id.tutorialButton);

        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTutorialActivity();
            }
        });

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        image.setImageResource(intent.getIntExtra("image", 0));
        txtid.setText(intent.getStringExtra("id23"));

        int id1 = Integer.parseInt(txtid.getText().toString());

        Cursor cursor = db.getData(id1);
        StringBuilder stringBuilder = new StringBuilder();
        while (cursor.moveToNext()){
            stringBuilder.append(""+cursor.getString(2));
        }
        des.setText(stringBuilder);
    }

    public void openTutorialActivity() {
        Helper h = new Helper();
        int id = Integer.parseInt((String) this.txtid.getText());

        String uri = h.getUri(id);
        Intent intent = new Intent(this, TutorialActivity.class);
        intent.putExtra(EXTRA_URI, uri);
        startActivity(intent);
    }
}