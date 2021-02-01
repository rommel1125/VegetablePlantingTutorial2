package com.example.vegetableplantingtutorial;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PlannerAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<Garden> gardens;

    public PlannerAdapter(Context context, ArrayList<Garden> gardens) {
        super(context, R.layout.planner_row, R.id.planner_row_vegetable_name);

        this.context = context;
        this.gardens = gardens;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.planner_row, parent, false);

        TextView title = row.findViewById(R.id.planner_row_vegetable_name);
        TextView sub = row.findViewById(R.id.planner_row_days_left);
        ImageView image = row.findViewById(R.id.planner_row_image_view);


        VegetableController con = new VegetableController(context);
        Vegetables vege = con.getVegetableById(gardens.get(position).getVegetableId());


        title.setText(vege.getName());
        sub.setText(gardens.get(position).getHarvestDate());

        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(vege.getImageName(), "drawable", context.getPackageName());
        image.setImageResource(resourceId);

        return row;
    }
}
