package com.example.vegetableplantingtutorial;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {

    ImageView plannerImageView;
    TextView plannerTitle;
    TextView plannerSubTitle;

    ViewHolder(View view) {

        plannerImageView = view.findViewById(R.id.planner_row_image_view);
        plannerTitle = view.findViewById(R.id.planner_row_vegetable_name);
        plannerSubTitle = view.findViewById(R.id.planner_row_days_left);

    }

}
