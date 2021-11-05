package com.capstone2.gymsbond.trademill;

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
import android.widget.Toast;
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


public class Trademill_Calculator extends Activity {
    String TAG = getClass().getSimpleName();
    AdView adView;
    ArrayAdapter<String> adapter_gender;
    ArrayList<String> arraylist_gender = new ArrayList<>();
    EditText et_minute;
    String gender;
    GlobalFunction globalFunction;
    ImageView iv_back;
    ListView listViewGender;
    private PopupWindow popupWindowGender;
    SharedPreferenceManager sharedPreferenceManager;
    Double trademill;
    Double trademill_time;
    TextView tv_calculate_trademill;
    TextView tv_gender;
    TextView tv_genderunit;
    TextView tv_trademill;
    TypefaceManager typefaceManager;


    public void attachBaseContext(Context context) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(context));
    }


    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.trademil_calculator);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.set_locale_language();
        this.iv_back = findViewById(R.id.iv_back);
        this.tv_genderunit = findViewById(R.id.tv_genderunit);
        this.tv_gender = findViewById(R.id.tv_gender);
        this.tv_calculate_trademill = findViewById(R.id.tv_calculate_trademill);
        this.et_minute = findViewById(R.id.et_minute);
        this.tv_trademill = findViewById(R.id.tv_trademill);
        this.adView = findViewById(R.id.adView);
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        this.tv_trademill.setTypeface(this.typefaceManager.getBold());
        this.tv_calculate_trademill.setTypeface(this.typefaceManager.getBold());
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        this.tv_gender.setOnClickListener(showPopupWindowGender());
        this.arraylist_gender.clear();
        this.arraylist_gender.add(getString(R.string.Male));
        this.arraylist_gender.add(getString(R.string.Female));
        this.adapter_gender = new ArrayAdapter<>(this, R.layout.spinner_item, R.id.text1, this.arraylist_gender);
        this.tv_calculate_trademill.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (Trademill_Calculator.this.et_minute.getText().toString().trim().equals("")) {
                    Toast.makeText(Trademill_Calculator.this.getApplicationContext(), Trademill_Calculator.this.getString(R.string.Enter_time), 0).show();
                    return;
                }
                Trademill_Calculator.this.gender = Trademill_Calculator.this.tv_gender.getText().toString().trim();
                Trademill_Calculator.this.trademill_time = Double.valueOf(Double.parseDouble(Trademill_Calculator.this.et_minute.getText().toString().trim()));
                int random = ((int) (Math.random() * 2.0d)) + 1;
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("random_number==>");
                sb.append(random);
                printStream.println(sb.toString());
            }
        });
        this.iv_back.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Trademill_Calculator.this.onBackPressed();
            }
        });
    }

    public void gettrademill() {
        if (this.gender.equalsIgnoreCase(getString(R.string.Male))) {
            this.trademill = Double.valueOf(((14.8d - (this.trademill_time.doubleValue() * 1.379d)) + ((this.trademill_time.doubleValue() * 0.451d) * this.trademill_time.doubleValue())) - (((this.trademill_time.doubleValue() * 0.012d) * this.trademill_time.doubleValue()) * this.trademill_time.doubleValue()));
        } else {
            this.trademill = Double.valueOf((this.trademill_time.doubleValue() * 4.38d) - 3.9d);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("trademill");
        sb.append(this.trademill);
        Log.d("trademill", sb.toString());
        Intent intent = new Intent(this, Trademill_Result.class);
        intent.putExtra("trademill", this.trademill);
        startActivity(intent);
    }

    private OnClickListener showPopupWindowGender() {
        return new OnClickListener() {
            public void onClick(View view) {
                Trademill_Calculator.this.popupWindowGender().showAsDropDown(view, 0, 0);
            }
        };
    }


    public PopupWindow popupWindowGender() {
        this.popupWindowGender = new PopupWindow(this);
        this.listViewGender = new ListView(this);
        this.listViewGender.setDividerHeight(0);
        this.listViewGender.setAdapter(this.adapter_gender);
        this.listViewGender.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Trademill_Calculator.this.tv_gender.setText(Trademill_Calculator.this.arraylist_gender.get(i));
                Trademill_Calculator.this.tv_genderunit.setText(Trademill_Calculator.this.arraylist_gender.get(i));
                Trademill_Calculator.this.dismissPopupGender();
            }
        });
        this.popupWindowGender.setFocusable(true);
        this.popupWindowGender.setWidth(this.tv_gender.getMeasuredWidth());
        this.popupWindowGender.setHeight(-2);
        this.popupWindowGender.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), 17170443));
        this.popupWindowGender.setContentView(this.listViewGender);
        return this.popupWindowGender;
    }


    public void dismissPopupGender() {
        if (this.popupWindowGender != null) {
            this.popupWindowGender.dismiss();
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
