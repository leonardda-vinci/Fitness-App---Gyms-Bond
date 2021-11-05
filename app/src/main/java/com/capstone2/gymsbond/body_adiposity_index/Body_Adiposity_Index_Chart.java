package com.capstone2.gymsbond.body_adiposity_index;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.capstone2.gymsbond.R;
import com.capstone2.gymsbond.utils.GlobalFunction;
import com.capstone2.gymsbond.utils.SharedPreferenceManager;
import com.capstone2.gymsbond.utils.TypefaceManager;


public class Body_Adiposity_Index_Chart extends Activity {
    String TAG = getClass().getSimpleName();
    AdView adView;
    GlobalFunction globalFunction;
    ImageView iv_back;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_Active;
    TextView tv_Active_male;
    TextView tv_age;
    TextView tv_age1_female;
    TextView tv_age1_male;
    TextView tv_age2_female;
    TextView tv_age2_male;
    TextView tv_age3_female;
    TextView tv_age3_male;
    TextView tv_age_male;
    TextView tv_cal_require;
    TextView tv_cal_require_male;
    TextView tv_female_healthy1;
    TextView tv_female_healthy2;
    TextView tv_female_healthy3;
    TextView tv_female_obese1;
    TextView tv_female_obese2;
    TextView tv_female_obese3;
    TextView tv_female_overweight1;
    TextView tv_female_overweight2;
    TextView tv_female_overweight3;
    TextView tv_female_underweight1;
    TextView tv_female_underweight2;
    TextView tv_female_underweight3;
    TextView tv_lowactivity;
    TextView tv_lowactivity_male;
    TextView tv_male_healthy1;
    TextView tv_male_healthy2;
    TextView tv_male_healthy3;
    TextView tv_male_obese1;
    TextView tv_male_obese2;
    TextView tv_male_obese3;
    TextView tv_male_overweight1;
    TextView tv_male_overweight2;
    TextView tv_male_overweight3;
    TextView tv_male_underweight1;
    TextView tv_male_underweight2;
    TextView tv_male_underweight3;
    TextView tv_obesed;
    TextView tv_obesed_male;
    TextView tv_sedentary;
    TextView tv_sedentary_male;
    TextView tv_title;
    TypefaceManager typefaceManager;


