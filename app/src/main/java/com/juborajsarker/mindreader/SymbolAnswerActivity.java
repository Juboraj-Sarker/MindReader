package com.juborajsarker.mindreader;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class SymbolAnswerActivity extends AppCompatActivity {


    InterstitialAd mInterstitialAd;

    int receiveImageID;
    ImageView imageView;
    Animation animation;

    TextView symbolTv;
    LinearLayout linearLayout, linearLayoutBlank;

    int [] images = {R.drawable.symbol_1, R.drawable.symbol_2, R.drawable.symbol_3, R.drawable.symbol_4, R.drawable.symbol_5,
            R.drawable.symbol_6, R.drawable.symbol_7, R.drawable.symbol_8, R.drawable.symbol_9, R.drawable.symbol_10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_symbol_answer);

        symbolTv = (TextView) findViewById(R.id.symbolTV);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayoutBlank = (LinearLayout) findViewById(R.id.linearLayoutBlank);

        animation = AnimationUtils.loadAnimation(this, R.anim.mytransition);

        imageView = (ImageView) findViewById(R.id.setImage);

        Intent intent = getIntent();
        receiveImageID = intent.getIntExtra("imageID",0);




        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms

                symbolTv.setText("The symbol in your mind is:");
                imageView.setImageDrawable(getResources().getDrawable( images[receiveImageID] ));
                imageView.setAnimation(animation);
                linearLayoutBlank.setVisibility(LinearLayout.GONE);
                linearLayout.setVisibility(LinearLayout.VISIBLE);
                linearLayout.setAnimation(animation);

            }
        }, 5000);

    }

    public void home(View view) {

        startActivity(new Intent(SymbolAnswerActivity.this, HomeActivity.class));
    }

    public void playAgain(View view) {


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen1));

        AdRequest adRequest = new AdRequest.Builder().addTestDevice("93448558CC721EBAD8FAAE5DA52596D3").build(); //add test device
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });


        startActivity(new Intent(SymbolAnswerActivity.this, SymbolInstructionActivity.class));
    }



    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
