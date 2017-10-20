package com.juborajsarker.mindreader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Random;

public class GuessSymbolActivity extends AppCompatActivity {

    ImageView[] iv = new ImageView[100];
    ImageView[] imageView = new ImageView[100] ;

    Random random;
    int imageID;
    int specialNumber;
    Bitmap bitmap;


    int [] images = {R.drawable.symbol_1, R.drawable.symbol_2, R.drawable.symbol_3, R.drawable.symbol_4, R.drawable.symbol_5,
            R.drawable.symbol_6, R.drawable.symbol_7, R.drawable.symbol_8, R.drawable.symbol_9, R.drawable.symbol_10};

    int[] imageViewID =
            { R.id.iv0, R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9, R.id.iv10,
            R.id.iv11, R.id.iv12, R.id.iv13, R.id.iv14, R.id.iv15, R.id.iv16, R.id.iv17, R.id.iv18, R.id.iv19, R.id.iv20,
            R.id.iv21, R.id.iv22, R.id.iv23, R.id.iv24, R.id.iv25, R.id.iv26, R.id.iv27, R.id.iv28, R.id.iv29, R.id.iv30,
            R.id.iv31, R.id.iv32, R.id.iv33, R.id.iv34, R.id.iv35, R.id.iv36, R.id.iv37, R.id.iv38, R.id.iv39, R.id.iv40,
            R.id.iv41, R.id.iv42, R.id.iv43, R.id.iv44, R.id.iv45, R.id.iv46, R.id.iv47, R.id.iv48, R.id.iv49, R.id.iv50,
            R.id.iv51, R.id.iv52, R.id.iv53, R.id.iv54, R.id.iv55, R.id.iv56, R.id.iv57, R.id.iv58, R.id.iv59, R.id.iv60,
            R.id.iv61, R.id.iv62, R.id.iv63, R.id.iv64, R.id.iv65, R.id.iv66, R.id.iv67, R.id.iv68, R.id.iv69, R.id.iv70,
            R.id.iv71, R.id.iv72, R.id.iv73, R.id.iv74, R.id.iv75, R.id.iv76, R.id.iv77, R.id.iv78, R.id.iv79, R.id.iv80,
            R.id.iv81, R.id.iv82, R.id.iv83, R.id.iv84, R.id.iv85, R.id.iv86, R.id.iv87, R.id.iv88, R.id.iv89, R.id.iv90,
            R.id.iv91, R.id.iv92, R.id.iv93, R.id.iv94, R.id.iv95, R.id.iv96, R.id.iv97, R.id.iv98, R.id.iv99 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_guess_symbol);

        init();
        setImage();
        specialCase();
    }




    private void init() {

        for (int j=0; j<100; j++){

            iv[j] = (ImageView) findViewById(imageViewID[j]);
        }


        for (int i=0; i<100; i++){

            imageView[i] = iv[i];
        }
    }


    private void setImage() {

        for (int i=0; i<100; i++){

            random = new Random();
            imageID = random.nextInt(10);

            imageView[i].setImageDrawable(getResources().getDrawable( images[imageID] ));
        }
    }



    private void specialCase() {


        random = new Random();
        specialNumber = random.nextInt(10);

        imageView[0].setImageDrawable(getResources().getDrawable( images[specialNumber] ));
        imageView[9].setImageDrawable(getResources().getDrawable( images[specialNumber] ));
        imageView[18].setImageDrawable(getResources().getDrawable( images[specialNumber] ));
        imageView[27].setImageDrawable(getResources().getDrawable( images[specialNumber] ));
        imageView[36].setImageDrawable(getResources().getDrawable( images[specialNumber] ));
        imageView[45].setImageDrawable(getResources().getDrawable( images[specialNumber] ));
        imageView[54].setImageDrawable(getResources().getDrawable( images[specialNumber] ));
        imageView[63].setImageDrawable(getResources().getDrawable( images[specialNumber] ));
        imageView[72].setImageDrawable(getResources().getDrawable( images[specialNumber] ));
        imageView[81].setImageDrawable(getResources().getDrawable( images[specialNumber] ));
        imageView[90].setImageDrawable(getResources().getDrawable( images[specialNumber] ));
        imageView[99].setImageDrawable(getResources().getDrawable( images[specialNumber] ));

    }


    public void gotoAnswerActivity(View view) {


        Intent intent = new Intent(GuessSymbolActivity.this, SymbolAnswerActivity.class);
        intent.putExtra("imageID", specialNumber);
        startActivity(intent);

    }
}
