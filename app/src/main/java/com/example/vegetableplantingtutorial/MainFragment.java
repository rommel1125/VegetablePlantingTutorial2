package com.example.vegetableplantingtutorial;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //Initialize and assign variable
        GridView gridView = view.findViewById(R.id.grid_view);

        // Get title
//        String sTitle = getArguments().getString("title");
//        textView.setText(sTitle);

        //Get Vegetables by category
        String category_id = getArguments().getString("id");
        DatabaseHelper db = new DatabaseHelper(getActivity());
        ArrayList<VegetableModel> vegetables = db.fetchVegetableByCategory(category_id);

        CategoryGridAdapter adapter = new CategoryGridAdapter(getActivity(), vegetables);
        gridView.setAdapter(adapter);

        Intent intent = new Intent(getActivity(), ListdataActivity.class);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Log.d("Vegetable_ID = ", String.valueOf(vegetables.get(position).getId()));

                //Put extra payload on the intent
                intent.putExtra("name", vegetables.get(position).getName());
                intent.putExtra("id23", String.valueOf(vegetables.get(position).getId()));
                int[] vegeImages = {R.drawable.eggplant,R.drawable.tomato,R.drawable.repolyo,R.drawable.sitaw,R.drawable.kalabasa,R.drawable.malunggay,R.drawable.kangkong,R.drawable.lettuce,R.drawable.upo,R.drawable.ampalaya};
                int v_id = vegetables.get(position).getId();
                intent.putExtra("image", vegeImages[v_id - 1]);

                startActivity(intent);
            }
        });

        // Return view
        return view;
    }
}