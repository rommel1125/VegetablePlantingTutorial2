package com.example.vegetableplantingtutorial;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class VegetablesFragment extends Fragment {
    ListView listView;
    String[] vegeName = {"Eggplant\n(Talong)","Tomato\n(Kamatis)","Cabbage\n(Repolyo)","String Beans\n(Sitaw)","Squash\n(Kalabasa)","Moringa Leaves\n(Malunggay)","Water Spinach\n(Kangkong)","Lettuce\n(Letsugas)","Bottle gourd\n(Upo)","Bitter gourd or Bitter melon\n(Ampalaya)"};
    int[] vegeImages = {R.drawable.eggplant,R.drawable.tomato,R.drawable.repolyo,R.drawable.sitaw,R.drawable.kalabasa,R.drawable.malunggay,R.drawable.kangkong,R.drawable.lettuce,R.drawable.upo,R.drawable.ampalaya};
    String[] number = {"1","2","3","4","5","6","7","8","9","10"};
    DatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vegetables,container,false);
        db = new DatabaseHelper(getActivity());
        listView = view.findViewById(R.id.listview);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),ListdataActivity.class);
                intent.putExtra("name",vegeName[i]);
                intent.putExtra("image",vegeImages[i]);
                intent.putExtra("id23",number[i]);
                startActivity(intent);
            }
        });
        return view;
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {

            return vegeImages.length;
        }

        @Override
        public Object getItem(int i) {

            return null;
        }

        @Override
        public long getItemId(int i) {

            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);

            TextView name = view1.findViewById(R.id.vegetables);
            ImageView image = view1.findViewById(R.id.vegetables_images);
//
//            name.setText(vegeName[i]);
//            image.setImageResource(vegeImages[i]);

            Vegetables vegetable = setVegetables(String.valueOf(i+1));

            name.setText(vegetable.getName());

            Resources resources = getActivity().getResources();
            final int resourceId = resources.getIdentifier(vegetable.getImageName(), "drawable", getActivity().getPackageName());
            image.setImageResource(resourceId);

            return view1;
        }

        public Vegetables setVegetables(String id) {

            String json;
            Vegetables vege = null;
            try {

                InputStream is = getActivity().getAssets().open("vegetables.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer, "UTF-8");
                JSONArray jsonArray = new JSONArray(json);

                for(int j = 0; j < jsonArray.length(); j++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(j);
                    Log.d(jsonObject.getString("id"), jsonObject.getString("name"));
                    if(jsonObject.getString("id").equals(id)) {
                        String v_id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String description = jsonObject.getString("description");
                        String url = jsonObject.getString("url");
                        String image = jsonObject.getString("image");

                        vege = new Vegetables(v_id, name, description, url, image);
                    }
                }
                return vege;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
