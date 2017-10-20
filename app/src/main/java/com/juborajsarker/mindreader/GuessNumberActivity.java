package com.juborajsarker.mindreader;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class GuessNumberActivity extends AppCompatActivity {


    InterstitialAd mInterstitialAd;

    TextView mainTV, answerTV ;
    Button nextBtn, plagAgainBtn;
    int clickCount = 1;
    int randomValue;
    Random random;
    int max = 50;
    int min = 10;

    android.os.Handler handler;

    FloatingActionButton fab;

    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guess_number);

        animation = AnimationUtils.loadAnimation(this, R.anim.mytransition);


        mainTV = (TextView) findViewById(R.id.main_text_view);
        answerTV = (TextView) findViewById(R.id.answer_text_view);

        handler = new Handler();



        nextBtn = (Button) findViewById(R.id.next_btn);
        plagAgainBtn = (Button) findViewById(R.id.play_again_btn);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(GuessNumberActivity.this, HomeActivity.class));
            }
        });

        random = new Random();




        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5809082953640465/9562042055");
        AdView mAdView = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("93448558CC721EBAD8FAAE5DA52596D3").build();
        mAdView.loadAd(adRequest);



    }

    public void onClick(View view) {



            clickCount++;

            switch (clickCount){

               case 2:{

                    mainTV.setText("Multiply that number by 2");
                    break;

                }case 3:{

                    generateRandomNumber();
                    mainTV.setText("Add " + String.valueOf(randomValue) + " with it" ) ;
                    break;

                }case 4:{

                    mainTV.setText("Now Divide it by 2");
                    break;

                }case 5:{

                    mainTV.setText("Subtract the number what you think first");
                    break;

                }case 6:{

                    mainTV.setText("The number is in your mind is:");

                    answerTV.setAnimation(animation);
                    answerTV.setText(String.valueOf(randomValue / 2));
                    animation.reset();
                    answerTV.clearAnimation();
                    answerTV.startAnimation(animation);

                    plagAgainBtn.setVisibility(View.VISIBLE);
                    nextBtn.setVisibility(View.GONE);


                    break;

                }default:{

                    nextBtn.setClickable(false);
                }

            }

        }

    private void generateRandomNumber() {

        randomValue = random.nextInt(max - min + 1) + min;
        randomValue = randomValue * 2;
    }

    public void playAgain(View view) {

        clickCount = 1;

        mainTV.setText("Think of a number");
        answerTV.setText(String.valueOf(""));

        plagAgainBtn.setVisibility(View.GONE);

        nextBtn.setClickable(true);
        nextBtn.setVisibility(View.VISIBLE);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen1));

        AdRequest adRequest = new AdRequest.Builder().addTestDevice("93448558CC721EBAD8FAAE5DA52596D3").build(); //add test device
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });



    }


    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


}

