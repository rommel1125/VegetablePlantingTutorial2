package com.example.vegetableplantingtutorial;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class PlanFragment extends Fragment {
    ListView plannerListView;
    TextView planner_row_vegetable_name, planner_dates,textDate;
    ArrayList<Garden> gardens;

    String plantName[];
    String harvestDate[];
    String imageName[];
    String sDate[];
    String hDate[];

    private static final String PREFS_NAME = "shared_prefs";
    private static final String PREFS_DATA = "shared_data";
    private static final String DATE_FORMAT = "MM/dd/yyyy";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        Intent intent = getActivity().getIntent();
        String name = intent.getStringExtra(ListdataActivity.EXTRA_VEGETABLE_ID);

//        if(getArguments() != null) {
//            getFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new EmptyFragment()).commit();
//        }
//        else{
//
//            String name = this.getArguments().getString(ListdataActivity.EXTRA_VEGETABLE_ID);
//        }

        loadData();
        planner_row_vegetable_name = view.findViewById(R.id.planner_row_vegetable_name);
        textDate = view.findViewById(R.id.textDate);
        planner_dates = view.findViewById(R.id.planner_dates);
        plannerListView = view.findViewById(R.id.planner_list_view);

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = df.format(c);

        textDate.setText(formattedDate);


        if(gardens != null) {
            CustomAdapter adapter = new CustomAdapter(getActivity(), plantName, harvestDate, imageName, sDate, hDate);
            plannerListView.setAdapter(adapter);
        }
        else {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new EmptyFragment()).commit();
        }
        registerForContextMenu(plannerListView);

        plannerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), ClickItemPlan.class);
                    intent.putExtra("name",plantName[position]);
                    intent.putExtra("date",sDate[position]);
                    intent.putExtra("harvestdate",hDate[position]);
                    startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_option, menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int index = info.position;
                new AlertDialog.Builder(getActivity())
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this item")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                removeData(index);
                                loadData();
                                refreshActivity();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;
            case R.id.view:
                AdapterView.AdapterContextMenuInfo info2 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int index1 = info2.position;
                Intent intent = new Intent(getActivity(), ClickItemPlan.class);
                intent.putExtra("name",plantName[index1]);
                intent.putExtra("date",sDate[index1]);
                intent.putExtra("harvestdate",hDate[index1]);
                startActivity(intent);
//                Intent intent = new Intent(getActivity(), ClickItemPlan.class);
//                startActivity(intent);
            default:
                return super.onContextItemSelected(item);
            }
    }
    public void openClickitem(){
        Intent intent = new Intent(getActivity(), ClickItemPlan.class);
        startActivity(intent);
    }

    private int removeData(int position) {

        try {

            gardens.remove(position);

            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(gardens);
            editor.putString(PREFS_DATA, json);
            editor.apply();

        }
        catch(Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG);
        }
        return position;
    }
    private void refreshActivity() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new PlanFragment()).commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadData() {

        try {

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString(PREFS_DATA, null);
            Type type = new TypeToken<ArrayList<Garden>>() {}.getType();

            gardens = gson.fromJson(json, type);

            if(gardens == null) {
                Toast.makeText(getActivity(), "No plans available", Toast.LENGTH_SHORT).show();
                return;
            }

            int size = gardens.size();
            plantName = new String[size];
            harvestDate = new String[size];
            imageName = new String[size];
            sDate = new String[size];
            hDate = new String[size];

            for(int i = 0; i < gardens.size(); i++) {
                VegetableController con = new VegetableController(getActivity());
                Vegetables vege = con.getVegetableById(gardens.get(i).getVegetableId());
                plantName[i] = vege.getName();
                harvestDate[i] = getHarvestDayLeft(gardens.get(i).getHarvestDate());
                imageName[i] = vege.getImageName();
                sDate[i] = gardens.get(i).getPlantedDate();
                hDate[i] = gardens.get(i).getHarvestDate();
            }

            if(gardens == null) {
                Toast.makeText(getActivity(), "No Data in sharedPreferences", Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EmptyFragment()).commit();
            }
            else {
                if(gardens.size() <= 0) {
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new EmptyFragment()).commit();
                }
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getHarvestDayLeft(String harvestDate) {

        try {

            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Calendar calendar = Calendar.getInstance();
            String currentDate = sdf.format(calendar.getTime());
            String finalDate = harvestDate;

            Date date1;
            Date date2;

            date1 = sdf.parse(currentDate);
            date2 = sdf.parse(finalDate);

            long diff = Math.abs(date1.getTime() - date2.getTime());
            long diffDays = diff / (24 * 60 * 60 * 1000);
            String daysLeft = Long.toString(diffDays) + " days left to harvest";

            return daysLeft;

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    class CustomAdapter extends ArrayAdapter<String> {

        Context context;
        String plantName[];
        String date[];
        String imageName[];
        String plantedDate[];
        String harvestDate[];

        public CustomAdapter(Context context, String plantName[], String date[], String imageName[], String plantedDate[], String harvestDate[]) {
            super(context, R.layout.planner_row, R.id.planner_row_vegetable_name, plantName);
            this.context = context;
            this.plantName = plantName;
            this.date = date;
            this.imageName = imageName;
            this.plantedDate = plantedDate;
            this.harvestDate = harvestDate;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.planner_row, parent, false);

            ImageView image = row.findViewById(R.id.planner_row_image_view);
            TextView name = row.findViewById(R.id.planner_row_vegetable_name);
            TextView hDate = row.findViewById(R.id.planner_row_days_left);
            TextView dates = row.findViewById(R.id.planner_dates);

            name.setText(plantName[position]);
            hDate.setText(date[position]);
            dates.setText(plantedDate[position] + " - " + harvestDate[position]);

            Resources resource = this.context.getResources();
            final int rId = resource.getIdentifier(imageName[position], "drawable", context.getPackageName());
            image.setImageResource(rId);

            return row;
        }
    }
}
