package com.example.vegetableplantingtutorial;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ClickItemPlan extends AppCompatActivity {
    Toolbar toolbar;
    TextView vegePlanname, dateplan, harvest1;
    ImageView imageViewplan;
    Button addToCalendar;

    private static final String DATE_FORMAT = "MM/dd/yyyy";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickitemplan);
        toolbar = findViewById(R.id.action_bar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addToCalendar = findViewById(R.id.addToCalendar);
        vegePlanname = findViewById(R.id.vegeNameplan);
        imageViewplan = findViewById(R.id.imageViewplan);
        dateplan = findViewById(R.id.dateplan);
        harvest1 = findViewById(R.id.harvest1);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String date = intent.getStringExtra("date");
        String hdate = intent.getStringExtra("harvestdate");
        if (name.equals("Eggplant\n(Talong)")) {
            imageViewplan.setImageResource(R.drawable.eggplant2);
        }

        else if (name.equals("Tomato\n(Kamatis)")) {
            imageViewplan.setImageResource(R.drawable.tomato1);
        }

        else if (name.equals("Cabbage\n(Repolyo)")) {
            imageViewplan.setImageResource(R.drawable.cabbage1);
        }

        else if (name.equals("String Beans\n(Sitaw)")) {
            imageViewplan.setImageResource(R.drawable.sitaw1);
        }

        else if (name.equals("Squash\n(Kalabas)")) {
            imageViewplan.setImageResource(R.drawable.kalabasa1);
        }

        else if (name.equals("Moringa Leaves\n(Malunggay)")) {
            imageViewplan.setImageResource(R.drawable.malunggay1);
        }

        else if (name.equals("Water Spinach\n(Kangkong)")) {
            imageViewplan.setImageResource(R.drawable.kangkong1);
        }

        else if (name.equals("Lettuce\n(Letsugas)")) {
            imageViewplan.setImageResource(R.drawable.lettuce1);
        }

        else if (name.equals("Bottle gourd\n(Upo)")) {
            imageViewplan.setImageResource(R.drawable.upo1);
        }

        else {
            imageViewplan.setImageResource(R.drawable.ampalaya1);
        }
        vegePlanname.setText(name);
        dateplan.setText("" + date + " - " + hdate);
        harvest1.setText(hdate);

        addToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vegePlanname.getText().toString().isEmpty()) {

                    Intent intent = new Intent(Intent.ACTION_INSERT)
                            .setData(CalendarContract.Events.CONTENT_URI)
                            .putExtra(CalendarContract.Events.TITLE, "Harvest " + vegePlanname.getText().toString())
                            .putExtra(CalendarContract.Events.ALL_DAY, true)
                            .putExtra(CalendarContract.Events.DTSTART, harvest1.getText().toString());

                    if (intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(ClickItemPlan.this,"There is no app can support this action",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(ClickItemPlan.this,"Walang laman",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//    public long getMilliSeconds(String date) throws ParseException{
//        Date dateSample = null ;
//        try {
//            SimpleDateFormat formatter ;
//            formatter = new SimpleDateFormat(DATE_FORMAT);
//            dateSample = (Date) formatter.parse(date);
//        } catch (Exception e) {
//        }
//
//        return dateSample.getTime();
//    }

//    @RequiresApi(api = Build.VERSION_CODES.N)
//    public Long getHarvestDayLeft(String harvestDate) {
//
//        try {
//
//            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
//            Calendar calendar = Calendar.getInstance();
//            String currentDate = sdf.format(calendar.getTime());
//            String finalDate = harvestDate;
//
//            Date date1;
//            Date date2;
//
//            date1 = sdf.parse(currentDate);
//            date2 = sdf.parse(finalDate);
//
//            long daysLeft = date1.getDate();
//
////            long diff = Math.abs(date1.getTime() - date2.getTime());
////            long diffDays = diff / (24 * 60 * 60 * 1000);
////            String daysLeft = Long.toString(diffDays) + " days left to harvest";
//
//            return daysLeft;
//
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_calendar, menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.addCalendar) {
//            if (!vegePlanname.getText().toString().isEmpty()) {
//                Intent intent = new Intent(Intent.ACTION_INSERT)
//                .setData(CalendarContract.Events.CONTENT_URI)
//                .putExtra(CalendarContract.Events.TITLE, "Harvest " + vegePlanname.getText().toString())
//                        .putExtra(CalendarContract.Events.ALL_DAY, true);
//                if (intent.resolveActivity(getPackageManager()) != null){
//                    startActivity(intent);
//                }
//                else{
//                    Toast.makeText(this,"There is no app can support this action",Toast.LENGTH_SHORT).show();
//                }
//            }else {
//                Toast.makeText(this,"Walang laman",Toast.LENGTH_SHORT).show();
//            }
//        }
//        else {
//
//        }
//        return true;
//    }
}