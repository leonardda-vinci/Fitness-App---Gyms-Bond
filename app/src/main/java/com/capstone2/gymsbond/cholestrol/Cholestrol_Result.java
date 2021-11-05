package com.capstone2.gymsbond.cholestrol;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.capstone2.gymsbond.R;
import com.capstone2.gymsbond.utils.GlobalFunction;
import com.capstone2.gymsbond.utils.SharedPreferenceManager;
import com.capstone2.gymsbond.utils.TypefaceManager;


public class Cholestrol_Result extends Activity {
    String TAG = getClass().getSimpleName();
    AdView adView;
    double cholestrol;
    Bundle extras;
    GlobalFunction globalFunction;
    ImageView iv_close;
    LinearLayout rl_main;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_ans_bmr;
    TypefaceManager typefaceManager;


    public void attachBaseContext(Context context) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(context));
    }


    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_bmr);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        this.adView = findViewById(R.id.adView);
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        this.iv_close = findViewById(R.id.iv_close);
        this.rl_main = findViewById(R.id.rl_main);
//        this.rl_main.setBackgroundResource(R.drawable.popup_background_gradient7);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.extras = getIntent().getExtras();
        this.cholestrol = this.extras.getDouble("cholestrol");
        this.tv_ans_bmr = findViewById(R.id.tv_ans_bmr);
        this.tv_ans_bmr.setTypeface(this.typefaceManager.getLight());
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
                    Cholestrol_Result.this.adView.setVisibility(0);
                }

                public void onAdFailedToLoad(int i) {
                    Cholestrol_Result.this.adView.setVisibility(8);
                }
            });
        }
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(this.cholestrol);
        Log.d("cholestrol->", sb.toString());
        TextView textView = this.tv_ans_bmr;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getString(R.string.your_cholestrol));
        sb2.append(" : ");
        sb2.append(String.format("%.0f", Double.valueOf(this.cholestrol)));
        textView.setText(sb2.toString());
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Cholestrol_Result.this.onBackPressed();
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
