package com.scott.dev.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.scott.dev.app.util.CommonConsts;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.dplus.UMADplus;
import com.umeng.commonsdk.UMConfigure;
import com.zamplus.businesstrack.ZampAppAnalytics;

import java.util.HashMap;
import java.util.Map;

public class BuySecondCarBargainSuccessActivity extends Activity {

    @Override
    protected void onPause() {
        super.onPause();

        //zamplus
        ZampAppAnalytics.onPause(this);

        //umeng
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //zamplus
        ZampAppAnalytics.onResume(this);

        //umeng
        MobclickAgent.onResume(this);
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
        String pt = "buy-2ndcar-bargain-success";
        String pageName = "购买二手车-我要砍价成功页";
        String isSucc = "1";
        String eventId = "2ndCarBargainSucc";
        String eventLable = eventId + "_lbl";

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("idtype", idtype);//
        params.put("moduleId", moduleId);//车型id，请用实际车型id替换moduleId
        params.put("carId", carId);//车款id，请用实际车款id替换carId
        params.put("pt", pt);
        params.put("pageName", pageName);
        params.put("isSucc", isSucc);
        params.put("info", "18612770743,100");//请用实际手机号码(加密后的手机号或实际的LeadsID均可)替换orderId
        ZampAppAnalytics.onEvent(context, eventId, eventLable, params);

        //umeng configuration
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);

        /**
         * 设置日志加密
         * 参数：boolean 默认为false（不加密）
         */
        UMConfigure.setEncryptEnabled(true);

        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:友盟 app key
         * 参数3:友盟 channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "58dc6d66734be46b97002a7d");

        Map<String, Object> umParam = new HashMap<String, Object>();
        umParam.put("idtype", idtype);//
        umParam.put("moduleId", moduleId);//车型id，请用实际车型id替换moduleId
        umParam.put("carId", carId);//车款id，请用实际车款id替换carId
        umParam.put("pt", pt);
        umParam.put("pageName", pageName);
        umParam.put("isSucc", isSucc);
        umParam.put("info", "18612770743,100");//请用实际手机号码(加密后的手机号或实际的LeadsID均可)替换orderId
        UMADplus.track(context, eventId, umParam);

        //talkingdata
        TCAgent.LOG_ON = true;
        TCAgent.init(context);
        TCAgent.setReportUncaughtExceptions(true);
        TCAgent.onEvent(context, eventId, eventLable, params);

        //mixpanel
        /*String mp_token = "b4ebdd3f7e2de5f3717bd49c40c5bb70";
        //String mp_secret = "c403c17c7399c9760f540021a1bfbce4";
        MixpanelAPI mixpanel = MixpanelAPI.getInstance(context, mp_token);
        mixpanel.trackMap(eventId, umParam);*/

        //google analytics sdk for android v3 (LEGANCY!!!)
        // Obtain the shared Tracker instance.


        //appsflyer
        AppsFlyerLib appsFlyerLib = AppsFlyerLib.getInstance();
        AppsFlyerConversionListener conversionDataListener = new AppsFlyerConversionListener() {
            @Override
            public void onInstallConversionDataLoaded(Map<String, String> map) {
                if(!map.isEmpty()){
                    map.put("bargain_convInfo","186_1357_2468,100");
                }else{
                    map = new HashMap<String,String>();
                    map.put("convInfo","186_1357_2468,100");
                }

            }

            @Override
            public void onInstallConversionFailure(String s) {

            }

            @Override
            public void onAppOpenAttribution(Map<String, String> map) {

            }

            @Override
            public void onAttributionFailure(String s) {

            }

        };

        String appsFlyerId = appsFlyerLib.getAppsFlyerUID(context);
        appsFlyerLib.init(CommonConsts.AF_DEV_KEY,conversionDataListener);
        appsFlyerLib.trackAppLaunch(context,pageName);
        umParam.put("self_af_id",appsFlyerId);
        appsFlyerLib.trackEvent(context,pageName,umParam);

        setContentView(R.layout.second_car_bargain_success);

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

    public void bargainSuccBackToBargainer(View view) {
        //创建一个意图
        Intent intent = new Intent(BuySecondCarBargainSuccessActivity.this, BuySecondCarBargainerActivity.class);
        startActivity(intent);
    }

    public void bargainSuccBackToCarInfo(View view) {
        //创建一个意图
        Intent intent = new Intent(BuySecondCarBargainSuccessActivity.this, SecondHandCarInfoActivity.class);
        startActivity(intent);
    }

    public void bargainSuccBackToHome(View view) {
        //创建一个意图
        Intent intent = new Intent(BuySecondCarBargainSuccessActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
