package com.example.vegetableplantingtutorial;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CategoryFragment extends Fragment {
    private static final String TAG = "CategoryFragment";

    DatabaseHelper db;
    private Button btnAdd, btnView;
    private EditText namevege, desvege;
    private TextView textView;
    private int id = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        btnAdd = view.findViewById(R.id.btnAdd);
        namevege = view.findViewById(R.id.namevege);
        desvege = view.findViewById(R.id.desvege);
        textView = view.findViewById(R.id.txtView);
        btnView = view.findViewById(R.id.btnView);

        db = new DatabaseHelper(getActivity());


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = namevege.getText().toString();
                String newDes = desvege.getText().toString();

                if (namevege.length() != 0 && desvege.length() != 0){
                    AddData(newName,newDes);
                    namevege.setText("");
                    desvege.setText("");
                }
                else {
                    toastMessage("You must enter some text");
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                }

        });

        return view;
    }

    public void AddData(String name1,String des1){
        boolean insertData = db.addData(name1,des1);

        if (insertData){
            toastMessage("Added Successfully");
        }
        else {
            toastMessage("Something went wrong");
        }

    }
    private void toastMessage(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
}
