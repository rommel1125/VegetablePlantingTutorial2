package com.example.vegetableplantingtutorial;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.google.android.material.slider.Slider;

import java.util.ArrayList;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;

   public ArrayList<PlantingStep> steps;

    public SliderAdapter(Context context, String vegetable_id) {

        this.context = context;

        PlantingStepController con = new PlantingStepController(context);
        steps = con.getPlantingStep(vegetable_id);

    }

    @Override
    public int getCount() {
        return steps.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide_layout, container, false);

        TextView stepTitle = view.findViewById(R.id.step_title);
        TextView stepProcess = view.findViewById(R.id.step_process);
        ImageView stepImage = view.findViewById(R.id.step_image_view);

        stepTitle.setText(steps.get(position).getName());
        stepProcess.setText(steps.get(position).getProcess());

        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(steps.get(position).getImageName(), "drawable", context.getPackageName());
        stepImage.setImageResource(resourceId);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
