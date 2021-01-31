package com.example.vegetableplantingtutorial;

import android.content.Context;
import android.content.res.Resources;
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
    private ArrayList<Vegetables> vegetables;

    public CategoryGridAdapter(Context context, ArrayList<Vegetables> vegetables) {

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

        ImageView imageView = convertView.findViewById(R.id.card_image_view);
        TextView textView = convertView.findViewById(R.id.card_text_view);

        String index = vegetables.get(position).getId();

        Resources  resources = this.context.getResources();
        final int resourceId = resources.getIdentifier(vegetables.get(position).getImageName(), "drawable", this.context.getPackageName());
        imageView.setImageResource(resourceId);

        textView.setText(vegetables.get(position).getName());


        return convertView;
    }
}
