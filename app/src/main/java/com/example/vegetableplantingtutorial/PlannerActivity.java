package com.example.vegetableplantingtutorial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PlannerActivity extends AppCompatActivity {

    ListView plannerListView;

    ArrayList<Garden> gardens;

    String plantName[];
    String harvestDate[];
    String imageName[];
    String sDate[];
    String hDate[];

    private static final String PREFS_NAME = "shared_prefs";
    private static final String PREFS_DATA = "shared_data";
    private static final String DATE_FORMAT = "MM/dd/yyyy";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        Intent intent = getIntent();
        String name = intent.getStringExtra(ListdataActivity.EXTRA_VEGETABLE_ID);

        loadData();

        plannerListView = findViewById(R.id.planner_list_view);

        if(gardens != null) {
            CustomAdapter adapter = new CustomAdapter(this, plantName, harvestDate, imageName, sDate, hDate);
            plannerListView.setAdapter(adapter);
        }
        else {
            Intent in = new Intent(this, EmptyActivity.class);
            startActivity(in);
        }

    }

    //PRESS AND HOLD planner item to delete
    public void deleteData(){
        plannerListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_item = position;
                new AlertDialog.Builder(PlannerActivity.this)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this item")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadData() {

        try {

            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString(PREFS_DATA, null);
            Type type = new TypeToken<ArrayList<Garden>>() {}.getType();

            gardens = gson.fromJson(json, type);

            if(gardens == null) {
                Toast.makeText(this, "No plans available", Toast.LENGTH_SHORT).show();
                return;
            }

            int size = gardens.size();
            plantName = new String[size];
            harvestDate = new String[size];
            imageName = new String[size];
            sDate = new String[size];
            hDate = new String[size];

            for(int i = 0; i < gardens.size(); i++) {
                VegetableController con = new VegetableController(this);
                Vegetables vege = con.getVegetableById(gardens.get(i).getVegetableId());
                plantName[i] = vege.getName();
                harvestDate[i] = getHarvestDayLeft(gardens.get(i).getHarvestDate());
                imageName[i] = vege.getImageName();
                sDate[i] = gardens.get(i).getPlantedDate();
                hDate[i] = gardens.get(i).getHarvestDate();


                Log.d("Planted Date", gardens.get(i).getPlantedDate());
                Log.d("Harvest Date", gardens.get(i).getHarvestDate());
            }

            if(gardens == null) {
                Toast.makeText(this, "No Data in sharedPreferences", Toast.LENGTH_SHORT).show();
            }
            else {

            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getHarvestDayLeft(String harvestDate) {

        try {

            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Calendar calendar = Calendar.getInstance();
            String currentDate = sdf.format(calendar.getTime());
            String finalDate = harvestDate;

            Date date1;
            Date date2;

            date1 = sdf.parse(currentDate);
            date2 = sdf.parse(finalDate);

            long diff = Math.abs(date1.getTime() - date2.getTime());
            long diffDays = diff / (24 * 60 * 60 * 1000);
            String daysLeft = Long.toString(diffDays) + " days left to harvest";

            return daysLeft;

        }
        catch(Exception e) {
            e.printStackTrace();
        }



        return null;
    }


    class CustomAdapter extends ArrayAdapter<String> {

        Context context;
        String plantName[];
        String date[];
        String imageName[];
        String plantedDate[];
        String harvesDate[];

        public CustomAdapter(Context context, String plantName[], String date[], String imageName[], String plantedDate[], String harvesDate[]) {
            super(context, R.layout.planner_row, R.id.planner_row_vegetable_name, plantName);
            this.context = context;
            this.plantName = plantName;
            this.date = date;
            this.imageName = imageName;
            this.plantedDate = plantedDate;
            this.harvesDate = harvesDate;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.planner_row, parent, false);

            ImageView image = row.findViewById(R.id.planner_row_image_view);
            TextView name = row.findViewById(R.id.planner_row_vegetable_name);
            TextView hDate = row.findViewById(R.id.planner_row_days_left);
            TextView dates = row.findViewById(R.id.planner_dates);

            name.setText(plantName[position]);
            hDate.setText(date[position]);
            dates.setText(plantedDate[position] + " - " + harvesDate[position]);

            Resources resource = this.context.getResources();
            final int rId = resource.getIdentifier(imageName[position], "drawable", context.getPackageName());
            image.setImageResource(rId);

            return row;
        }
    }

}