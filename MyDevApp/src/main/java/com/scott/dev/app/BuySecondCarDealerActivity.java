package com.scott.dev.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zamplus.businesstrack.ZampAppAnalytics;

import java.util.HashMap;

//二手车询价Activity
public class BuySecondCarDealerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = this.getApplicationContext();

        //开启SDK的日志输出
        ZampAppAnalytics.setLogStatus(true);
        ZampAppAnalytics.init(context);
        //zamplus rmkt代码

        String idtype = "imei";
        String moduleId = "123-4-567";
        String carId = "89011";
        String pt = "buy-2ndcar-dealer";
        String pageName=  "购买二手车-咨询底价页";
        String isSucc = "0";
        String eventId = "2ndCarDealer";
        String eventLable = eventId+"_lbl";

        HashMap<String,String> params = new HashMap<String,String>();
        params.put("idtype",idtype);//
        params.put("moduleId",moduleId );//车型id，请用实际车型id替换moduleId
        params.put("carId", carId);//车款id，请用实际车款id替换carId
        params.put("pt",pt);
        params.put("pageName",pageName);
        params.put("isSucc",isSucc);
        ZampAppAnalytics.onEvent(context, eventId, eventLable, params);


        setContentView(R.layout.second_car_dealer);

    }

    @Override
    protected void onPause() {
        super.onPause();


        //zamplus
        ZampAppAnalytics.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();


        //zamplus
        ZampAppAnalytics.onResume(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void bargainerBackToCarInfo(View view) {
        //创建一个意图
        Intent intent = new Intent(BuySecondCarDealerActivity.this, SecondHandCarInfoActivity.class);
        startActivity(intent);
    }

    public void bargainerToBargainSuccess(View view) {
        //创建一个意图
        Intent intent = new Intent(BuySecondCarDealerActivity.this, BuySecondCarDealSuccessActivity.class);
        startActivity(intent);
    }

    public void bargainerBackToHome(View view) {
        //创建一个意图
        Intent intent = new Intent(BuySecondCarDealerActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
