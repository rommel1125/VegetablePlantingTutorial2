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

public class OfflineTutorialActivity extends AppCompatActivity {

    private ViewPager sliderViewPager;
    private LinearLayout dotsLinearLayout;

    // White dots indicator
    private TextView[] dots;

    private String vegetableId;
    private SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_tutorial);

        Intent intent = getIntent();
        vegetableId = intent.getStringExtra(ListdataActivity.EXTRA_VEGETABLE_ID);

        sliderViewPager = findViewById(R.id.slider_view_pager);
        dotsLinearLayout = findViewById(R.id.slider_dots_layout);

        sliderAdapter = new SliderAdapter(this, vegetableId);
        sliderViewPager.setAdapter(sliderAdapter);

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