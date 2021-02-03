package com.example.vegetableplantingtutorial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ImageSlider imageSlider;
    public final String[] nameVeg = {"Eggplant\n(Talong)","Tomato\n(Kamatis)","Cabbage\n(Repolyo)","String Beans\n(Sitaw)","Squash\n(Kalabasa)","Moringa Leaves\n(Malunggay)","Water Spinach\n(Kangkong)","Lettuce\n(Letsugas)","Bottle gourd\n(Upo)","Bitter gourd or Bitter melon\n(Ampalaya)"};
    public final int imageVeg[] = {R.drawable.eggplant,R.drawable.tomato,R.drawable.repolyo,R.drawable.sitaw,R.drawable.kalabasa,R.drawable.malunggay,R.drawable.kangkong,R.drawable.lettuce,R.drawable.upo,R.drawable.ampalaya};
    public final String[] number = {"1","2","3","4","5","6","7","8","9","10"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
                imageSlider = view.findViewById(R.id.image_slider);
        imageSlide();
        return view;
    }

    public void imageSlide(){
        List<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.eggplant1,"Eggplant", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.tomato1,"Tomato", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.cabbage1,"Cabbage", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.sitaw1,"String Beans", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.kalabasa1,"Squash", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.malunggay1,"Moringa Leaves", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.kangkong1,"Water Spinach", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.lettuce1,"Lettuce", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.upo1,"Bottle gourd", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.ampalaya1,"Bitter Melon", ScaleTypes.FIT));

        imageSlider.setImageList(imageList,ScaleTypes.FIT);
        imageSlider.startSliding(2000);

        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Intent intent = new Intent(getActivity(),ListdataActivity.class);
                intent.putExtra("name", nameVeg[i]);
                intent.putExtra("image",imageVeg[i]);
                intent.putExtra("id23",number[i]);
                startActivity(intent);
            }
        });
    }
}
