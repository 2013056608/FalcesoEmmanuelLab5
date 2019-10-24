package com.falceso.falcesoemmanuellab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int[] draw = {R.drawable.android0, R.drawable.petit, R.drawable.cupcake, R.drawable.donut, R.drawable.eclair,
            R.drawable.froyo, R.drawable.gingerbread, R.drawable.honeycomb, R.drawable.icecream,
            R.drawable.jellybean, R.drawable.kitkat, R.drawable.lollipop, R.drawable.marshmallow,
            R.drawable.nougat, R.drawable.oreo, R.drawable.pie, R.drawable.android10};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] androidVer = getResources().getStringArray(R.array.android);
        LinearLayout mainLayout = findViewById(R.id.main_layout);

        TextView text;

        for(int i = 0; i < androidVer.length; i++){
            LinearLayout currLayout = new LinearLayout(this);

            ImageView img = new ImageView(this);


            img.setImageResource(draw[1]);
            currLayout.addView(img);
            mainLayout.addView(currLayout);

        }





    }
}
