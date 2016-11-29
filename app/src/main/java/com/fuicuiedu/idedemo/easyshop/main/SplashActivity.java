package com.fuicuiedu.idedemo.easyshop.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.feicuiedu.apphx.model.HxUserManager;
import com.fuicuiedu.idedemo.easyshop.R;
import com.fuicuiedu.idedemo.easyshop.commons.ActivityUtils;
import com.fuicuiedu.idedemo.easyshop.commons.CurrentUser;
import com.fuicuiedu.idedemo.easyshop.model.CachePreferences;
import com.fuicuiedu.idedemo.easyshop.model.User;
import com.hyphenate.easeui.domain.EaseUser;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private ActivityUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        EventBus.getDefault().register(this);

        utils = new ActivityUtils(this);

        /**
         * 账号冲突后，自动退出
         * 由apphx模块HxMainService发出
         * */
        if (getIntent().getBooleanExtra("AUTO_LOGIN",false)){
            //清除本地缓存的用户信息
            CachePreferences.clearAllData();
            //踢出时，登出环信
            HxUserManager.getInstance().asyncLogout();
        }
        //当前用户如果是已经登陆过但是未登出的，再次进入需要自动登录
        if (CachePreferences.getUser().getName() != null
                && !HxUserManager.getInstance().isLogin()){
            User user = CachePreferences.getUser();
            EaseUser easeUser = CurrentUser.convert(user);
            HxUserManager.getInstance().asyncLogin(easeUser,user.getPassword());
            return;
        }

        Timer timer = new Timer();
        //1.5秒后跳转到主页面并且finish
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                utils.startActivity(MainActivity.class);
                finish();
            }
        },1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
