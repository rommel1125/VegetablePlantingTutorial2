package com.example.vegetableplantingtutorial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category,container,false);

        // Assign variable
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);



        // prepare view pager
        array();

        //Setup with view pager
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void array(){
        ArrayList<CategoryModel> categories = new ArrayList<>();
        categories.add(new CategoryModel("1", "JAN-MAR"));
        categories.add(new CategoryModel("2", "APR-JUN"));
        categories.add(new CategoryModel("3", "JUL-SEP"));
        categories.add(new CategoryModel("4", "OCT-NOV"));
        prepareViewPager(viewPager, categories);
    }
    private void prepareViewPager(ViewPager viewPager, ArrayList<CategoryModel> categories) {

        // Initialize main adapter
        MainAdapter adapter = new MainAdapter(getChildFragmentManager());

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
