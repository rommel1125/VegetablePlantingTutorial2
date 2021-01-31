package com.example.vegetableplantingtutorial;

import android.content.Context;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class VegetableController {

    Context context;

    public VegetableController(Context context) {
        this.context = context;
    }


    /**************************************
     *
     *  Get a Single Vegetable using
     *  Vegetable ID as the filter
     *
    **************************************/
    public Vegetables getVegetableById(String vegetableId) {

        Vegetables vegetable = null;
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
                JSONObject vegetableObject = vegetables.getJSONObject(i);
                if(vegetableObject.getString("id").equals(vegetableId)) {
                    String id = vegetableObject.getString("id");
                    String name = vegetableObject.getString("name");
                    String description = vegetableObject.getString("description");
                    String url = vegetableObject.getString("url");
                    String imageName = vegetableObject.getString("image");
                    String category = vegetableObject.getString("category");

                    vegetable = new Vegetables(id, name, description, url, imageName, category);
                }
            }
            return vegetable;

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**************************************
     *
     *  Get the List of Vegetables by
     *  using Category as filter
     *
     **************************************/
    public ArrayList<Vegetables> getVegetablesByCategory(String category_id) {

        ArrayList<Vegetables> vegetables = new ArrayList<>();

        String json;
        try {

            InputStream is = this.context.getAssets().open("vegetables.json");
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray vegetableArray = new JSONArray(json);

            for(int i = 0; i < vegetableArray.length(); i++) {
                JSONObject vegetableObject = vegetableArray.getJSONObject(i);
                if(vegetableObject.getString("category").equals(category_id)) {
                    String id = vegetableObject.getString("id");
                    String name = vegetableObject.getString("name");
                    String description = vegetableObject.getString("description");
                    String url = vegetableObject.getString("url");
                    String imageName = vegetableObject.getString("image");
                    String category = vegetableObject.getString("category");

                    vegetables.add(new Vegetables(id, name, description, url, imageName, category));
                }
            }
            return vegetables;

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
