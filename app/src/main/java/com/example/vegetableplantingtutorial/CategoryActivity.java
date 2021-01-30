package com.example.vegetableplantingtutorial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    // Initialize variable
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Assign variable
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        // Initialize Arraylist
        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<CategoryModel> categories = db.fetchCategories();

        // prepare view pager
        prepareViewPager(viewPager, categories);

        //Setup with view pager
        tabLayout.setupWithViewPager(viewPager);

    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<CategoryModel> categories) {

        // Initialize main adapter
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());

        // Initialize Main Fragment
        MainFragment fragment = new MainFragment();

        for(int i = 0; i < categories.size(); i++) {

            //Initialize bundle
            Bundle bundle = new Bundle();
            //Put String
            bundle.putString("title", categories.get(i).getCategory_name());
            bundle.putString("id", categories.get(i).getCategory_id());
            //Set Arguments
            fragment.setArguments(bundle);
            //Add Fragment
            adapter.addFragment(fragment, categories.get(i));
            //Define new Fragment
            fragment = new MainFragment();
        }

        //Set adapter
        viewPager.setAdapter(adapter);

    }

    private class MainAdapter extends FragmentPagerAdapter {

        // Initialize Arralist
        ArrayList<CategoryModel> categories = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<Fragment>();

        // Create method
        public void addFragment(Fragment fragment, CategoryModel category) {
            //Add Category
            categories.add(category);

            //Add Fragment
            fragmentList.add(fragment);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return categories.get(position).getCategory_name();
        }
    }
}