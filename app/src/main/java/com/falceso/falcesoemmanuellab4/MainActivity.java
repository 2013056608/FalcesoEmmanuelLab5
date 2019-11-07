package com.falceso.falcesoemmanuellab4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int[] draw = {R.drawable.android0, R.drawable.petit, R.drawable.cupcake, R.drawable.donut, R.drawable.eclair,
            R.drawable.froyo, R.drawable.gingerbread, R.drawable.honeycomb, R.drawable.icecream,
            R.drawable.jellybean, R.drawable.kitkat, R.drawable.lollipop, R.drawable.marshmallow,
            R.drawable.nougat, R.drawable.oreo, R.drawable.pie, R.drawable.android10};

    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] androidVer = getResources().getStringArray(R.array.android);
        LinearLayout mainLayout = findViewById(R.id.main_layout);

        file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "android.txt");


        for(int i = 0; i < androidVer.length; i++){
            LinearLayout currLayout = new LinearLayout(this);
            TextView text = new TextView(this);
            ImageView img = new ImageView(this);

            StringBuilder details = new StringBuilder();
            final String[] detail = androidVer[i].split("!");

            Log.d("error",detail[3]);
            final int iterator = i;
            details.append(detail[0]);
            details.append("\nVer. " + detail[1]);
            details.append("\nAPI level " + detail[3]);
            details.append("\nReleased " + detail[2]);

            Bitmap bmp = BitmapFactory.decodeResource(getResources(),draw[i]);
            bmp = Bitmap.createScaledBitmap(bmp,450,300, true);

            img.setImageBitmap(bmp);
            currLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle(detail[0]);
                    dialog.setMessage(detail[4]);
                    dialog.setIcon(draw[iterator]);
                    dialog.setNeutralButton("CLOSE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick (DialogInterface dialog,int which){
                            writeData("API level " + detail[3] + " - Released in " + detail[2]);
                            String detail = readData();
                            Toast.makeText(MainActivity.this, detail, Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.create().show();

                }
            });


            text.setText(details.toString());
            text.setTextSize(25);
            currLayout.addView(img);
            currLayout.addView(text);

            mainLayout.addView(currLayout);

        }

    }

    private String readData() {
        FileInputStream stream = null;
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "android.txt");
        StringBuilder sb = new StringBuilder();
        try {
            stream = new FileInputStream(file);
            int i;
            while ((i = stream.read()) != -1) {
                sb.append((char) i);
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            Log.d("error", "File not found");
        } catch (IOException e) {
            Log.d("error", "IO error");
        }
        return null;
    }

    private void writeData(String data) {
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            stream.write(data.getBytes());
        } catch (FileNotFoundException e) {
            Log.d("error", "File not found");
        } catch (IOException e) {
            Log.d("error", "IO error");
        }
    }
}
