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

public class HomeActivity extends Activity {

    @Override
    protected void onPause() {
        super.onPause();

        //zamplus
        ZampAppAnalytics.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("HomeActivity - onDestroy!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("HomeActivity - onRestart!");
    }

    @Override
    protected void onResume() {
        super.onResume();

        //zamplus
        ZampAppAnalytics.onResume(this);

        System.out.println("HomeActivity - onResume!");
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("HomeActivity - onStart!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("HomeActivity - onStop!");
    }

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
        String pt = "taoche-2rdcar-home";
        String pageName = "淘车二手车-首页";
        String eventId = "2ndCarHomePage";
        String eventLable = eventId + "_lbl";

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("idtype", idtype);//
        params.put("moduleId", moduleId);//车型id，请用实际车型id替换moduleId
        params.put("carId", carId);//车款id，请用实际车款id替换carId
        params.put("pt", pt);
        params.put("pageName", pageName);
        ZampAppAnalytics.onEvent(context, eventId, eventLable, params);


        setContentView(R.layout.home);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 通过这个方法跳转到SecondHandCarInfoActivity界面
     */
    public void homeToSecondCarInfo(View view) {
        //创建一个意图
        Intent intent = new Intent(HomeActivity.this, SecondHandCarInfoActivity.class);
        startActivity(intent);
    }

    public void home2SecondCarDealer(View view) {
        //创建一个意图
        Intent intent = new Intent(HomeActivity.this, BuySecondCarDealerActivity.class);
        startActivity(intent);
    }

    public void home2SecondCarBargainer(View view) {
        //创建一个意图
        Intent intent = new Intent(HomeActivity.this, BuySecondCarBargainerActivity.class);
        startActivity(intent);
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
}
