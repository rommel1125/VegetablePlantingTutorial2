package com.example.vegetableplantingtutorial;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class PlantingStepController {

    Context context;

    public PlantingStepController(Context context) {
        this.context = context;
    }

    public ArrayList<PlantingStep> getPlantingStep(String vegetable_id) {

        ArrayList<PlantingStep> steps = new ArrayList<>();

        String json;
        try {

            InputStream is = this.context.getAssets().open("vegetables.json");
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray vegetables = new JSONArray(json);

            for(int i = 0; i < vegetables.length(); i++) {

                JSONObject vegetable = vegetables.getJSONObject(i);
                if(vegetable.getString("id").equals(vegetable_id)) {

                    // Get the stepArray
                    JSONArray stepArray = vegetable.getJSONArray("steps");
                    for(int j = 0; j < stepArray.length(); j++) {

                        JSONObject step = stepArray.getJSONObject(j);
                        String name = step.getString("step_name");
                        String process = step.getString("process");
                        String imageName = step.getString("step_image");

                        steps.add(new PlantingStep(name, process, imageName));
                    }

                }

            }

            return steps;

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