    public void attachBaseContext(Context context) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(context));
    }


    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.body_adiposity_index_chart);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.adView = findViewById(R.id.adView);
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.iv_back = findViewById(R.id.iv_back);
        this.tv_title = findViewById(R.id.tv_title);
        this.tv_cal_require = findViewById(R.id.tv_cal_require);
        this.tv_age = findViewById(R.id.tv_age);
        this.tv_sedentary = findViewById(R.id.tv_sedentary);
        this.tv_lowactivity = findViewById(R.id.tv_lowactivity);
        this.tv_Active = findViewById(R.id.tv_Active);
        this.tv_obesed = findViewById(R.id.tv_obesed);
        this.tv_cal_require_male = findViewById(R.id.tv_cal_require_male);
        this.tv_age_male = findViewById(R.id.tv_age_male);
        this.tv_sedentary_male = findViewById(R.id.tv_sedentary_male);
        this.tv_lowactivity_male = findViewById(R.id.tv_lowactivity_male);
        this.tv_Active_male = findViewById(R.id.tv_Active_male);
        this.tv_obesed_male = findViewById(R.id.tv_obesed_male);
        this.tv_age1_male = findViewById(R.id.tv_age1_male);
        this.tv_male_underweight1 = findViewById(R.id.tv_male_underweight1);
        this.tv_male_healthy1 = findViewById(R.id.tv_male_healthy1);
        this.tv_male_overweight1 = findViewById(R.id.tv_male_overweight1);
        this.tv_male_obese1 = findViewById(R.id.tv_male_obese1);
        this.tv_age2_male = findViewById(R.id.tv_age2_male);
        this.tv_male_underweight2 = findViewById(R.id.tv_male_underweight2);
        this.tv_male_healthy2 = findViewById(R.id.tv_male_healthy2);
        this.tv_male_overweight2 = findViewById(R.id.tv_male_overweight2);
        this.tv_male_obese2 = findViewById(R.id.tv_male_obese2);
        this.tv_age3_male = findViewById(R.id.tv_age3_male);
        this.tv_male_underweight3 = findViewById(R.id.tv_male_underweight3);
        this.tv_male_healthy3 = findViewById(R.id.tv_male_healthy3);
        this.tv_male_overweight3 = findViewById(R.id.tv_male_overweight3);
        this.tv_male_obese3 = findViewById(R.id.tv_male_obese3);
        this.tv_age1_female = findViewById(R.id.tv_age1_female);
        this.tv_female_underweight1 = findViewById(R.id.tv_female_underweight1);
        this.tv_female_healthy1 = findViewById(R.id.tv_female_healthy1);
        this.tv_female_overweight1 = findViewById(R.id.tv_female_overweight1);
        this.tv_female_obese1 = findViewById(R.id.tv_female_obese1);
        this.tv_age2_female = findViewById(R.id.tv_age2_female);
        this.tv_female_underweight2 = findViewById(R.id.tv_female_underweight2);
        this.tv_female_healthy2 = findViewById(R.id.tv_female_healthy2);
        this.tv_female_overweight2 = findViewById(R.id.tv_female_overweight2);
        this.tv_female_obese2 = findViewById(R.id.tv_female_obese2);
        this.tv_age3_female = findViewById(R.id.tv_age3_female);
        this.tv_female_underweight3 = findViewById(R.id.tv_female_underweight3);
        this.tv_female_healthy3 = findViewById(R.id.tv_female_healthy3);
        this.tv_female_overweight3 = findViewById(R.id.tv_female_overweight3);
        this.tv_female_obese3 = findViewById(R.id.tv_female_obese3);
        this.tv_title.setTypeface(this.typefaceManager.getBold());
        this.tv_cal_require.setTypeface(this.typefaceManager.getBold());
        this.tv_age.setTypeface(this.typefaceManager.getBold());
        this.tv_sedentary.setTypeface(this.typefaceManager.getBold());
        this.tv_lowactivity.setTypeface(this.typefaceManager.getBold());
        this.tv_Active.setTypeface(this.typefaceManager.getBold());
        this.tv_obesed.setTypeface(this.typefaceManager.getBold());
        this.tv_cal_require_male.setTypeface(this.typefaceManager.getBold());
        this.tv_age_male.setTypeface(this.typefaceManager.getBold());
        this.tv_sedentary_male.setTypeface(this.typefaceManager.getBold());
        this.tv_lowactivity_male.setTypeface(this.typefaceManager.getBold());
        this.tv_Active_male.setTypeface(this.typefaceManager.getBold());
        this.tv_obesed_male.setTypeface(this.typefaceManager.getBold());
        this.tv_age1_male.setTypeface(this.typefaceManager.getBold());
        this.tv_male_underweight1.setTypeface(this.typefaceManager.getLight());
        this.tv_male_healthy1.setTypeface(this.typefaceManager.getLight());
        this.tv_male_overweight1.setTypeface(this.typefaceManager.getLight());
        this.tv_male_obese1.setTypeface(this.typefaceManager.getLight());
        this.tv_age2_male.setTypeface(this.typefaceManager.getBold());
        this.tv_male_underweight2.setTypeface(this.typefaceManager.getLight());
        this.tv_male_healthy2.setTypeface(this.typefaceManager.getLight());
        this.tv_male_overweight2.setTypeface(this.typefaceManager.getLight());
        this.tv_male_obese2.setTypeface(this.typefaceManager.getLight());
        this.tv_age3_male.setTypeface(this.typefaceManager.getBold());
        this.tv_male_underweight3.setTypeface(this.typefaceManager.getLight());
        this.tv_male_healthy3.setTypeface(this.typefaceManager.getLight());
        this.tv_male_overweight3.setTypeface(this.typefaceManager.getLight());
        this.tv_male_obese3.setTypeface(this.typefaceManager.getLight());
        this.tv_age1_female.setTypeface(this.typefaceManager.getBold());
        this.tv_female_underweight1.setTypeface(this.typefaceManager.getLight());
        this.tv_female_healthy1.setTypeface(this.typefaceManager.getLight());
        this.tv_female_overweight1.setTypeface(this.typefaceManager.getLight());
        this.tv_female_obese1.setTypeface(this.typefaceManager.getLight());
        this.tv_age2_female.setTypeface(this.typefaceManager.getBold());
        this.tv_female_underweight2.setTypeface(this.typefaceManager.getLight());
        this.tv_female_healthy2.setTypeface(this.typefaceManager.getLight());
        this.tv_female_overweight2.setTypeface(this.typefaceManager.getLight());
        this.tv_female_obese2.setTypeface(this.typefaceManager.getLight());
        this.tv_age3_female.setTypeface(this.typefaceManager.getBold());
        this.tv_female_underweight3.setTypeface(this.typefaceManager.getLight());
        this.tv_female_healthy3.setTypeface(this.typefaceManager.getLight());
        this.tv_female_overweight3.setTypeface(this.typefaceManager.getLight());
        this.tv_female_obese3.setTypeface(this.typefaceManager.getLight());
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        if (this.sharedPreferenceManager.get_Remove_Ad().booleanValue()) {
            this.adView.setVisibility(8);
        } else {
            this.adView.setVisibility(0);
            this.adView.loadAd(new Builder().build());
            this.adView.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    super.onAdLoaded();
                    Body_Adiposity_Index_Chart.this.adView.setVisibility(0);
                }

                public void onAdFailedToLoad(int i) {
                    Body_Adiposity_Index_Chart.this.adView.setVisibility(8);
                }
            });
        }
        this.iv_back.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Body_Adiposity_Index_Chart.this.onBackPressed();
            }
        });
    }


    public void onResume() {
        super.onResume();
        if (!this.sharedPreferenceManager.get_Remove_Ad().booleanValue()) {
            this.adView.setVisibility(0);
        } else {
            this.adView.setVisibility(8);
        }
    }
}
