package com.capstone2.gymsbond.heart_rate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
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
import com.capstone2.gymsbond.general.MyApplication;
import com.capstone2.gymsbond.utils.GlobalFunction;
import com.capstone2.gymsbond.utils.SharedPreferenceManager;
import com.capstone2.gymsbond.utils.TypefaceManager;
import com.zplesac.connectionbuddy.ConnectionBuddy;
import com.zplesac.connectionbuddy.interfaces.NetworkRequestCheckListener;
import java.io.PrintStream;


public class Heart_Rate_Result extends Activity {
    String TAG = getClass().getSimpleName();
    AdView adView;
    GlobalFunction globalFunction;
    ImageView iv_close;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_ans_heartrate;
    TextView tv_heartrate_chart;
    TypefaceManager typefaceManager;


    public void attachBaseContext(Context context) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(context));
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_heartrate);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.iv_close = findViewById(R.id.iv_close);
        this.tv_ans_heartrate = findViewById(R.id.tv_ans_heartrate);
        this.tv_heartrate_chart = findViewById(R.id.tv_heartrate_chart);
        this.tv_ans_heartrate.setTypeface(this.typefaceManager.getLight());
        this.tv_heartrate_chart.setTypeface(this.typefaceManager.getBold());
        this.adView = findViewById(R.id.adView);
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        TextView textView = this.tv_ans_heartrate;
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.heart_rate));
        sb.append(Heart_Rate_Calculator.HRmax);
        sb.append(" bpm");
        textView.setText(sb.toString());
        this.tv_heartrate_chart.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int random = ((int) (Math.random() * 2.0d)) + 1;
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("random_number==>");
                sb.append(random);
                printStream.println(sb.toString());
                Heart_Rate_Result.this.startActivity(new Intent(Heart_Rate_Result.this, Heart_Rate_Chart.class));
            }
        });
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Heart_Rate_Result.this.onBackPressed();
            }
        });
    }
}
