package com.capstone2.gymsbond.Body_Surface_Area_Calculator;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.Html;
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


public class BodySurfaceArea_Result extends Activity {
    String TAG = getClass().getSimpleName();
    AdView adView;
    double bsa;
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
        this.rl_main = findViewById(R.id.rl_main);
        this.iv_close = findViewById(R.id.iv_close);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
//        this.rl_main.setBackgroundResource(R.drawable.popup_background_gradient2);
        this.extras = getIntent().getExtras();
        this.bsa = this.extras.getDouble("bsa");
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
                    BodySurfaceArea_Result.this.adView.setVisibility(0);
                }

                public void onAdFailedToLoad(int i) {
                    BodySurfaceArea_Result.this.adView.setVisibility(8);
                }
            });
        }
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BodySurfaceArea_Result.this.onBackPressed();
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(this.bsa);
        Log.d("bmr_val->", sb.toString());
        TextView textView = this.tv_ans_bmr;
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(getString(R.string.your_body_surface_Area));
        sb3.append(" :\n");
        sb3.append(String.format("%.02f", Double.valueOf(this.bsa)));
        sb2.append(sb3.toString());
        sb2.append(" m<sup>2</sup>");
        textView.setText(Html.fromHtml(sb2.toString()));
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
