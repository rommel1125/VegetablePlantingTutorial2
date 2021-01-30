package com.example.vegetableplantingtutorial;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryGridAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<VegetableModel> vegetables;

    public CategoryGridAdapter(Context context, ArrayList<VegetableModel> vegetables) {

        this.context = context;
        this.vegetables = vegetables;

    }

    @Override
    public int getCount() {
        return vegetables.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.row_item, null);
        }

        int[] vegeImages = {R.drawable.eggplant,R.drawable.tomato,R.drawable.pechay,R.drawable.sitaw,R.drawable.kalabasa,R.drawable.malunggay,R.drawable.kangkong,R.drawable.monggo,R.drawable.upo,R.drawable.ampalaya};

        ImageView imageView = convertView.findViewById(R.id.card_image_view);
        TextView textView = convertView.findViewById(R.id.card_text_view);

        int index = vegetables.get(position).getId();
        imageView.setImageResource(vegeImages[index -1]);
        textView.setText(vegetables.get(position).getName());


        return convertView;
    }
}
