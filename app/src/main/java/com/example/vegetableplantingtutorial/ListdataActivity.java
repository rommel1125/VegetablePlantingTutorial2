package com.example.vegetableplantingtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListdataActivity extends AppCompatActivity {
    TextView name,des, txtid;
    ImageView image;

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
}