package com.capstone2.gymsbond.calories_burn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
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


public class Calories_Burn_Result extends Activity {
    String TAG = getClass().getSimpleName();
    AdView adView;
    float caloriesburn;
    Bundle extras;
    GlobalFunction globalFunction;
    ImageView iv_close;
    SharedPreferenceManager sharedPreferenceManager;
    String tips;
    TextView tv_ans_calburn;
    TextView tv_calburn;
    TextView tv_calburnchart;
    TextView tv_recomended;
    TypefaceManager typefaceManager;


    public void attachBaseContext(Context context) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(context));
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_calories_burn);
        this.globalFunction = new GlobalFunction(this);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.iv_close = findViewById(R.id.iv_close);
        this.tv_ans_calburn = findViewById(R.id.tv_ans_calburn);
        this.tv_calburn = findViewById(R.id.tv_calburn);
        this.tv_recomended = findViewById(R.id.tv_recomended);
        this.tv_calburnchart = findViewById(R.id.tv_calburnchart);
        this.tv_ans_calburn.setTypeface(this.typefaceManager.getLight());
        this.tv_calburn.setTypeface(this.typefaceManager.getLight());
        this.tv_recomended.setTypeface(this.typefaceManager.getLight());
        this.tv_calburnchart.setTypeface(this.typefaceManager.getBold());
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        this.adView = findViewById(R.id.adView);
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        this.extras = getIntent().getExtras();
        this.caloriesburn = this.extras.getFloat("caloriesburn");
        this.tips = this.extras.getString("tips");
        StringBuilder sb = new StringBuilder();
        sb.append("tips");
        sb.append(this.tips);
        Log.d("tips", sb.toString());
        try {
            TextView textView = this.tv_ans_calburn;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getString(R.string.Calories_burned));
            sb2.append(" : ");
            sb2.append(String.format("%.02f", Float.valueOf(this.caloriesburn)));
            textView.setText(sb2.toString());
            this.tv_recomended.setText(String.valueOf(this.tips));
            this.tv_calburn.setText("");
        } catch (Exception unused) {
        }
        this.tv_calburnchart.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int random = ((int) (Math.random() * 2.0d)) + 1;
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("random_number==>");
                sb.append(random);
                printStream.println(sb.toString());
                Calories_Burn_Result.this.startActivity(new Intent(Calories_Burn_Result.this, Calories_Burn_Chart.class));
            }
        });
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Calories_Burn_Result.this.onBackPressed();
            }
        });
    }
}
