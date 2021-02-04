package com.example.vegetableplantingtutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper myDb;
    DrawerLayout drawer;
    int[] vegeImages = {R.drawable.eggplant,R.drawable.tomato,R.drawable.repolyo,R.drawable.sitaw,R.drawable.kalabasa,R.drawable.malunggay,R.drawable.kangkong,R.drawable.lettuce,R.drawable.upo,R.drawable.ampalaya};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        myDb = new DatabaseHelper(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        //update vegetable image in the database
//        for(int i = 0; i < vegeImages.length; i++) {
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), vegeImages[i]);
//            String id = String.valueOf(i+1);
//            myDb.updateVegetableImage(id, bitmap);
//        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;

            case R.id.nav_vegetable:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new VegetablesFragment()).commit();

                break;

            case R.id.nav_category:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CategoryFragment()).commit();
                break;

            case R.id.nav_plan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PlanFragment()).commit();
                break;

//            case R.id.nav_settings:
//                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.nav_about:
                Aboutus();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }


    public void openCategoryActivity() {

        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);

    }

    public void openPlannerActivity() {
        Intent intent = new Intent(this, PlannerActivity.class);
        startActivity(intent);
    }
    public void Aboutus(){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Vegetable Planting Tutorial")
                .setMessage("\nVersion 1.0\n\nPROGRAMMER: Frogoso, Rommel John \n\nDESIGN: Vidal, Magen David\nGonzal, Anthony James\n\nDOCUMENTS: Ferrer, Brian Charles\nBaritua, Steven John")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

}