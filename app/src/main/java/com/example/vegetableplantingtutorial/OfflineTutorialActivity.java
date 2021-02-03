package com.example.vegetableplantingtutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.adapters.ViewPagerAdapter;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;

public class OfflineTutorialActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private WormDotsIndicator wormDotsIndicator;

    // White dots indicator
    private TextView[] dots;
    public ArrayList<PlantingStep> steps;
    private String vegetableId;
    private SliderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_tutorial);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        vegetableId = intent.getStringExtra(ListdataActivity.EXTRA_VEGETABLE_ID);

        viewPager = findViewById(R.id.slider_view_pager);
        wormDotsIndicator = findViewById(R.id.worm_dots_indicator);
//        PlantingStepController con = new PlantingStepController(this);
//        steps = con.getPlantingStep(vegetableId);

        adapter = new SliderAdapter(this,vegetableId);
        viewPager.setAdapter(adapter);
        wormDotsIndicator.setViewPager(viewPager);

    }
//    public void addDotsIndicator() {
//        TextView[] dots = new TextView[4];
//
//        for(int i = 0; i < dots.length; i++) {
//            dots[i] = new TextView(this);
//            dots[i].setText("O");
//            dots[i].setTextSize(35);
//            dots[i].setTextColor(Color.rgb(255, 255, 255));
//
//            dotsLinearLayout.addView(dots[i]);
//        }
//    }

}