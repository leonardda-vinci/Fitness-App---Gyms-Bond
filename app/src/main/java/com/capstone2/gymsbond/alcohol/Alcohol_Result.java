package com.capstone2.gymsbond.alcohol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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


public class Alcohol_Result extends Activity {
    Double BACinPer;
    String TAG = getClass().getSimpleName();
    AdView adView;
    Bundle extras;
    GlobalFunction globalFunction;
    ImageView iv_close;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_alcohol_result;
    TextView tv_alcohol_result_chart;
    TypefaceManager typefaceManager;


    public void attachBaseContext(Context context) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(context));
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_alcohol);
        this.globalFunction = new GlobalFunction(this);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.tv_alcohol_result = findViewById(R.id.tv_alcohol_result);
        this.tv_alcohol_result_chart = findViewById(R.id.tv_alcohol_result_chart);
        this.iv_close = findViewById(R.id.iv_close);
        this.adView = findViewById(R.id.adView);
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        this.tv_alcohol_result.setTypeface(this.typefaceManager.getLight());
        this.tv_alcohol_result_chart.setTypeface(this.typefaceManager.getBold());
        this.extras = getIntent().getExtras();
        this.BACinPer = Double.valueOf(this.extras.getDouble("BACinPer"));
        if (this.BACinPer.doubleValue() < 0.0d) {
            TextView textView = this.tv_alcohol_result;
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.BAC_level));
            sb.append(" : 0");
            textView.setText(sb.toString());
        } else {
            TextView textView2 = this.tv_alcohol_result;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getString(R.string.BAC_level));
            sb2.append(" : %.2f");
            textView2.setText(String.format(sb2.toString(), this.BACinPer));
        }
        this.tv_alcohol_result_chart.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int random = ((int) (Math.random() * 2.0d)) + 1;
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("random_number==>");
                sb.append(random);
                printStream.println(sb.toString());
                Alcohol_Result.this.startActivity(new Intent(Alcohol_Result.this, Alcohol_Chart.class));
            }
        });
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Alcohol_Result.this.onBackPressed();
            }
        });
    }
}
