package com.capstone2.gymsbond.smoking_risk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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


public class Smoking_Risk_Result extends Activity {
    String TAG = getClass().getSimpleName();
    AdView adView;
    Bundle extras;
    GlobalFunction globalFunction;
    ImageView iv_close;
    SharedPreferenceManager sharedPreferenceManager;
    String smoking_risk_msg;
    TextView tv_smoking_risk_result;
    TypefaceManager typefaceManager;


    public void attachBaseContext(Context context) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(context));
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_smoking_risk);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.iv_close = findViewById(R.id.iv_close);
        this.tv_smoking_risk_result = findViewById(R.id.tv_smoking_risk_result);
        this.adView = findViewById(R.id.adView);
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        this.tv_smoking_risk_result.setTypeface(this.typefaceManager.getLight());
        this.extras = getIntent().getExtras();
        this.smoking_risk_msg = this.extras.getString("smoking_risk_msg");
        this.tv_smoking_risk_result.setText(this.smoking_risk_msg);
        if (this.sharedPreferenceManager.get_Remove_Ad().booleanValue()) {
            this.adView.setVisibility(8);
        } else {
            this.adView.setVisibility(0);
            this.adView.loadAd(new Builder().build());
            this.adView.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    super.onAdLoaded();
                    Smoking_Risk_Result.this.adView.setVisibility(0);
                }

                public void onAdFailedToLoad(int i) {
                    Smoking_Risk_Result.this.adView.setVisibility(8);
                }
            });
        }
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Smoking_Risk_Result.this.onBackPressed();
            }
        });
    }
}
