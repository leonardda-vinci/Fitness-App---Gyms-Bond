package com.capstone2.gymsbond.cholestrol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
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
import java.util.ArrayList;


public class Cholestrol_Calculator extends Activity {
    String TAG = getClass().getSimpleName();
    AdView adView;
    ArrayAdapter<String> adapter_cholestrol;
    ArrayList<String> arraylist_cholestrol = new ArrayList<>();
    EditText et_hdl;
    EditText et_ldl;
    EditText et_triglyceride;
    GlobalFunction globalFunction;
    ImageView iv_back;
    ListView listViewHeight;
    private PopupWindow popupWindowTime;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_cholestrol;
    TextView tv_hdl;
    TextView tv_ldl;
    TextView tv_search_cholestrol;
    TextView tv_triglyceride;
    TypefaceManager typefaceManager;


    public void attachBaseContext(Context context) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(context));
    }


    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cholestrol_calculator);
        this.globalFunction = new GlobalFunction(this);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.set_locale_language();
        this.iv_back = findViewById(R.id.iv_back);
        this.tv_cholestrol = findViewById(R.id.tv_cholestrol);
        this.tv_hdl = findViewById(R.id.tv_hdl);
        this.tv_ldl = findViewById(R.id.tv_ldl);
        this.tv_triglyceride = findViewById(R.id.tv_triglyceride);
        this.tv_search_cholestrol = findViewById(R.id.tv_search_cholestrol);
        this.et_hdl = findViewById(R.id.et_hdl);
        this.et_ldl = findViewById(R.id.et_ldl);
        this.et_triglyceride = findViewById(R.id.et_triglyceride);
        this.adView = findViewById(R.id.adView);
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        this.et_hdl.setTypeface(this.typefaceManager.getLight());
        this.et_ldl.setTypeface(this.typefaceManager.getLight());
        this.tv_cholestrol.setTypeface(this.typefaceManager.getBold());
        this.tv_hdl.setTypeface(this.typefaceManager.getLight());
        this.tv_ldl.setTypeface(this.typefaceManager.getLight());
        this.tv_search_cholestrol.setTypeface(this.typefaceManager.getBold());
        this.tv_triglyceride.setTypeface(this.typefaceManager.getLight());
        this.et_triglyceride.setTypeface(this.typefaceManager.getLight());
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.tv_hdl.setOnClickListener(showPopupWindowTime(0));
        this.tv_ldl.setOnClickListener(showPopupWindowTime(1));
        this.tv_triglyceride.setOnClickListener(showPopupWindowTime(2));
        this.arraylist_cholestrol.clear();
        this.arraylist_cholestrol.add(getString(R.string.mgdl));
        this.arraylist_cholestrol.add(getString(R.string.mgol_a));
        this.adapter_cholestrol = new ArrayAdapter<>(this, R.layout.spinner_item, R.id.text1, this.arraylist_cholestrol);
        this.iv_back.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Cholestrol_Calculator.this.onBackPressed();
            }
        });
        this.tv_search_cholestrol.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (Cholestrol_Calculator.this.et_hdl.getText().toString().trim().equals("")) {
                    Cholestrol_Calculator.this.et_hdl.setError(Cholestrol_Calculator.this.getString(R.string.enter_hdl));
                } else if (Cholestrol_Calculator.this.et_ldl.getText().toString().trim().equals("")) {
                    Cholestrol_Calculator.this.et_ldl.setError(Cholestrol_Calculator.this.getString(R.string.enter_ldl));
                } else if (Cholestrol_Calculator.this.et_triglyceride.getText().toString().trim().equals("")) {
                    Cholestrol_Calculator.this.et_triglyceride.setError(Cholestrol_Calculator.this.getString(R.string.enter_triglyceride));
                } else {
                    int random = ((int) (Math.random() * 2.0d)) + 1;
                    PrintStream printStream = System.out;
                    StringBuilder sb = new StringBuilder();
                    sb.append("random_number==>");
                    sb.append(random);
                    printStream.println(sb.toString());
                }
            }
        });
    }

    public void calculate() {
        float f;
        float f2;
        float f3;
        if (this.tv_hdl.getText().toString().trim().equals(getString(R.string.mgdl))) {
            f = Float.parseFloat(this.et_hdl.getText().toString().trim());
        } else {
            f = Float.parseFloat(this.et_hdl.getText().toString().trim()) * 18.0f;
        }
        if (this.tv_ldl.getText().toString().trim().equals(getString(R.string.mgdl))) {
            f2 = Float.parseFloat(this.et_ldl.getText().toString().trim());
        } else {
            f2 = Float.parseFloat(this.et_ldl.getText().toString().trim()) * 18.0f;
        }
        if (this.tv_triglyceride.getText().toString().trim().equals(getString(R.string.mgdl))) {
            f3 = Float.parseFloat(this.et_triglyceride.getText().toString().trim());
        } else {
            f3 = Float.parseFloat(this.et_triglyceride.getText().toString().trim()) * 18.0f;
        }
        double d = f + f2 + (f3 / 5.0f);
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(d);
        Log.d("cholestrol->", sb.toString());
        Intent intent = new Intent(this, Cholestrol_Result.class);
        intent.putExtra("cholestrol", d);
        startActivity(intent);
    }

    private OnClickListener showPopupWindowTime(final int i) {
        return new OnClickListener() {
            public void onClick(View view) {
                Cholestrol_Calculator.this.popupWindowTime(i).showAsDropDown(view, 0, 0);
            }
        };
    }


    public PopupWindow popupWindowTime(final int i) {
        this.popupWindowTime = new PopupWindow(this);
        this.listViewHeight = new ListView(this);
        this.listViewHeight.setDividerHeight(0);
        this.listViewHeight.setAdapter(this.adapter_cholestrol);
        this.listViewHeight.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    Cholestrol_Calculator.this.tv_hdl.setText(Cholestrol_Calculator.this.arraylist_cholestrol.get(i));
                } else if (i == 1) {
                    Cholestrol_Calculator.this.tv_ldl.setText(Cholestrol_Calculator.this.arraylist_cholestrol.get(i));
                } else {
                    Cholestrol_Calculator.this.tv_triglyceride.setText(Cholestrol_Calculator.this.arraylist_cholestrol.get(i));
                }
                Cholestrol_Calculator.this.dismissPopupTime();
            }
        });
        this.popupWindowTime.setFocusable(true);
        this.popupWindowTime.setWidth(this.tv_hdl.getMeasuredWidth());
        this.popupWindowTime.setHeight(-2);
        this.popupWindowTime.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), 17170443));
        this.popupWindowTime.setContentView(this.listViewHeight);
        return this.popupWindowTime;
    }


    public void dismissPopupTime() {
        if (this.popupWindowTime != null) {
            this.popupWindowTime.dismiss();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        this.adView.setVisibility(8);
        ActivityCompat.finishAfterTransition(this);
    }
}
