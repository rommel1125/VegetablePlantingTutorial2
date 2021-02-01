package com.example.vegetableplantingtutorial;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.Calendar;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListdataActivity extends AppCompatActivity {

    public static final String EXTRA_URI = "com.example.vegetableplantingtutorial.EXTRA_URI";
    public static final String EXTRA_VEGETABLE_ID = "com.example.vegetableplantingtutorial.EXTRA_VEGETABLE_ID";

    private static final String PREFS_NAME = "shared_prefs";
    private static final String PREFS_DATA = "shared_data";
    private static final String DATE_FORMAT = "MM/dd/yyyy";

    TextView name,des, txtid;
    ImageView image;
    Button tutorialButton, plannerButton;

    private String videoUrl;
    private boolean toVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.listdata);
        image = findViewById(R.id.imageView);
        des = findViewById(R.id.textDescription);
        txtid = findViewById(R.id.txtid);
        tutorialButton = (Button) findViewById(R.id.tutorialButton);
        plannerButton = findViewById(R.id.plannerButton);

        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTutorialActivity();
            }
        });

        plannerButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                startPlanting();
            }
        });

        Intent intent = getIntent();

        String vegetable_id = intent.getStringExtra("id23");
        txtid.setText(vegetable_id);

        VegetableController con = new VegetableController(ListdataActivity.this);
        Vegetables vegetable = con.getVegetableById(vegetable_id);

        name.setText(vegetable.getName());

        Resources resources = getResources();
        final int resourceId = resources.getIdentifier(vegetable.getImageName(), "drawable", getPackageName());
        image.setImageResource(resourceId);

        this.videoUrl = vegetable.getUrl();
        des.setText(vegetable.getDescription());
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startPlanting() {

        saveData();

        String plantId = txtid.getText().toString();
        Intent intent = new Intent(this, PlannerActivity.class);
        intent.putExtra(EXTRA_VEGETABLE_ID, plantId);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void saveData() {
        String vegetableId = txtid.getText().toString();
        try {

            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

            ArrayList<Garden> gardens = loadData();
            VegetableController con = new VegetableController(this);
            Vegetables vegetable = con.getVegetableById(vegetableId);

            int daysForHarvest = Integer.parseInt(vegetable.getDays());
            String imageName = vegetable.getImageName();

            // Get harvest date
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, daysForHarvest);
            String harvestDate = sdf.format(calendar.getTime());

            // Add to ArrayList
            gardens.add(new Garden(vegetableId, harvestDate, imageName));

            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(gardens);
            editor.putString(PREFS_DATA, json);
            editor.apply();
            Toast.makeText(this, "Added to planner", Toast.LENGTH_SHORT).show();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Garden> loadData() {

        ArrayList<Garden> gardens;

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(PREFS_DATA, null);
        Type type = new TypeToken<ArrayList<Garden>>() {}.getType();

        gardens = gson.fromJson(json, type);

        if(gardens == null) {
            gardens = new ArrayList<>();
        }

        return gardens;
    }

    public void openTutorialActivity() {

        String uri = this.videoUrl;

        if(hasConnection()) {

            Intent onlineIntent = new Intent(this, TutorialActivity.class);
            onlineIntent.putExtra(EXTRA_URI, uri);

            Intent offlineIntent = new Intent(this, OfflineTutorialActivity.class);
            offlineIntent.putExtra(EXTRA_VEGETABLE_ID, txtid.getText().toString());

            AlertDialog.Builder builder = new AlertDialog.Builder(ListdataActivity.this);
            builder.setMessage("Do you want to proceed to video tutorial or the offline documents")
                    .setCancelable(true)
                    .setPositiveButton("Video", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(onlineIntent);
                        }
                    })
                    .setNegativeButton("Document", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(offlineIntent);
                        }
                    })
                    .show();

        }   else {
            showOptionDialog();
        }

    }

    public boolean hasConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ListdataActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    public void showOptionDialog() {

        Intent intent = new Intent(this, OfflineTutorialActivity.class);
        intent.putExtra(EXTRA_VEGETABLE_ID, txtid.getText().toString());

        AlertDialog.Builder builder = new AlertDialog.Builder(ListdataActivity.this);
        builder.setMessage("You have no internet connection, Proceed to Offline Tutorial Mode instead ?")
                .setCancelable(true)
                .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(intent);
                    }
                })
                .show();

    }

}