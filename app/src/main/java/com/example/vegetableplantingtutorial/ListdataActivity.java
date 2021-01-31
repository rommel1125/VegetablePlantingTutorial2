package com.example.vegetableplantingtutorial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class ListdataActivity extends AppCompatActivity {

    public static final String EXTRA_URI = "com.example.vegetableplantingtutorial.EXTRA_URI";
    public static final String EXTRA_VEGETABLE_ID = "com.example.vegetableplantingtutorial.EXTRA_VEGETABLE_ID";

    TextView name,des, txtid;
    ImageView image;
    Button tutorialButton;

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

        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTutorialActivity();
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