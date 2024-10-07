package com.example.przeksztalcenie;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public ImageView fishImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button loadButton = findViewById(R.id.loadButton);
        Button transformButton = findViewById(R.id.transformButton);
        fishImg = findViewById(R.id.imageView);
        Boolean isLoaded = false;


        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fishImg.setImageResource(R.drawable.ryba);
            }
        });

        transformButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transform();
            }
        });
    }

    private Bitmap randomize(Bitmap original){
        Bitmap newbit = Bitmap.createBitmap(original.getWidth(),
                original.getHeight(),original.getConfig());
        Random rng = new Random();
        for (int i = 0;i<original.getWidth();i++) {
            for (int j = 0; j < original.getHeight(); j++) {
                int color = original.getPixel(i,j);
                int newx = Math.min(Math.max(i + rng.nextInt(7) - 3,0),original.getWidth() - 1);
                int newy = Math.min(Math.max(j + rng.nextInt(7) - 3,0),original.getHeight() - 1);
                newbit.setPixel(newx,newy,color);
            }
        }
        return newbit;
    }


    private void transform(){
        Drawable fishDrawable = fishImg.getDrawable();
        if (fishDrawable instanceof BitmapDrawable) {
            Bitmap original = ((BitmapDrawable) fishDrawable).getBitmap();
            Bitmap randomized = randomize(original);
            fishImg.setImageBitmap(randomized);
            TextView text = findViewById(R.id.textView);
            text.setText("w:" + randomized.getWidth() + "h:" + randomized.getHeight()+"total:"+(randomized.getHeight()*randomized.getWidth()));

        }
    }



}
