package com.example.vegetableplantingtutorial;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {
    Toolbar toolbar4;
    EditText addNoteTxt;
    TextView nameVegetable;
    Button addNote;
    ListView listNote;
    CalendarView calendarView;
    DatabaseHelper databaseHelper;
    private static final String DATE_FORMAT = "MM/dd/yyyy";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        toolbar4 = findViewById(R.id.action_bar4);
        setSupportActionBar(toolbar4);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameVegetable = findViewById(R.id.nameVegetable);
        addNoteTxt = findViewById(R.id.addNoteTxt);
        addNote = findViewById(R.id.addNote);
        listNote = findViewById(R.id.listNote);
        calendarView = findViewById(R.id.calendar_view);
        databaseHelper = new DatabaseHelper(this);

        String datePlanted = getIntent().getStringExtra("datePlanted");
        String name = getIntent().getStringExtra("name");
        String note = addNoteTxt.getText().toString();

        nameVegetable.setText(name);
        String gulayName = nameVegetable.getText().toString();
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = df.format(c); //date ng note

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date1 = (month + 1) + "/" + dayOfMonth + "/"+ year;
                Toast.makeText(CalendarActivity.this,date1,Toast.LENGTH_SHORT).show();

//                ViewData(date1);
            }
        });

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vegetableName = gulayName;
                String plantedDate = datePlanted;
                String noted = note;
                String dateNoted = formattedDate;
                if (TextUtils.isEmpty(addNoteTxt.getText().toString())){
                    addNoteTxt.setError("The note cannot be empty");
                }
                else {
                    AddData(vegetableName, plantedDate, noted, dateNoted);
                }
            }
        });
    }
    public void ViewData(String date1){

    }
    public void AddData(String vegetableName, String plantedDate, String noted, String dateNoted){
        boolean insert = databaseHelper.insertDataPlan(vegetableName, plantedDate, noted, dateNoted);

        if (insert == true){
            Toast.makeText(CalendarActivity.this,"Successfully added",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CalendarActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
}